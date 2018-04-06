package ru.ftl.besthack.view.krop.presenter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.esafirm.imagepicker.model.Image
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.ftl.besthack.App
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.di.users.UsersModule
import ru.ftl.besthack.interactor.users.IUsersInteractor
import ru.ftl.besthack.view.krop.ui.IKropView
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.04.18
 */

@InjectViewState
class KropPresenter : MvpPresenter<IKropView>() {
    var bitmap: Bitmap? = null
    @Inject
    lateinit var userInteractor: IUsersInteractor
    var userModel: UserModel? = null

    init {
        App.appComponent.plus(UsersModule()).inject(this)
    }

    fun loadImage() {
        if (bitmap == null) {
            viewState.requestAndOpenPicker()
            return
        }
        viewState.setBitmap(bitmap!!)
    }

    fun storeUser(userModel: UserModel?) {
        this.userModel = userModel ?: this.userModel
    }

    fun onImageLoad(img: Image?) {
        if (img == null) {
            viewState.finish()
            return
        }

        bitmap = BitmapFactory.decodeFile(img.path)
        viewState.setBitmap(bitmap!!)
    }

    fun submit(bitmap: Bitmap?) {
        if (userModel == null || bitmap == null) {
            return //TODO add error
        }
        viewState.showLoading(true)

        userInteractor.saveDraft(userModel!!, bitmap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.showLoading(false)
                    viewState.finish()
                }, {
                    viewState.showLoading(false)
                    Timber.e(it)
                })
    }
}