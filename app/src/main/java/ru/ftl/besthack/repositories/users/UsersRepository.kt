package ru.ftl.besthack.repositories.users

import android.content.SharedPreferences
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.data.db.AppDatabase

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class UsersRepository(appDatabase: AppDatabase) : IUsersRepository {
    val userDao = appDatabase.getUserDao()

    override fun getUsers(): Flowable<List<UserModel>> {
        return userDao.getUsers()
                .subscribeOn(Schedulers.io())
    }

    override fun saveUser(userModel: UserModel): Single<UserModel> {
        return Single.fromCallable {
            userModel.id = userDao.insert(userModel)
            return@fromCallable userModel
        }.subscribeOn(Schedulers.io())
    }

}