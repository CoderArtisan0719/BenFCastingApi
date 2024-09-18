// logic/src/main/kotlin/nl.benfcasting.api.api/UserLogicImpl
package nl.benfcasting.api.logic

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.SignatureAlgorithm
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.serviceinterface.UserService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.crypto.SecretKey
import java.util.*

@Service
class UserLogicImpl (
    private val userRepository: UserRepository,
    private val userService: UserService,
    @Value("\${JWT_SECRET}")
    private val jwtSecret: String
) : UserLogic {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret))

    override fun login(email: String, password: String): User {

        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("Uw wachtwoord en e-mailcombinatie zijn niet gevonden")

        if (!userService.authenticateUser(password, user.password)) {
            throw IllegalArgumentException("Uw wachtwoord en e-mailcombinatie zijn niet gevonden")
        }

        if (user.type != UserType.admin) {
            throw IllegalArgumentException("Alleen beheerders mogen inloggen")
        }

        return user
    }

    override fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }
}