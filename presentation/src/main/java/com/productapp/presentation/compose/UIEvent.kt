package com.productapp.presentation.compose

sealed class UIEvent {
    data class OnProductClick(val productName: String) : UIEvent()
}