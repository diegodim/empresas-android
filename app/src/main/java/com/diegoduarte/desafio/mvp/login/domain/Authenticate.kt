package com.diegoduarte.desafio.mvp.login.domain

import com.diegoduarte.desafio.base.UseCase
import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.source.Repository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import retrofit2.Response

class Authenticate(val repository: Repository,
                   mThreadExecutor: Scheduler,
                   mPostExecutionThread: Scheduler):
    UseCase<Response<LoginResponse>, Authenticate.Params>(mThreadExecutor, mPostExecutionThread) {



    override fun buildUseCaseObservable(params: Params?): Observable<Response<LoginResponse>> {
        return repository.login(params!!.email, params.password)
    }

    data class Params constructor(val email: String,
                                     val password: String )
}