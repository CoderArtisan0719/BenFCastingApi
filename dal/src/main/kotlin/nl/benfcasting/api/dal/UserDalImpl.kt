// dal/src/main/kotlin/nl.benfcasting.api.api/UserDalImpl
package nl.benfcasting.api.dal

import jakarta.inject.Inject
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import org.springframework.stereotype.Repository

@Repository
class UserDalImpl : UserDal {
    private val users = listOf(
        User(1, "admin@office.com", "password123", UserType.ADMIN, "Admin", "User")
    )

    override fun findByEmail(email: String): User? {
        return users.find { it.email == email }
    }
//    override fun findByEmail(email: String): User? {
//        return userRepository.findByEmail(email)
//    }
}