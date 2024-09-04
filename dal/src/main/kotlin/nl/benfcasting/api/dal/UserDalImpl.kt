package nl.benfcasting.api.dal

import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User

class UserDalImpl: UserDal {
    override fun getUser(): User {
        return User()
    }
}