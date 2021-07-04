package com.diegoduarte.desafio.base

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {

    // Create the base activit instance and content view
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }


    // get the base presenter
    abstract fun getPresenter(): BasePresenter

    // Dispose all observables ans clear the cash
    override fun onDestroy() {
        getPresenter().onDestroy()
        cacheDir.deleteRecursively()
        super.onDestroy()
    }
}