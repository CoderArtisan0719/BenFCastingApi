// model/src/main/kotlin/nl.benfcasting.api.api/User
package nl.benfcasting.api.model

import jakarta.persistence.*
import com.google.inject.Inject

@Entity
@Table(name = "users")
data class User @Inject constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: UserType,

    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "last_name", nullable = true)
    val lastName: String? = null,

    val preposition: String? = null
)

enum class UserType {
    ADMIN, SUBSCRIBER
}