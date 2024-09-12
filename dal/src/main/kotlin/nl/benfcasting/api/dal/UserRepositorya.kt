package nl.benfcasting.api.dal

import nl.benfcasting.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepositorya : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}
