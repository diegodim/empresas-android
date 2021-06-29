package com.diegoduarte.desafio.data.source.remote

import com.diegoduarte.desafio.data.model.Token
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class Interceptor(private val token: Token) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val builder: Request.Builder = request.newBuilder()


        builder.addHeader("access-token", token.access_token.toString())
        builder.addHeader("uid", token.uid.toString())
        builder.addHeader("client", token.client.toString())

        request = builder.build()
        return chain.proceed(request)
    }
}