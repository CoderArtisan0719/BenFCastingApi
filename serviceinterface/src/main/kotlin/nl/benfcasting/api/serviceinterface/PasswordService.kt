package nl.benfcasting.api.serviceinterface

interface PasswordService {
    fun hashPassword(password: String): String
    fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean
}