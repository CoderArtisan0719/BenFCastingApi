package nl.benfcasting.api.logic

import com.google.inject.Inject
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.model.User
import nl.benfcasting.api.repositoryinterface.UserRepository

class UserLogicImpl @Inject constructor(
    private var userRepository: UserRepository
) : UserLogic {

    override fun getUser(): User {
        return this.userRepository.getUser()
    }
}