package com.productapp.presentation.common.extension

fun String?.notNullOrEmpty(): Boolean {
    return this?.isNotEmpty() ?: false
}