package nl.benfcasting.api.logic

import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import nl.benfcasting.api.serviceinterface.UserService
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class UserLogicImplTest {

    private lateinit var userService: UserService
    private lateinit var userLogic: UserLogicImpl

    @BeforeEach
    fun setUp() {
        userService = mock(UserService::class.java)
        userLogic = UserLogicImpl(userService)
    }

    @Test
    fun `test successful login for admin user`() {
        val user = User(
            id = 1,
            firstname = "John",
            preposition = "van",
            lastname = "Doe",
            fullname = "John van Doe",
            email = "admin@example.com",
            password = "hashed_password",
            type = UserType.admin
        )
        `when`(userService.findByEmail("admin@example.com")).thenReturn(user)
        `when`(userService.authenticateUser("password", "hashed_password")).thenReturn(true)

        val result = userLogic.login("admin@example.com", "password")

        assert(result.email == user.email)
    }

    @Test
    fun `test login with invalid email`() {
        `when`(userService.findByEmail("invalid@example.com")).thenReturn(null)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogic.login("invalid@example.com", "password")
        }
        assert(exception.message == "Uw wachtwoord en e-mailcombinatie zijn niet gevonden")
    }

    @Test
    fun `test login with wrong password`() {
        val user = User(
            id = 1,
            firstname = "John",
            preposition = "van",
            lastname = "Doe",
            fullname = "John van Doe",
            email = "admin@example.com",
            password = "hashed_password",
            type = UserType.admin
        )
        `when`(userService.findByEmail("admin@example.com")).thenReturn(user)
        `when`(userService.authenticateUser("wrong_password", "hashed_password")).thenReturn(false)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogic.login("admin@example.com", "wrong_password")
        }
        assert(exception.message == "Uw wachtwoord en e-mailcombinatie zijn niet gevonden")
    }

    @Test
    fun `test login for non-admin user`() {
        val user = User(
            id = 1,
            firstname = "Jane",
            preposition = "van",
            lastname = "Smith",
            fullname = "Jane van Smith",
            email = "user@example.com",
            password = "hashed_password",
            type = UserType.subscriber
        )
        `when`(userService.findByEmail("user@example.com")).thenReturn(user)
        `when`(userService.authenticateUser("password", "hashed_password")).thenReturn(true)

        val exception = assertThrows(IllegalArgumentException::class.java) {
            userLogic.login("user@example.com", "password")
        }
        assert(exception.message == "Alleen beheerders mogen inloggen")
    }
}
