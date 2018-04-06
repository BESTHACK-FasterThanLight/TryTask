package ru.ftl.besthack.interactor.users

import android.graphics.Bitmap
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ru.ftl.besthack.data.auth.UserModel

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

interface IUsersInteractor {
    fun getUsers(): Flowable<List<UserModel>>
    fun saveUser(userModel: UserModel, bitmap: Bitmap?): Completable
    fun saveDraft(userModel: UserModel, bitmap: Bitmap?): Completable
    fun getDraft(): Single<UserModel>
}