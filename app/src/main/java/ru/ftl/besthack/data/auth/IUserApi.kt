package ru.ftl.besthack.data.auth

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Nikita Kulikov <nikita@kulikof.ru>
 * @project BestHack
 * @date 07.04.18
 */
interface IUserApi {
    @GET("team.json")
    fun getUsers(): Observable<List<UserModel>>
}