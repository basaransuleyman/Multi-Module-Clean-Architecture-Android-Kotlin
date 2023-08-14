package com.productapp.presentation.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.product.common.extensions.onFailure
import com.product.common.extensions.onSuccess
import com.productapp.domain.usecase.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListLazyColumnViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private var _uiEvent = MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getLazyColumnData()
    }

    fun onIntent(event: UIEvent){
        when(event){
            is UIEvent.OnProductClick ->{
                viewModelScope.launch {
                    _uiEvent.emit(
                        UIEvent.OnProductClick(
                            event.productName
                        )
                    )
                }
            }

            else -> {}
        }
    }

    fun getLazyColumnData() {
        viewModelScope.launch {
            getListUseCase(GetListUseCase.Param(1))
                .onSuccess { product ->
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                    delay(1000)
                    product.data?.productList.let { productList ->
                        _state.update {
                            it.copy(
                                isLoading = false,
                                lazyColumnList = productList
                            )
                        }
                    }
                }.onFailure {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
        }
    }
}