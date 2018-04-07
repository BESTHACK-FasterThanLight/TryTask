package ru.ftl.besthack.di.users

import dagger.Subcomponent
import ru.ftl.besthack.view.krop.presenter.KropPresenter
import ru.ftl.besthack.view.menu.presenter.UserMenuPresenter
import ru.ftl.besthack.view.profile_add.presenter.AddUserPresenter
import ru.ftl.besthack.view.splash.presenter.SplashPresenter

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

@Subcomponent(modules = [UsersModule::class])
@UsersScope
interface UsersComponent {
    fun inject(presenter: UserMenuPresenter)
    fun inject(presenter: KropPresenter)
    fun inject(presenter: SplashPresenter)
    fun inject(presenter: AddUserPresenter)
}