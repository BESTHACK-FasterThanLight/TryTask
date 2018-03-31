package ru.ftl.besthack.repositories.auth

import android.content.SharedPreferences
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import ru.ftl.besthack.data.auth.UserApi
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.data.db.AppDatabase

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class AuthRepository(private val sharedPreferences: SharedPreferences,
                     appDatabase: AppDatabase) : IAuthRepository {
    val userDao = appDatabase.getUserDao()

    override fun getToken(): String? {
        val token = sharedPreferences.getString("auth_token", "")

        return if (token.isNullOrEmpty()) {
            null
        } else token
    }

    private fun putToken(token: String) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }
}