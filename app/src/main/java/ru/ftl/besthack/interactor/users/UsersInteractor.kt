package ru.ftl.besthack.interactor.users

import ru.ftl.besthack.repositories.users.IUsersRepository

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

class UsersInteractor(private val usersRepository: IUsersRepository) : IUsersInteractor {
    override fun getToken(): String? {
        return usersRepository.getToken()
    }
}