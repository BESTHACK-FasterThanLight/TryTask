package ru.ftl.besthack.repositories.users

import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import ru.ftl.besthack.data.auth.IUserApi
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.data.db.AppDatabase

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class UsersRepository(appDatabase: AppDatabase,
                      val sharedPreferences: SharedPreferences, val gson: Gson,
                      retrofit: Retrofit) : IUsersRepository {
    val userDao = appDatabase.getUserDao()
    val userApi = retrofit.create(IUserApi::class.java)

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
            val model = gson.fromJson(sharedPreferences.getString("draft", "{}"), UserModel::class.java)
            return@fromCallable model
        }.subscribeOn(Schedulers.io())
    }

    override fun loadServerUsers(): Observable<UserModel> {
        return userApi.getUsers()
                .subscribeOn(Schedulers.io())
                .flatMap { Observable.fromIterable(it) }
    }
}