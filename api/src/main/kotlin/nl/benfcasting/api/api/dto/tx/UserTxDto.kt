package nl.benfcasting.api.api.dto.tx

import nl.benfcasting.api.model.User

class UserTxDto: TxDto<User, UserTxDto> {
    var id: Int? = null

    override fun toDto(domainModel: User): UserTxDto {
        id = domainModel.id
        return this
    }
}