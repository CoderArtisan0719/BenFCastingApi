package nl.benfcasting.api.serviceinterface

import nl.benfcasting.api.model.User

interface UserService {
    fun authenticateUser(plainPassword: String, password: String): Boolean
    fun findByEmail(email: String): User?
    fun generateToken(user: User): String
}