package com.productapp.presentation.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.productapp.presentation.compose.component.GetProductList
import com.productapp.presentation.compose.ui.theme.ProductAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListLazyColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductAppTheme() {
                GetProductList()
            }
        }
    }
}