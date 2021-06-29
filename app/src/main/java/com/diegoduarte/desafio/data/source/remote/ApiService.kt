package com.diegoduarte.desafio.data.source.remote

import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.model.Token
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

import retrofit2.http.POST




interface ApiService {

    @POST("users/auth/sign_in")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String?
    ): Observable<Response<LoginResponse>>
}