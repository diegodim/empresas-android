package com.diegoduarte.desafio.data.source.remote

import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.data.model.Enterprises
import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.model.Token
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    // Login api interface
    @POST("users/auth/sign_in")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String?
    ): Observable<Response<LoginResponse>>

    // Search enterprise api interface
    @GET("enterprises")
    fun getEnterprise(
        @Query("name") name: String
    ): Observable<Response<Enterprises>>

    // Get a enterprise by id api interface
    @GET("enterprises/{enterprise}")
    fun getEnterprise(
        @Path("enterprise") enterpriseId: Int
    ): Observable<Response<Enterprise>>
}