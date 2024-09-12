// dal/src/main/kotlin/nl.benfcasting.api.api/UserDalImpl

package nl.benfcasting.api.dal

import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User
import nl.benfcasting.api.repositoryinterface.UserRepository
import jakarta.inject.Inject
import org.springframework.stereotype.Component

@Component
class UserDalImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserDal {
    override fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }
}
