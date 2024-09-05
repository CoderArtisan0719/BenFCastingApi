// model/src/main/kotlin/nl.benfcasting.api.api/User
package nl.benfcasting.api.model

import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(unique = true, nullable = false)
    val email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: UserType,

    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "last_name", nullable = true)
    val lastName: String? = null,

    val preposition: String? = null
) {
    constructor(): this(0, "", "", UserType.SUBSCRIBER, "", null, null)

    fun encryptPassword() {
        this.password = BCryptPasswordEncoder().encode(this.password)
    }
}

enum class UserType {
    ADMIN, SUBSCRIBER
}