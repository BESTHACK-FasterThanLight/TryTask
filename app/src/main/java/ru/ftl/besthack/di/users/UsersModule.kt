package ru.ftl.besthack.di.users

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.ftl.besthack.data.db.AppDatabase
import ru.ftl.besthack.interactor.users.UsersInteractor
import ru.ftl.besthack.interactor.users.IUsersInteractor
import ru.ftl.besthack.repositories.users.UsersRepository
import ru.ftl.besthack.repositories.users.IUsersRepository

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
    fun provideRepository(sharedPreferences: SharedPreferences, appDatabase: AppDatabase): IUsersRepository {
        return UsersRepository(sharedPreferences, appDatabase)
    }


    @Provides
    @UsersScope
    fun provideInteractor(usersRepository: IUsersRepository): IUsersInteractor {
        return UsersInteractor(usersRepository)
    }
}