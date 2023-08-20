package com.productapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.product.common.extensions.onFailure
import com.product.common.extensions.onSuccess
import com.product.sideapp.home.R
import com.productapp.domain.model.home.HomeRouteData
import com.productapp.domain.model.home.HomeSectionAdapterItem
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

@HiltViewModel
class HomeSharedViewModel @Inject constructor(
    private val getHomeUseCase: GetHomeUseCase
) : ViewModel() {

    private val _homeFlow = MutableStateFlow<GetHomeEvents>(GetHomeEvents.Idle)
    val homeFlow: StateFlow<GetHomeEvents> = _homeFlow

    private val _sectionList = MutableStateFlow<List<HomeSectionAdapterItem>?>(null)
    val sectionList = _sectionList.asStateFlow()

    fun getHome() {
        viewModelScope.launch {
            getHomeUseCase().onSuccess { homeData ->
                _homeFlow.value = GetHomeEvents.StartShimmer
                delay(3000) //Backend Response delay
                _homeFlow.value = GetHomeEvents.Idle
                _homeFlow.value = GetHomeEvents.Success(
                    homeData = homeData
                )
                _sectionList.value = homeData.sections
            }.onFailure {
                _homeFlow.value = GetHomeEvents.Failure(IOException())
            }
        }
    }

    fun handleBannerRouteId(route: String, navController: NavController) {
        when (route) {
            HomeRouteData.ROUTE_TO_DETAIL.routeID -> {
                navController.navigate(R.id.detailFragment)
            }

            HomeRouteData.ROUTE_TO_LIST.routeID -> {
                navController.navigate(R.id.listFragment)
            }

            HomeRouteData.ROUTE_TO_COMPOSE_MULTI_TYPE_LAZY_COLUMN.routeID -> {
                navController.navigate(R.id.listLazyColumn)
            }
        }
    }

}