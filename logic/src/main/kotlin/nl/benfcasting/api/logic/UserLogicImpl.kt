package nl.benfcasting.api.logic

import com.google.inject.Inject
import com.google.inject.Singleton
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import nl.benfcasting.api.serviceinterface.UserService

@Singleton
class UserLogicImpl @Inject constructor(
    private val userService: UserService,
) : UserLogic {
    override fun login(email: String, password: String): User {

        val user = userService.findByEmail(email)
            ?: throw IllegalArgumentException("Uw wachtwoord en e-mailcombinatie zijn niet gevonden")

        if (!userService.authenticateUser(password, user.password)) {
            throw IllegalArgumentException("Uw wachtwoord en e-mailcombinatie zijn niet gevonden")
        }

        if (user.type != UserType.admin) {
            throw IllegalArgumentException("Alleen beheerders mogen inloggen")
        }

        return user
    }

    override fun generateToken(user: User): String {
        return userService.generateToken(user)
    }
}