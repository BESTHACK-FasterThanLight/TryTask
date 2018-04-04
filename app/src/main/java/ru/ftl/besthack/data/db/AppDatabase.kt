package ru.ftl.besthack.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.ftl.besthack.data.auth.UserModel

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 28.03.18
 */

@Database(entities = [(UserModel::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDAO
}