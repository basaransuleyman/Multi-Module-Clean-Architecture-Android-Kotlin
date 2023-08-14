package com.productapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.product.common.extensions.onFailure
import com.product.common.extensions.onSuccess
import com.product.common.model.detail.DetailUIModel
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

    private val _detailFlow = MutableSharedFlow<DetailUIModel?>(extraBufferCapacity = 1)
    val detailFlow = _detailFlow.asSharedFlow()

    fun getDetail() {
        viewModelScope.launch {
            getDetailUseCase(Unit).onSuccess {detailData ->
                _detailFlow.emit(detailData.data)
            }.onFailure {
            }
        }
    }
}