package nl.benfcasting.api.api.dto.tx

data class LoginResponseDto(
    val token: String,
    val message: String,
    val status: Boolean
)