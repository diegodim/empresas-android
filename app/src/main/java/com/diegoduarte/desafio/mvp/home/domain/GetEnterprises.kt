package com.diegoduarte.desafio.mvp.home.domain

import com.diegoduarte.desafio.base.UseCase
import com.diegoduarte.desafio.data.model.Enterprises
import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import retrofit2.Response

class GetEnterprises(val repository: Repository, mThreadExecutor: Scheduler, mPostExecutionThread: Scheduler) :
    UseCase<Response<Enterprises>, GetEnterprises.Params>(mThreadExecutor, mPostExecutionThread) {



    override fun buildUseCaseObservable(params: Params?): Observable<Response<Enterprises>> {
        return repository.getEnterprise(params!!.token, params.name)
    }

    data class Params constructor(val token: Token, val name: String)

}