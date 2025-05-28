package com.example.transparentaccounts.data.network

import com.example.transparentaccounts.data.network.response.TransparentAccountsResp
import retrofit2.http.GET
import retrofit2.http.Query

interface TransparentAccountsAPI {

    @GET("transparentAccounts")
    suspend fun getTransparentAccounts(
        @Query("size") size: Int = 50,
        @Query("page") page: Int = 0
    ) : TransparentAccountsResp
}