package nl.benfcasting.api.logicinterface

import nl.benfcasting.api.model.User

interface UserLogic {
    fun getUser(): User
}