// logic/src/main/kotlin/nl.benfcasting.api.api/UserLogicImpl
package nl.benfcasting.api.logic

import com.google.inject.Inject
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.service.UserService
import org.springframework.stereotype.Service
import javax.crypto.SecretKey
import java.util.*

@Service
class UserLogicImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
) : UserLogic {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("9VXsL1HSqnpB5oc7LJrx5K5XI+Ficqu1uvpG4uJhtQo="))

    override fun login(email: String, password: String): User {

        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("Voer het juiste e-mailadres in")

        if (!userService.authenticateUser(password, user.password)) {
            throw IllegalArgumentException("Voer het juiste wachtwoord in")
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