package ru.ftl.besthack.interactor.auth

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 06.03.18
 */

interface IAuthInteractor {
    fun getToken(): String?

}