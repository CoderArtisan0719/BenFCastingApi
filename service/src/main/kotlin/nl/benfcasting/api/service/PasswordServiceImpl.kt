package nl.benfcasting.api.service

import com.google.inject.Singleton

import nl.benfcasting.api.serviceinterface.PasswordService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Singleton
class PasswordServiceImpl : PasswordService {
    private val passwordEncoder = BCryptPasswordEncoder()
    override fun hashPassword(password: String): String {
        return passwordEncoder.encode(password)
    }
    override fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainPassword, hashedPassword)
    }
}
