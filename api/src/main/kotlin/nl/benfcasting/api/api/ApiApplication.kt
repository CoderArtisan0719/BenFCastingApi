package nl.benfcasting.api.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("nl.benfcasting.api", "nl.benfcasting.api.factory")
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
