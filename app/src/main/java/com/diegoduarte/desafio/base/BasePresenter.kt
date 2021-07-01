package com.diegoduarte.desafio.base

import androidx.annotation.CallSuper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BasePresenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    // Create the base presenter
    open fun onCreate() {
    }

    // Destroy the observables
    @CallSuper
    open fun onDestroy() {
        compositeDisposable.clear()
    }

    // add observable to be destroyed on future
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}