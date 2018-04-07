package ru.ftl.besthack.view.menu.ui

import com.arellomobile.mvp.MvpView
import ru.ftl.besthack.data.auth.UserModel

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 27.03.18
 */

interface IUserMenuView : MvpView {
    fun setList(users: List<UserModel>)
    fun onError()
}