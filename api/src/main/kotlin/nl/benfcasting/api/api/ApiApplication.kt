package nl.benfcasting.api.api

import nl.benfcasting.api.factory.Factory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan("nl.benfcasting.api", "nl.benfcasting.api.factory")
@EntityScan(basePackages = ["nl.benfcasting.api.model"])
class ApiApplication {
    @Bean
    fun factory(entityManager: EntityManager): Factory {
        return Factory(entityManager)
    }

    @Bean
    fun methodValidationPostProcessor(): MethodValidationPostProcessor {
        return MethodValidationPostProcessor()
    }
}

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}