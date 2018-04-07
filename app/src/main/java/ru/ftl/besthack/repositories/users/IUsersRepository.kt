package ru.ftl.besthack.repositories.users

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ru.ftl.besthack.data.auth.UserModel

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

interface IUsersRepository {
    fun getUsers(): Flowable<List<UserModel>>
    fun saveUser(userModel: UserModel): Single<UserModel>
    fun saveDraft(userModel: UserModel): Single<UserModel>
    fun getDraft(): Single<UserModel>
    fun loadServerUsers(): Observable<UserModel>
}