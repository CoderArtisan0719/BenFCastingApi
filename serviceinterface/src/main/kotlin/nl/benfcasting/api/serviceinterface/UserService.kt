package nl.benfcasting.api.serviceinterface

interface UserService {
    fun authenticateUser(plainPassword: String, password: String): Boolean
}