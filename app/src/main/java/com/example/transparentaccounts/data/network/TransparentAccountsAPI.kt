package com.example.transparentaccounts.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TransparentAccountsAPI {

    @GET("transparentAccounts")
    suspend fun getTransparentAccounts(
        @Query("size") perPage: Int = 25,
        @Query("page") cursor: Int = 0
    )
}