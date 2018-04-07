package ru.ftl.besthack.repositories.image

import android.graphics.Bitmap
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.ftl.besthack.data.auth.UserModel
import java.io.File

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 05.04.18
 */

interface IImageRepository {
    fun saveImage(bitmap: Bitmap, tryimagename: String): Single<File>
    fun loadImageFromServer(url: String): Single<Bitmap>
}