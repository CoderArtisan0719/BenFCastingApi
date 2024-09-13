package nl.benfcasting.api.service

import com.google.inject.Inject
import nl.benfcasting.api.dalinterface.UserDal
import org.springframework.stereotype.Service

@Service
class UserService @Inject constructor(
    private val userDal: UserDal,
    private val passwordService: PasswordService
){
    fun authenticateUser(plainPassword: String, password: String): Boolean {
        return passwordService.verifyPassword(plainPassword, password)
    }
}
