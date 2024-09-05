package nl.benfcasting.api.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // Apply to all endpoints
            .allowedOrigins("*") // Allow all origins
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify the allowed methods
            .allowedHeaders("*") // Allow all headers
//            .allowCredentials(true) // Include credentials in the CORS requests
            .maxAge(3600) // How long the response from a pre-flight request can be cached in seconds
    }
}
