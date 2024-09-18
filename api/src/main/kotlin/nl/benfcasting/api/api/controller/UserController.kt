package nl.benfcasting.api.api.controller

import com.google.inject.Inject
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Valid
import nl.benfcasting.api.factory.Factory
import nl.benfcasting.api.api.dto.tx.LoginRequestDto
import nl.benfcasting.api.api.dto.tx.LoginResponseDto
import nl.benfcasting.api.logicinterface.UserLogic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class UserController @Inject constructor (factory: Factory) {
    private val userLogic = factory.resolve<UserLogic>()

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val user = userLogic.login(request.email, request.password)
        val token = userLogic.generateToken(user)
        return ResponseEntity.ok(LoginResponseDto(token = token,"Login successful", status = true))
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleException(ex: IllegalArgumentException): ResponseEntity<LoginResponseDto> {
        return ResponseEntity(LoginResponseDto(token = "", ex.message ?: "Invalid credentials", status = false), HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleValidationException(ex: ConstraintViolationException): ResponseEntity<LoginResponseDto> {
        return ResponseEntity(LoginResponseDto(token = "", message = "Invalid input: ${ex.message}", status = false), HttpStatus.BAD_REQUEST)
    }
}
