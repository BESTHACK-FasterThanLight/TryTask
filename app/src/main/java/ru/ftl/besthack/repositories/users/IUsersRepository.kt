package ru.ftl.besthack.repositories.users

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

interface IUsersRepository {
    fun getToken(): String?
}