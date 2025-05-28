package com.example.transparentaccounts.domain.usecase

import com.example.transparentaccounts.data.network.TransparentAccountsAPI
import com.example.transparentaccounts.data.network.response.TransparentAccountsResp
import javax.inject.Inject

class AccountsUseCase @Inject constructor(
    private val api: TransparentAccountsAPI
) {
    suspend fun fetchAccounts(page: Int, size: Int) : TransparentAccountsResp {
        return api.getTransparentAccounts(size, page)
    }
}