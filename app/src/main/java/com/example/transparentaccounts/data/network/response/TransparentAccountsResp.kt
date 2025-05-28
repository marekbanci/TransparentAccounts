package com.example.transparentaccounts.data.network.response

import com.example.transparentaccounts.domain.model.TransparentAccount

data class TransparentAccountsResp(
    val pageNumber: Int,
    val pageCount: Int,
    val nextPage: Int,
    val pageSize: Int,
    val accounts: List<TransparentAccount>
)