package com.example.transparentaccounts.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.transparentaccounts.presentation.ui.components.AccountCard
import com.example.transparentaccounts.presentation.viewmodels.TransparentAccountsListVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListUI(navController: NavController, viewModel: TransparentAccountsListVM = hiltViewModel()) {
    val accounts by viewModel.accounts.collectAsState(emptyList())
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null && lastVisibleItemIndex >= accounts.size - 1) {
                    viewModel.getNextPage()
                }
            }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Transparent Accounts") }) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            state = listState,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(accounts) { item ->
                AccountCard (item, onClick = {

                })
            }
        }
    }
}