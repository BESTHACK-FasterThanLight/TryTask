package ru.ftl.besthack.view.profile_add.ui

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
interface IAddUserView : MvpView {
    fun setUser(userModel: UserModel)
    fun onError(resId: Int)
    fun onLoading(visible: Boolean)
    fun finishForResult()
}