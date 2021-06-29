package com.diegoduarte.desafio.base

import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getContent())
    }

    abstract fun getContent(): Int

    abstract fun getPresenter(): BasePresenter

    override fun onDestroy() {
        getPresenter().onDestroy()
        cacheDir.deleteRecursively()
        super.onDestroy()
    }
}