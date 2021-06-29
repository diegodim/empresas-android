package com.diegoduarte.desafio.data.source

import com.diegoduarte.desafio.data.model.Enterprise
import com.diegoduarte.desafio.data.model.Enterprises
import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.model.Token
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response

interface Repository {

    fun login(email: String, password: String): Observable<Response<LoginResponse>>
    fun getEnterprise(token: Token, name: String): Observable<Response<Enterprises>>
    fun getEnterprise(token: Token, id: Int): Observable<Response<Enterprise>>
}