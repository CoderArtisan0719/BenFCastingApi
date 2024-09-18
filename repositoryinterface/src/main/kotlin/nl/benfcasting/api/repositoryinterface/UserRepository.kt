package nl.benfcasting.api.repositoryinterface

import nl.benfcasting.api.model.User

interface UserRepository {
    fun findByEmail(email: String): User?
}