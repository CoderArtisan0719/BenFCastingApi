package nl.benfcasting.api.logic

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.serviceinterface.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import java.util.*

class UserLogicImplTest {

    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService
    private lateinit var userLogic: UserLogicImpl

    private val jwtSecret = Base64.getEncoder().encodeToString("9VXsL1HSqnpB5oc7LJrx5K5XI+Ficqu1uvpG4uJhtQo=".toByteArray())

    @BeforeEach
    fun setUp() {
        userRepository = mock()
        userService = mock()
        userLogic = UserLogicImpl(userRepository, userService, jwtSecret)
    }

    @Test
    fun `login should return user when credentials are correct and user is admin`() {
        val email = "admin@example.com"
        val password = "password123"
        val user = User(
            id = 1L,
            firstname = "John",
            preposition = "",
            lastname = "Doe",
            fullname = "John Doe",
            email = email,
            password = password,
            type = UserType.admin,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        whenever(userRepository.findByEmail(email)).thenReturn(user)
        whenever(userService.authenticateUser(password, user.password)).thenReturn(true)

        val result = userLogic.login(email, password)
        assertNotNull(result)
        assertEquals(user, result)
    }

    @Test
    fun `login should throw exception when user is not admin`() {
        val email = "user@example.com"
        val password = "password123"
        val user = User(
            id = 1L,
            firstname = "Jane",
            preposition = "",
            lastname = "Doe",
            fullname = "Jane Doe",
            email = email,
            password = password,
            type = UserType.subscriber,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        whenever(userRepository.findByEmail(email)).thenReturn(user)
        whenever(userService.authenticateUser(password, user.password)).thenReturn(true)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogic.login(email, password)
        }
        assertEquals("Alleen beheerders mogen inloggen", exception.message)
    }

    @Test
    fun `login should throw exception when password is incorrect`() {
        val email = "admin@example.com"
        val password = "password123"
        val user = User(
            id = 1L,
            firstname = "John",
            preposition = "",
            lastname = "Doe",
            fullname = "John Doe",
            email = email,
            password = "wrong-password",
            type = UserType.admin,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        whenever(userRepository.findByEmail(email)).thenReturn(user)
        whenever(userService.authenticateUser(password, user.password)).thenReturn(false)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogic.login(email, password)
        }
        assertEquals("Uw wachtwoord en e-mailcombinatie zijn niet gevonden", exception.message)
    }

    @Test
    fun `generateToken should return valid JWT token`() {
        val user = User(
            id = 1L,
            firstname = "John",
            preposition = "",
            lastname = "Doe",
            fullname = "John Doe",
            email = "admin@example.com",
            password = "password123",
            type = UserType.admin,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val token = userLogic.generateToken(user)

        assertNotNull(token)
        val claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)))
            .build()
            .parseClaimsJws(token)
            .body

        assertEquals(user.email, claims.subject)
    }
}
