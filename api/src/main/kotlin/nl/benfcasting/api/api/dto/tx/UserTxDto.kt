// api/src/main/kotlin/nl.benfcasting.api.api/dto/tx/UserTxDto
package nl.benfcasting.api.api.dto.tx

import nl.benfcasting.api.model.User

class UserTxDto: TxDto<User, UserTxDto> {
    var id: Long? = null

    override fun toDto(domainModel: User): UserTxDto {
        id = domainModel.id
        return this
    }
}