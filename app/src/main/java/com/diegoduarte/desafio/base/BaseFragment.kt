package com.diegoduarte.desafio.base

import android.os.Bundle
import android.util.Log
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

abstract class BaseFragment<T>: DaggerFragment() {

    abstract var dataBiding: T?

    // Create the base activit instance and content view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    // get the base presenter
    abstract fun getPresenter(): BasePresenter

    override fun onDestroyView() {
        dataBiding = null
        getPresenter().onDestroy()
        Log.d("BaseFragment", "Destroy")
        super.onDestroyView()
    }
}