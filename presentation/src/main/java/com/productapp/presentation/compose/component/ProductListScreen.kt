package com.productapp.presentation.compose.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.productapp.presentation.compose.ListLazyColumnViewModel
import com.productapp.presentation.compose.UIEvent

@Composable
fun GetProductList(
    viewModel: ListLazyColumnViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect {
            when (it) {
                is UIEvent.OnProductClick -> {
                    Toast.makeText(context, it.productName, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            columns = GridCells.Adaptive(150.dp),
            content = {
                viewState.lazyColumnList?.let {
                    items(viewState.lazyColumnList!!) { item ->
                        Box(
                            modifier = Modifier
                                .padding(12.dp)
                        ) {
                            ProductCard(item.productImage, item.text) {
                                viewModel.onIntent(UIEvent.OnProductClick(item.text))
                            }
                        }
                    }
                }
            }
        )
    }
}