package ru.ftl.besthack.repositories.image

import android.content.Context
import android.graphics.Bitmap
import io.reactivex.Single
import timber.log.Timber
import java.io.File

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 05.04.18
 */

class ImageRepository(context: Context) : IImageRepository {
    val imageFile = File(context.filesDir, "image")

    override fun saveImage(bitmap: Bitmap, imagename: String): Single<File> {
        return Single.fromCallable {
            val file = File(imageFile, "$imagename.png")
            try {
                saveBitmapToFile(bitmap, file)
            } catch (e: Exception) {
                Timber.e(e)
            }
            return@fromCallable file
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap, file: File) {
        file.parentFile.mkdirs()
        file.createNewFile()
        file.outputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }

}