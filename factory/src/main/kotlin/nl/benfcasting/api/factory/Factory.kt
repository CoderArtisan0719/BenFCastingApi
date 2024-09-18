package nl.benfcasting.api.factory

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.name.Names
import jakarta.persistence.EntityManager
import nl.benfcasting.api.dal.UserDalImpl
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.logic.UserLogicImpl
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.repository.UserRepositoryImpl
import nl.benfcasting.api.repositoryinterface.UserRepository
import nl.benfcasting.api.service.PasswordServiceImpl
import nl.benfcasting.api.serviceinterface.PasswordService
import nl.benfcasting.api.service.UserServiceImpl
import nl.benfcasting.api.serviceinterface.UserService
import org.springframework.stereotype.Component
import org.springframework.core.env.Environment

@Component
class Factory(
    private val entityManager: EntityManager,
    private val environment: Environment
) : AbstractModule() {
    var injector: Injector = Guice.createInjector(this)

    final inline fun <reified T> resolve(): T {
        return injector.getInstance(T::class.java)
    }

    override fun configure() {
        val jwtSecret = environment.getProperty("JWT_SECRET")

        bind(EntityManager::class.java).toInstance(entityManager)
        bind(UserDal::class.java).to(UserDalImpl::class.java)
        bind(UserLogic::class.java).to(UserLogicImpl::class.java)
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
        bind(PasswordService::class.java).to(PasswordServiceImpl::class.java)
        bind(UserService::class.java).to(UserServiceImpl::class.java)
        bindConstant().annotatedWith(Names.named("JWT_SECRET")).to(jwtSecret)
    }
}