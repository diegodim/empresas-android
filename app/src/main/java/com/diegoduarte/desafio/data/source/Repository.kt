package com.diegoduarte.desafio.data.source

import com.diegoduarte.desafio.data.model.LoginResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

interface Repository {

    fun login(email: String, password: String): Observable<Response<LoginResponse>>
}