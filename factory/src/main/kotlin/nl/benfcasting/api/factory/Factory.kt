package nl.benfcasting.api.factory

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import nl.benfcasting.api.dal.UserDalImpl
import nl.benfcasting.api.dalinterface.UserDal
import nl.benfcasting.api.logic.UserLogicImpl
import nl.benfcasting.api.logicinterface.UserLogic
import nl.benfcasting.api.repository.UserRepositoryImpl
import nl.benfcasting.api.repositoryinterface.UserRepository
import org.springframework.stereotype.Component


@Component
class Factory : AbstractModule() {
    var injector: Injector = Guice.createInjector(this)

    final inline fun <reified T> resolve(): T {
        return injector.getInstance(T::class.java)
    }

    override fun configure() {
        bind(UserDal::class.java).to(UserDalImpl::class.java)
        bind(UserRepository::class.java).to(UserRepositoryImpl::class.java)
        bind(UserLogic::class.java).to(UserLogicImpl::class.java)
    }
}