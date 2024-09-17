package nl.benfcasting.api.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import nl.benfcasting.api.serviceinterface.PasswordService
import org.springframework.stereotype.Service

@Service
class PasswordServiceImpl : PasswordService {
    private val passwordEncoder = BCryptPasswordEncoder()
    override fun hashPassword(password: String): String {
        return passwordEncoder.encode(password)
    }
    override fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainPassword, hashedPassword)
    }
}
