package com.diegoduarte.desafio.data.source.remote

import com.diegoduarte.desafio.data.model.Token
import com.diegoduarte.desafio.data.source.Repository

class RemoteRepository: Repository {

    override fun login(email: String, password: String)
    = RetrofitBuilder().createService().login(email, password)

    override fun getEnterprise(token: Token, name: String)
    = RetrofitBuilder().createService(token).getEnterprise(name)

    override fun getEnterprise(token: Token, id: Int)
            = RetrofitBuilder().createService(token).getEnterprise(id)
}