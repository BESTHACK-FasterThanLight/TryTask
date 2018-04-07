package ru.ftl.besthack.interactor.users

import android.graphics.Bitmap
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ru.ftl.besthack.BuildConfig
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.repositories.image.IImageRepository
import ru.ftl.besthack.repositories.users.IUsersRepository

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class UsersInteractor(private val usersRepository: IUsersRepository, private val imageRepository: IImageRepository) : IUsersInteractor {
    override fun getUsers(): Flowable<List<UserModel>> {
        return usersRepository.getUsers()
    }

    override fun saveUser(userModel: UserModel, bitmap: Bitmap?): Completable {
        return usersRepository.saveUser(userModel) // Сохраняем в БД и заодно получаем id пользователя
                .flatMap { usr ->
                    if (bitmap != null) {
                        return@flatMap saveUserImage(usr, bitmap)
                    }
                    return@flatMap Single.just(usr)
                }
                .toCompletable()
    }

    private fun saveUserImage(userModel: UserModel, bitmap: Bitmap): Single<UserModel>? {
        // Сохраняем аву на локальный сторадж
        return imageRepository.saveImage(bitmap, userModel.id.toString())
                .map {
                    userModel.imageUrl = it.absolutePath
                    return@map userModel
                }
                .flatMap { usersRepository.saveUser(userModel) } // А теперь сохраняем в БД уже с путем к картинкам
    }

    override fun saveDraft(userModel: UserModel, bitmap: Bitmap?): Completable {
        userModel.id = -1
        var fileSingle = Single.just(userModel)
        if (bitmap != null) {
            fileSingle = imageRepository.saveImage(bitmap, userModel.id.toString()).map {
                userModel.imageUrl = it.absolutePath
                return@map userModel
            }
        }
        return fileSingle.flatMap {
            return@flatMap usersRepository.saveDraft(it)
        }.toCompletable()
    }

    override fun getDraft(): Single<UserModel> {
        return usersRepository.getDraft()
    }

    override fun loadAndSaveFirstUser(): Completable {
        return usersRepository.loadServerUsers()
                .flatMapSingle { user ->
                    imageRepository.loadImageFromServer("${BuildConfig.STATIC_URL}${user.imageUrl}").map { user to it }
                }
                .flatMapCompletable { saveUser(it.first, it.second) }
    }
}