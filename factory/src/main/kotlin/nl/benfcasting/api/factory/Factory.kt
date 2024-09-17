// factory/src/main/kotlin/nl.benfcasting.api.api/Factory
package nl.benfcasting.api.factory

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
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

@Component
class Factory(entityManager: EntityManager) : AbstractModule() {
    private val entityManager: EntityManager = entityManager

    var injector: Injector = Guice.createInjector(this)

    final inline fun <reified T> resolve(): T {
        return injector.getInstance(T::class.java)
    }

    override fun configure() {
        bind(EntityManager::class.java).toInstance(entityManager)
        bind(UserDal::class.java).to(UserDalImpl::class.java)
        bind(UserLogic::class.java).to(UserLogicImpl::class.java)
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
        bind(PasswordService::class.java).to(PasswordServiceImpl::class.java)
        bind(UserService::class.java).to(UserServiceImpl::class.java)
    }
}