package ru.ftl.besthack.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import ru.ftl.besthack.data.auth.UserModel

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 28.03.18
 */

@Dao
abstract class UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(list: List<UserModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(user: UserModel): Long

    @Query("SELECT * FROM ${UserModel.TABLE_NAME}")
    abstract fun getUsers(): Flowable<List<UserModel>>
}