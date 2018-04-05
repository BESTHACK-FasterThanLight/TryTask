package ru.ftl.besthack.repositories.users

import android.content.SharedPreferences
import ru.ftl.besthack.data.db.AppDatabase

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class UsersRepository(private val sharedPreferences: SharedPreferences,
                      appDatabase: AppDatabase) : IUsersRepository {
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