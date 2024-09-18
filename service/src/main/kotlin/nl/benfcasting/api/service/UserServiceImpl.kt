package nl.benfcasting.api.service

import com.google.inject.Inject
import com.google.inject.Singleton
import com.google.inject.name.Named
import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.serviceinterface.UserService
import nl.benfcasting.api.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.SignatureAlgorithm
import javax.crypto.SecretKey
import java.util.*

@Singleton
class UserServiceImpl @Inject constructor (
    private val passwordServiceImpl: PasswordServiceImpl,
    private val userRepository: UserRepository,
    @Named("JWT_SECRET") private val jwtSecret: String
) : UserService {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret))

    override fun authenticateUser(plainPassword: String, password: String): Boolean {
        return passwordServiceImpl.verifyPassword(plainPassword, password)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
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
