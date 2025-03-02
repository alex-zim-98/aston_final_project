package com.example.aston_final_project.di

import com.example.aston_final_project.data.retrofit.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @ApplicationScope
    @Provides
    fun provideAuthInterceptor() = Interceptor { chain ->
        val url = chain.request().url.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }

    @ApplicationScope
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: Interceptor
    ) =
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @ApplicationScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @ApplicationScope
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    companion object {
        private const val URL = "https://newsapi.org/"
        private const val API_KEY = "1aeba5fa0f344ef7891f6ba0ea8d4357"
    }
}