package ru.ftl.besthack.di.users

import android.content.Context
import dagger.Module
import dagger.Provides
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
    fun provideRepository(appDatabase: AppDatabase): IUsersRepository {
        return UsersRepository(appDatabase)
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