package nl.benfcasting.api.service

import com.google.inject.Inject
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User
import org.springframework.stereotype.Service

@Service
class UserService @Inject constructor(
    private val userDal: UserDal,
    private val passwordService: PasswordService
){

    // Method to authenticate a user during login
    fun authenticateUser(plainPassword: String, password: String): Boolean {

        // Check if the password matches using PasswordService
        return passwordService.verifyPassword(plainPassword, password)
    }
}
