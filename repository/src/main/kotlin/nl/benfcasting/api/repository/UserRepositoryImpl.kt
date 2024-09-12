// repository/src/main/kotlin/nl.benfcasting.api.api/UserRepositoryImpl

package nl.benfcasting.api.repository

import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import nl.benfcasting.api.model.User
import nl.benfcasting.api.repositoryinterface.UserRepository
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl @Inject constructor(
    private val entityManager: EntityManager
) : SimpleJpaRepository<User, Int>(User::class.java, entityManager), UserRepository {

    override fun findByEmail(email: String): User? {
        val query: TypedQuery<User> = entityManager.createQuery(
            "SELECT u FROM User u WHERE u.email = :email",
            User::class.java
        )
        query.setParameter("email", email)
        return query.resultList.firstOrNull()
    }
}