package nl.benfcasting.api.logic

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import nl.benfcasting.api.api.ApiApplication
import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.service.UserServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest(classes = [ApiApplication::class])
class UserLogicImplTest {

    private lateinit var userRepository: UserRepository
    private lateinit var userServiceImpl: UserServiceImpl
    private lateinit var userLogicImpl: UserLogicImpl

    @BeforeEach
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        userServiceImpl = mock(UserServiceImpl::class.java)
        userLogicImpl = UserLogicImpl(userRepository, userServiceImpl)
    }

    @Test
    fun `login should throw exception if user is not found`() {
        val email = "test@example.com"
        val password = "password"

        `when`(userRepository.findByEmail(email)).thenReturn(null)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogicImpl.login(email, password)
        }

        assertEquals("Voer het juiste e-mailadres in", exception.message)
    }

    @Test
    fun `login should throw exception if password does not match`() {
        val email = "test@example.com"
        val password = "wrong-password"
        val user = User(
            id = 1L,
            firstname = "John",
            preposition = "",
            lastname = "Doe",
            fullname = "John Doe",
            email = email,
            password = "correct-password",
            type = UserType.admin,
            verified = true
        )

        `when`(userRepository.findByEmail(email)).thenReturn(user)
        `when`(userServiceImpl.authenticateUser(password, user.password)).thenReturn(false)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogicImpl.login(email, password)
        }

        assertEquals("Voer het juiste wachtwoord in", exception.message)
    }

    @Test
    fun `login should throw exception if user is not admin`() {
        val email = "test@example.com"
        val password = "correct-password"
        val user = User(
            id = 1L,
            firstname = "John",
            preposition = "",
            lastname = "Doe",
            fullname = "John Doe",
            email = email,
            password = password,
            type = UserType.subscriber, // Not an admin
            verified = true
        )

        `when`(userRepository.findByEmail(email)).thenReturn(user)
        `when`(userServiceImpl.authenticateUser(password, user.password)).thenReturn(true)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogicImpl.login(email, password)
        }

        assertEquals("Alleen beheerders mogen inloggen", exception.message)
    }

    @Test
    fun `login should return user when valid credentials are provided`() {
        val email = "test@example.com"
        val password = "correct-password"
        val user = User(
            id = 1L,
            firstname = "John",
            preposition = "",
            lastname = "Doe",
            fullname = "John Doe",
            email = email,
            password = password,
            type = UserType.admin,
            verified = true
        )

        `when`(userRepository.findByEmail(email)).thenReturn(user)
        `when`(userServiceImpl.authenticateUser(password, user.password)).thenReturn(true)

        val result = userLogicImpl.login(email, password)

        assertNotNull(result)
        assertEquals(email, result.email)
        verify(userRepository).findByEmail(email)
        verify(userServiceImpl).authenticateUser(password, user.password)
    }

    @Test
    fun `generateToken should return a valid JWT token`() {
        val user = User(
            id = 1L,
            firstname = "John",
            preposition = "",
            lastname = "Doe",
            fullname = "John Doe",
            email = "test@example.com",
            password = "correct-password",
            type = UserType.admin,
            verified = true
        )

        val token = userLogicImpl.generateToken(user)

        assertNotNull(token)
        assertTrue(token.isNotEmpty())

        val claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode("9VXsL1HSqnpB5oc7LJrx5K5XI+Ficqu1uvpG4uJhtQo=")))
            .build()
            .parseClaimsJws(token)

        assertEquals(user.email, claims.body.subject)
    }
}
