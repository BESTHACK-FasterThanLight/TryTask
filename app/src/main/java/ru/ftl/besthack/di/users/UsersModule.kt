package ru.ftl.besthack.di.users

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.ftl.besthack.data.db.AppDatabase
import ru.ftl.besthack.interactor.users.IUsersInteractor
import ru.ftl.besthack.interactor.users.UsersInteractor
import ru.ftl.besthack.repositories.image.IImageRepository
import ru.ftl.besthack.repositories.image.ImageRepository
import ru.ftl.besthack.repositories.users.IUsersRepository
import ru.ftl.besthack.repositories.users.UsersRepository

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

@Module
@UsersScope
class UsersModule {
    @Provides
    @UsersScope
    fun provideRepository(appDatabase: AppDatabase, sharedPreferences: SharedPreferences, gson: Gson, retrofit: Retrofit): IUsersRepository {
        return UsersRepository(appDatabase, sharedPreferences, gson, retrofit)
    }


    @Provides
    @UsersScope
    fun provideInteractor(usersRepository: IUsersRepository, imageRepository: IImageRepository): IUsersInteractor {
        return UsersInteractor(usersRepository, imageRepository)
    }

    @Provides
    @UsersScope
    fun provideImageRepo(context: Context): IImageRepository {
        return ImageRepository(context)
    }
}