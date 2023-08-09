package com.productapp.presentation.common.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> ViewGroup.viewBinding(
    viewBindingFactory:
        (LayoutInflater, ViewGroup, Boolean) -> T
) = viewBindingFactory.invoke(LayoutInflater.from(this.context), this, false)