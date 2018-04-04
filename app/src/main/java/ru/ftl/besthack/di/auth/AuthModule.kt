package ru.ftl.besthack.di.auth

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.ftl.besthack.data.db.AppDatabase
import ru.ftl.besthack.interactor.auth.AuthInteractor
import ru.ftl.besthack.interactor.auth.IAuthInteractor
import ru.ftl.besthack.repositories.auth.AuthRepository
import ru.ftl.besthack.repositories.auth.IAuthRepository

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

@Module
@AuthScope
class AuthModule {
    @Provides
    @AuthScope
    fun provideRepository(sharedPreferences: SharedPreferences, appDatabase: AppDatabase): IAuthRepository {
        return AuthRepository(sharedPreferences, appDatabase)
    }


    @Provides
    @AuthScope
    fun provideInteractor(authRepository: IAuthRepository): IAuthInteractor {
        return AuthInteractor(authRepository)
    }
}