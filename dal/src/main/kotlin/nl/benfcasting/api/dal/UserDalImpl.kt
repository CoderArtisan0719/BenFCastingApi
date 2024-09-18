package nl.benfcasting.api.dal

import com.google.inject.Inject
import com.google.inject.Singleton
import jakarta.persistence.EntityManager
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.model.User

@Singleton
class UserDalImpl @Inject constructor (
    private val entityManager: EntityManager,
) : UserDal {
    override fun findByEmail(email: String): User? {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User::class.java)
            .setParameter("email", email)
            .resultList
            .firstOrNull()
    }
}
