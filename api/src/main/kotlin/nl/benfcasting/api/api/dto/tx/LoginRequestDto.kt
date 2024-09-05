package nl.benfcasting.api.api.dto.tx

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:NotBlank(message = "Email is mandatory")
    @field:Email(message = "Email is not in the correct format")
    val email: String,

    @field:NotBlank(message = "Password is mandatory")
    val password: String
)