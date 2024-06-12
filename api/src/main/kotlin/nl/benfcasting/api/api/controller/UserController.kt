package nl.benfcasting.api.api.controller

import nl.benfcasting.api.api.dto.tx.UserTxDto
import nl.benfcasting.api.factory.Factory
import nl.benfcasting.api.logicinterface.UserLogic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("user")
class UserController(factory: Factory) {
    private var userLogic: UserLogic = factory.resolve<UserLogic>();

    @GetMapping
    fun getUser(): ResponseEntity<UserTxDto> {
        val user = userLogic.getUser()
        val dto = UserTxDto().toDto(user)
        return ResponseEntity<UserTxDto>(dto, HttpStatus.OK)
    }
}