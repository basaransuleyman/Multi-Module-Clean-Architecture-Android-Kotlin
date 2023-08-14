package com.productapp.presentation.compose.uievent

sealed class UIEvent {
    data class OnToastShow(val productName: String) : UIEvent()
}