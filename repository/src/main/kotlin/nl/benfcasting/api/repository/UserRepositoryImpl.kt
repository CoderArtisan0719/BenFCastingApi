package nl.benfcasting.api.repository

import com.google.inject.Inject
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User
import nl.benfcasting.api.repositoryinterface.UserRepository

class UserRepositoryImpl @Inject constructor(
    private var userDal: UserDal
) : UserRepository {

    override fun getUser(): User {
        return this.userDal.getUser()
    }
}