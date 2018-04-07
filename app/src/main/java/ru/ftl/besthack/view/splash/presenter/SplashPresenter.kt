package ru.ftl.besthack.view.splash.presenter

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.ftl.besthack.App
import ru.ftl.besthack.di.users.UsersModule
import ru.ftl.besthack.interactor.users.IUsersInteractor
import ru.ftl.besthack.view.splash.ui.ISplashView
import javax.inject.Inject

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 07.04.18
 */

@InjectViewState
class SplashPresenter : MvpPresenter<ISplashView>() {
    @Inject
    lateinit var interactor: IUsersInteractor
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        App.appComponent.plus(UsersModule()).inject(this)
    }

    fun loadUsers() {
        if (!sharedPreferences.getBoolean("firstrun", true)) {
            viewState.finishLoad()
            return
        }
        interactor.loadAndSaveFirstUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sharedPreferences.edit().putBoolean("firstrun", false).apply()
                    viewState.finishLoad()
                }, {
                    viewState.finishLoad()
                })
    }
}