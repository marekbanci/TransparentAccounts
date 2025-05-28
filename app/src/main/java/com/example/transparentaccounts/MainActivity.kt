package com.example.transparentaccounts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.transparentaccounts.presentation.ui.ListUI
import com.example.transparentaccounts.ui.theme.TransparentAccountsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TransparentAccountsTheme {
                Navigation()
            }
        }
    }
}

