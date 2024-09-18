package nl.benfcasting.api.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@ComponentScan(basePackages = ["nl.benfcasting.api", "nl.benfcasting.api.factory", "nl.benfcasting.api.service"])
@EntityScan(basePackages = ["nl.benfcasting.api.model"])
@EnableJpaRepositories(basePackages = ["nl.benfcasting.api.repositoryinterface", "nl.benfcasting.api.repository"])
class ApiApplication {

    @Bean
    fun methodValidationPostProcessor(): MethodValidationPostProcessor {
        return MethodValidationPostProcessor()
    }
}

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}