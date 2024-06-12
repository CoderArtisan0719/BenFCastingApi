package nl.benfcasting.api.api.dto.rx

interface RxDto<T> {
    fun toDomainModel(): T
}