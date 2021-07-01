package com.diegoduarte.desafio.data.source.remote

import com.diegoduarte.desafio.data.model.Token
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitBuilder {

    private val baseUrl = "https://empresas.ioasys.com.br/api/v1/"

    // Build retrofit with a Gson converter
    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(buildClient())
            .baseUrl(baseUrl)
            .build()

    }

    // Build a OkHttp client
    private fun buildClient(): OkHttpClient {

        val builder:OkHttpClient.Builder = OkHttpClient.Builder()
        builder.retryOnConnectionFailure(true)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addNetworkInterceptor(interceptor)

        builder.connectTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)

        return builder.build()
    }

    // Create a service without Auth
    fun createService(): ApiService {
        return buildRetrofit().create(ApiService::class.java)
    }

    // Create a service with Auth
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