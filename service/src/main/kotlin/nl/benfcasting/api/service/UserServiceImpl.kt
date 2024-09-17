package nl.benfcasting.api.service

import com.google.inject.Inject
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.serviceinterface.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Inject constructor(
    private val userDal: UserDal,
    private val passwordServiceImpl: PasswordServiceImpl
) : UserService {
    override fun authenticateUser(plainPassword: String, password: String): Boolean {
        return passwordServiceImpl.verifyPassword(plainPassword, password)
    }
}
