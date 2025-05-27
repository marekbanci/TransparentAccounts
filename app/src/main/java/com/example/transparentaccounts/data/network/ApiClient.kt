package com.example.transparentaccounts.data.network

import com.example.transparentaccounts.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    @Singleton
    @Provides
    fun provideApi(): TransparentAccountsAPI {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


        val authInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("WEB-API-key", BuildConfig.API_KEY)
                .build()
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TransparentAccountsAPI::class.java)
    }
}