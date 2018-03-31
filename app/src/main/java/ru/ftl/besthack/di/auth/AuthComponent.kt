package ru.ftl.besthack.di.auth

import android.support.v7.view.menu.MenuPresenter
import dagger.Subcomponent
import ru.ftl.besthack.view.menu.presenter.UserMenuPresenter

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

@Subcomponent(modules = [AuthModule::class])
@AuthScope
interface AuthComponent {
    fun inject(presenter: UserMenuPresenter)
}