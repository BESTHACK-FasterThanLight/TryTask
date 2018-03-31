package ru.ftl.besthack.data.auth

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 27.03.18
 */

@Entity(tableName = "users")
data class UserApi(
        @SerializedName("secret")
        var secret: String?,
        @PrimaryKey
        @SerializedName("login")
        var login: String) {
    constructor() : this(null, "")
}