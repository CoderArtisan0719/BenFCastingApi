package nl.benfcasting.api.dalinterface

import nl.benfcasting.api.model.User

interface UserDal {
    fun getUser(): User
}