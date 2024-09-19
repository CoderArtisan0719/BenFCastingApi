package nl.benfcasting.api.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor
import org.springframework.core.env.Environment
import nl.benfcasting.api.factory.Factory

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ApiApplication {
    @Bean
    fun factory(environment: Environment): Factory {
        return Factory(environment)
    }

    @Bean
    fun methodValidationPostProcessor(): MethodValidationPostProcessor {
        return MethodValidationPostProcessor()
    }
}

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
