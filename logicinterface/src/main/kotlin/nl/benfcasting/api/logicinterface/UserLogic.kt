package nl.benfcasting.api.logicinterface

import nl.benfcasting.api.model.User

interface UserLogic {
    fun login(email: String, password: String): User
    fun generateToken(user: User): String
}
