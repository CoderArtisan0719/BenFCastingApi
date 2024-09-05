// api/src/main/kotlin/nl.benfcasting.api.api/dto/rx/RxDto
package nl.benfcasting.api.api.dto.rx

interface RxDto<T> {
    fun toDomainModel(): T
}