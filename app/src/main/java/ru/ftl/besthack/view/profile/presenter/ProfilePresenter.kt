package ru.ftl.besthack.view.profile.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.view.profile.ui.IProfileView

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 07.04.18
 */

@InjectViewState
class ProfilePresenter : MvpPresenter<IProfileView>() {
    private var userModel: UserModel? = null

    fun loadUser(userModel: UserModel?) {
        this.userModel = userModel ?: this.userModel
        viewState.setUser(this.userModel!!)
    }
}