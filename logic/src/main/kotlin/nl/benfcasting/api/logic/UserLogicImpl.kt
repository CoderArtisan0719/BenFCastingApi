// logic/src/main/kotlin/nl.benfcasting.api.api/UserLogicImpl
package nl.benfcasting.api.logic

import com.google.inject.Inject
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.io.Decoders
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.model.User
import nl.benfcasting.api.model.UserType
import nl.benfcasting.api.repositoryinterface.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserLogicImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserLogic {
    private val logger = LoggerFactory.getLogger(UserLogicImpl::class.java)
    private val passwordEncoder = BCryptPasswordEncoder()

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("9VXsL1HSqnpB5oc7LJrx5K5XI+Ficqu1uvpG4uJhtQo="))

    override fun login(email: String, password: String): User {
        logger.info("Logging in user $email")
        logger.info(password)
        logger.info("secretKey: $secretKey")
        val enpass = passwordEncoder.encode(password)
        logger.info("enpass: $enpass")
        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("E-mail password combination not found")
        logger.info(user.toString())
//        if (!passwordEncoder.matches(password, user.password)) {
//            throw IllegalArgumentException("E-mail password combination not found")
//        }
        if (password != user.password) {
            throw IllegalArgumentException("E-mail password combination not found")
        }

        if (user.type != UserType.ADMIN) {
            throw IllegalArgumentException("Only admin users are allowed to log in")
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