package nl.benfcasting.api.repository

import com.google.inject.Inject
import com.google.inject.Singleton
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User
import nl.benfcasting.api.repositoryinterface.UserRepository

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDal: UserDal,
) : UserRepository {
    override fun findByEmail(email: String): User? {
        return userDal.findByEmail(email)
    }
}