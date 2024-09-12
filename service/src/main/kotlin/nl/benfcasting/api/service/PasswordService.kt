package nl.benfcasting.api.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordService {

    private val passwordEncoder = BCryptPasswordEncoder()

    // Method to hash a plain password (in case of password creation/reset)
    fun hashPassword(password: String): String {
        return passwordEncoder.encode(password)
    }

    // Method to verify if a given plain password matches the hashed one
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return passwordEncoder.matches(plainPassword, hashedPassword)
    }
}
