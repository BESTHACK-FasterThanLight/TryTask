package ru.ftl.besthack.di.app

import dagger.Component
import ru.ftl.besthack.di.auth.AuthComponent
import ru.ftl.besthack.di.auth.AuthModule
import javax.inject.Singleton

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */
@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun plus(module: AuthModule): AuthComponent
}