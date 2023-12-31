package com.productapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.product.common.extensions.onFailure
import com.product.common.extensions.onSuccess
import com.productapp.domain.model.detail.DetailModel
import com.productapp.domain.usecase.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailSharedViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase
) : ViewModel() {

    private val _detailFlow = MutableSharedFlow<DetailModel?>(extraBufferCapacity = 1)
    val detailFlow = _detailFlow.asSharedFlow()

    fun getDetail() {
        viewModelScope.launch {
            getDetailUseCase().onSuccess { detailData ->
                _detailFlow.emit(detailData)
            }.onFailure {
            }
        }
    }
}