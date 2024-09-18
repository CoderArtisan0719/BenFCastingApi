package nl.benfcasting.api.service

import nl.benfcasting.api.serviceinterface.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val passwordServiceImpl: PasswordServiceImpl
) : UserService {
    override fun authenticateUser(plainPassword: String, password: String): Boolean {
        return passwordServiceImpl.verifyPassword(plainPassword, password)
    }
}
