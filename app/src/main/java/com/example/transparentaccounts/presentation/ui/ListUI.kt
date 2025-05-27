package com.example.transparentaccounts.presentation.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transparentaccounts.presentation.viewmodels.TransparentAccountsListVM

@Composable
fun ListUI(viewModel: TransparentAccountsListVM = hiltViewModel()) {
    viewModel.fetchData()
}