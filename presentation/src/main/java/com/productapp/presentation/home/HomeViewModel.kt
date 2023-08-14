package com.productapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.product.common.extensions.onFailure
import com.product.common.extensions.onSuccess
import com.product.common.model.home.HomeSectionAdapterItem
import com.productapp.domain.usecase.GetHomeUseCase
import com.productapp.presentation.home.uievents.GetHomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

//TODO: Unit Test
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase
) : ViewModel() {

    private val _homeFlow = MutableStateFlow<GetHomeEvents>(GetHomeEvents.Idle)
    val homeFlow: StateFlow<GetHomeEvents> = _homeFlow

    private val _sectionList = MutableStateFlow<List<HomeSectionAdapterItem>?>(null)
    val sectionList = _sectionList.asStateFlow()

    fun getHome() {
        viewModelScope.launch {
            getHomeUseCase(Unit)
                .onSuccess { homeData ->
                    _homeFlow.value = GetHomeEvents.StartShimmer
                    delay(3000) //Backend Response delay
                    homeData.data?.let {
                        _homeFlow.value = GetHomeEvents.Idle
                        _homeFlow.value = GetHomeEvents.Success(
                            homeData = it
                        )

                        _sectionList.value = it.sections
                    }
                }
                .onFailure {
                    _homeFlow.value = GetHomeEvents.Failure(IOException())
                }
        }
    }



}