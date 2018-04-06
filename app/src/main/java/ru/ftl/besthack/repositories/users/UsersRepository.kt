package ru.ftl.besthack.repositories.users

import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.data.db.AppDatabase

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class UsersRepository(appDatabase: AppDatabase, val sharedPreferences: SharedPreferences, val gson: Gson) : IUsersRepository {
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

    override fun saveDraft(userModel: UserModel): Single<UserModel> {
        return Single.fromCallable {
            sharedPreferences.edit().putString("draft", gson.toJson(userModel)).apply()
            userModel
        }.subscribeOn(Schedulers.io())
    }

    override fun getDraft(): Single<UserModel> {
        return Single.fromCallable {
            gson.fromJson(sharedPreferences.getString("draft", ""), UserModel::class.java)
        }.subscribeOn(Schedulers.io())
    }
}