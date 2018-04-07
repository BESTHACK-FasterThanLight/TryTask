package ru.ftl.besthack.view.profile.ui

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.ftl.besthack.data.auth.UserModel

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 07.04.18
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface IProfileView : MvpView {
    fun setUser(userModel: UserModel)
}