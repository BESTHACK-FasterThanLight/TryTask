package ru.ftl.besthack.interactor.auth

import io.reactivex.Single
import ru.ftl.besthack.data.auth.UserApi
import ru.ftl.besthack.data.auth.UserModel
import ru.ftl.besthack.repositories.auth.IAuthRepository

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class AuthInteractor(private val authRepository: IAuthRepository) : IAuthInteractor {
    override fun getToken(): String? {
        return authRepository.getToken()
    }
}