package ru.ftl.besthack.repositories.auth

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

interface IAuthRepository {
    fun getToken(): String?
}