package ru.ftl.besthack.di.users

import dagger.Subcomponent
import ru.ftl.besthack.view.menu.presenter.UserMenuPresenter

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

@Subcomponent(modules = [UsersModule::class])
@UsersScope
interface UsersComponent {
    fun inject(presenter: UserMenuPresenter)
}