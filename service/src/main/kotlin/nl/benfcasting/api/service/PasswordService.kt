package nl.benfcasting.api.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordService {
    private val passwordEncoder = BCryptPasswordEncoder()
    fun hashPassword(password: String): String {
        return passwordEncoder.encode(password)
    }
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainPassword, hashedPassword)
    }
}
