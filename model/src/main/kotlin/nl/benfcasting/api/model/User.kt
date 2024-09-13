// model/src/main/kotlin/nl.benfcasting.api.api/User
package nl.benfcasting.api.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column(name = "firstname", nullable = false, length = 255)
    val firstname: String,

    @Column(name = "preposition", nullable = false, length = 255)
    val preposition: String,

    @Column(name = "lastname", nullable = false, length = 255)
    val lastname: String,

    @Column(name = "fullname", nullable = false, length = 255)
    val fullname: String,

    @Column(name = "email", nullable = false, unique = true, length = 255)
    val email: String,

    @Column(name = "push_token", length = 255)
    val pushToken: String? = null,

    @Column(name = "verified", nullable = false)
    val verified: Boolean = false,

    @Column(name = "privacy_consent")
    val privacyConsent: LocalDateTime? = null,

    @Column(name = "password", nullable = false, length = 255)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 10)
    val type: UserType,

    @Column(name = "remember_token", length = 100)
    val rememberToken: String? = null,

    @Column(name = "created_at")
    val createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime? = null
)

enum class UserType {
    admin, subscriber
}
