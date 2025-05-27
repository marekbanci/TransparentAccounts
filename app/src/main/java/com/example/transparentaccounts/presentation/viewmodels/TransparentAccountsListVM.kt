package com.example.transparentaccounts.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.transparentaccounts.data.network.TransparentAccountsAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransparentAccountsListVM @Inject constructor(private val api: TransparentAccountsAPI) :
    ViewModel() {
    fun fetchData() {
        viewModelScope.launch {
            api.getTransparentAccounts()
        }
    }
}