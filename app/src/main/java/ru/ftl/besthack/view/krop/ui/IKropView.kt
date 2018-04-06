package ru.ftl.besthack.view.krop.ui

import android.graphics.Bitmap
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.04.18
 */

@StateStrategyType(AddToEndSingleStrategy::class)
interface IKropView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun requestAndOpenPicker()

    fun finish()
    fun setBitmap(bitmap: Bitmap)
    fun showLoading(isVisible: Boolean)
}