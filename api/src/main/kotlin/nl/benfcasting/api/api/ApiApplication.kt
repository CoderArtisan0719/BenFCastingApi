package nl.benfcasting.api.api

import nl.benfcasting.api.factory.Factory
import jakarta.persistence.EntityManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor
import org.springframework.core.env.Environment

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EntityScan("nl.benfcasting.api.model")
class ApiApplication {
    @Bean
    fun factory(entityManager: EntityManager, environment: Environment): Factory {
        return Factory(entityManager, environment)
    }

    @Bean
    fun methodValidationPostProcessor(): MethodValidationPostProcessor {
        return MethodValidationPostProcessor()
    }
}

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
