// api/src/main/kotlin/nl.benfcasting.api.api/dto/TxDto
package nl.benfcasting.api.api.dto.tx

interface TxDto<DM, DTO> {
    fun toDto(domainModel: DM): DTO
}