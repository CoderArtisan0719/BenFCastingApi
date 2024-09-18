// dal/src/main/kotlin/nl.benfcasting.api.api/UserDalImpl

package nl.benfcasting.api.dal

import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User
import nl.benfcasting.api.repositoryinterface.UserRepository
import org.springframework.stereotype.Component

@Component
class UserDalImpl (
    private val userRepository: UserRepository
) : UserDal {
    override fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }
}
