// repositoryinterface/src/main/kotlin/nl.benfcasting.api.api/UserRepository
package nl.benfcasting.api.repositoryinterface

import nl.benfcasting.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}