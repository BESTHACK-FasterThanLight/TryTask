package ru.ftl.besthack.view.profile_add.presenter

import android.graphics.BitmapFactory
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.ftl.besthack.App
import ru.ftl.besthack.R
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.di.users.UsersModule
import ru.ftl.besthack.interactor.users.IUsersInteractor
import ru.ftl.besthack.view.profile_add.ui.IAddUserView
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 07.04.18
 */

@InjectViewState
class AddUserPresenter : MvpPresenter<IAddUserView>() {
    @Inject
    lateinit var interactor: IUsersInteractor
    private val disposable = CompositeDisposable()

    init {
        App.appComponent.plus(UsersModule()).inject(this)
    }

    fun loadDraft() {
        disposable.addAll(interactor.getDraft().observeOn(AndroidSchedulers.mainThread()).subscribe({
            viewState.setUser(it)
        }, Timber::e))
    }

    fun saveDraft(userModel: UserModel) {
        disposable.addAll(interactor.saveDraft(userModel, null)
                .subscribe({}, Timber::e))
    }

    fun submit(userModel: UserModel) {
        if (userModel.name.isEmpty()) {
            viewState.onError(R.string.edit_error)
            return
        }
        userModel.id = 0
        viewState.onLoading(true)
        disposable.addAll(interactor.getDraft()
                .map {
                    userModel.imageUrl = it.imageUrl
                    return@map userModel
                }
                .flatMapCompletable {
                    interactor.saveUser(userModel, BitmapFactory.decodeFile(userModel.imageUrl))
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    saveDraft(UserModel())
                    viewState.setUser(UserModel())
                    viewState.onLoading(false)
                    viewState.finishForResult()
                }, {
                    Timber.e(it)
                    viewState.onLoading(false)
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}