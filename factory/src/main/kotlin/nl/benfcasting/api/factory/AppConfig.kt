package nl.benfcasting.api.factory

import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.repository.UserRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun userRepository(): UserRepository {
        return UserRepositoryImpl()
    }

    @Bean
    fun userService(): UserService {
        return UserServiceImpl(userRepository())
    }
}