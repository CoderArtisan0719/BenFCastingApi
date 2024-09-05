// repository/src/main/kotlin/nl.benfcasting.api.api/UserRepositoryImpl

package nl.benfcasting.api.repository

import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User
import nl.benfcasting.api.repositoryinterface.UserRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl @Inject constructor(
    private var userDal: UserDal,
    private val entityManager: EntityManager
) : SimpleJpaRepository<User, String>(User::class.java, entityManager), UserRepository {

    override fun findByEmail(email: String): User? {
        return this.userDal.findByEmail(email)
    }
}