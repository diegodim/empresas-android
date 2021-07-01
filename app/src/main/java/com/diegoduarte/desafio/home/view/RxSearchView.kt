package com.diegoduarte.desafio.home.view

import com.diegoduarte.desafio.UseCase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler

class RxSearchView(val observable: Observable<String>?,mThreadExecutor: Scheduler, mPostExecutionThread: Scheduler) :
    UseCase<String, Void>(mThreadExecutor, mPostExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<String>? {
        return observable
    }
}