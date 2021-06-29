package com.diegoduarte.desafio.data.source.remote

import com.diegoduarte.desafio.data.source.Repository

class RemoteRepository: Repository {

    override fun login(email: String, password: String)  = RetrofitBuilder().createService().login(email, password)

}