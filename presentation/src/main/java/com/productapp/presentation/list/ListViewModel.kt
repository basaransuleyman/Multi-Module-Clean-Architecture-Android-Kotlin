package com.productapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.product.common.extensions.onFailure
import com.product.common.extensions.onSuccess
import com.productapp.domain.usecase.GetListUseCase
import com.productapp.presentation.list.uievents.GetListEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase
) : ViewModel() {

    private val _listFlow = MutableSharedFlow<GetListEvents>(replay = 1)
    val listFlow = _listFlow.asSharedFlow()

    private val _isPagingLoading = MutableStateFlow(false)
    val isPagingLoading = _isPagingLoading.asStateFlow()

    private val _currentPage = MutableStateFlow(1)
    val currentPage = _currentPage.asStateFlow()

    fun getList() {
        viewModelScope.launch {
            getListUseCase(GetListUseCase.Param(currentPage.value)).onSuccess { listData ->
                delay(1000) //Backend Response delay
                    _listFlow.emit(
                        GetListEvents.Success(
                            listData = listData
                        )
                    )
                    _currentPage.value += 1
            }.onFailure {
                _listFlow.emit(GetListEvents.Failure(IOException()))
            }
        }
    }

    fun setPagingLoading(isPagingLoading: Boolean){
        _isPagingLoading.value = isPagingLoading
    }

}