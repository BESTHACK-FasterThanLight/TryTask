package ru.ftl.besthack.data.auth

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

@Entity(tableName = UserModel.TABLE_NAME)
class UserModel(var name: String = "",
                var surname: String = "",
                var middlename: String = "",
                var imageUrl: String = "",
                var group: String = "",
                var about: String = "",
                @PrimaryKey(autoGenerate = true)
                var id: Long = 0) : Parcelable {
    constructor() : this("", "", "", "", "", "", 0)
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(middlename)
        parcel.writeString(imageUrl)
        parcel.writeString(group)
        parcel.writeString(about)
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        const val TABLE_NAME = "user"

        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }
}