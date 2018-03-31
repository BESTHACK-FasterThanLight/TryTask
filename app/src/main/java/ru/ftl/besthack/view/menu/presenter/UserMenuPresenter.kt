package ru.ftl.besthack.view.menu.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.ftl.besthack.App
import ru.ftl.besthack.di.auth.AuthModule
import ru.ftl.besthack.interactor.auth.IAuthInteractor
import ru.ftl.besthack.view.menu.ui.IUserMenuActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 27.03.18
 */

@InjectViewState
class UserMenuPresenter : MvpPresenter<IUserMenuActivity>() {
    @Inject
    lateinit var authInteractor: IAuthInteractor
    private val disposable = CompositeDisposable()

    init {
        App.appComponent.plus(AuthModule()).inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadList()
    }

    fun loadList() {
        /*disposable.addAll(authInteractor.get()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.setList(it, authInteractor.getToken() ?: "")
                }, {
                    Timber.e(it)
                    viewState.onError()
                }))*/
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}