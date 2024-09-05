// dalinterface/src/main/kotlin/nl.benfcasting.api.api/UserDal
package nl.benfcasting.api.dalinterface

import nl.benfcasting.api.model.User

interface UserDal {
    fun findByEmail(email: String): User?
}