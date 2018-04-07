package ru.ftl.besthack.repositories.image

import android.graphics.Bitmap
import io.reactivex.Single
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