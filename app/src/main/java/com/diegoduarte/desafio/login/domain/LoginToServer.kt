package com.diegoduarte.desafio.login.domain

import com.diegoduarte.desafio.UseCase
import com.diegoduarte.desafio.data.model.LoginResponse
import com.diegoduarte.desafio.data.source.Repository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import retrofit2.Response

class LoginToServer(val repository: Repository,
                     mThreadExecutor: Scheduler,
                     mPostExecutionThread: Scheduler):
    UseCase<Response<LoginResponse>, LoginToServer.Params>(mThreadExecutor, mPostExecutionThread) {



    override fun buildUseCaseObservable(params: Params?): Observable<Response<LoginResponse>> {
        return repository.login(params!!.email, params.password)
    }

    class Params constructor(val email: String,
                                     val password: String ) {

        companion object {
            fun forUser(email: String, password: String): Params {
                return Params(email, password)
            }
        }
    }
}