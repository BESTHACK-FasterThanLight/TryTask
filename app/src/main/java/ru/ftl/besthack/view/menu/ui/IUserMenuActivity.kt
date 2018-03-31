package ru.ftl.besthack.view.menu.ui

import com.arellomobile.mvp.MvpView
import ru.ftl.besthack.data.auth.UserApi

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 27.03.18
 */

interface IUserMenuActivity : MvpView {
    fun setList(users: List<UserApi>, login: String)
    fun onError()
}