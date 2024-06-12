package nl.benfcasting.api.factory

import nl.benfcasting.api.dal.UserDalImpl
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.logic.UserLogicImpl
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.repository.UserRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun userDal(): UserDal {
        return UserDalImpl()
    }

    @Bean
    fun userRepository(): UserRepository {
        return UserRepositoryImpl(this.userDal())
    }

    @Bean
    fun userLogic(): UserLogic {
        return UserLogicImpl(this.userRepository())
    }
}