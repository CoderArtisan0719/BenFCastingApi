package nl.benfcasting.api.api.dto.tx

interface TxDto<DM, DTO> {
    fun toDto(domainModel: DM): DTO
}