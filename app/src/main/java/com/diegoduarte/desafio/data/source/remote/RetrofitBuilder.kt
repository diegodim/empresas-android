package com.diegoduarte.desafio.data.source.remote

import com.diegoduarte.desafio.data.model.Token
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    private val baseUrl = "https://empresas.ioasys.com.br/api/v1/"

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(buildClient())
            .baseUrl(baseUrl)
            .build()

    }


    private fun buildClient(): OkHttpClient {

        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
        builder.retryOnConnectionFailure(true)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addNetworkInterceptor(interceptor)

        //builder.addInterceptor(Interceptor(""))

        return builder.build()
    }

    fun createService(): ApiService {
        return buildRetrofit().create(ApiService::class.java)
    }

    fun createService( token: Token): ApiService {
        val newClient: OkHttpClient = buildClient()
            .newBuilder().
        addInterceptor(Interceptor(token))
            .build()
        val newRetrofit: Retrofit = buildRetrofit()
            .newBuilder()
            .client(newClient)
            .build()
        return newRetrofit.create(ApiService::class.java)
    }



}