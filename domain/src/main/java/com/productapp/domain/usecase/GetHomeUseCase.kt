package com.productapp.domain.usecase

import com.product.common.utils.Resource
import com.product.common.extensions.useCaseHandler
import com.product.common.mapper.UIConverterModel
import com.product.common.model.home.HomeUIModel
import com.product.common.base.BaseUseCase
import com.product.common.di.IoDispatcher
import com.productapp.domain.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val getHomeRepository: HomeRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, UIConverterModel<HomeUIModel>>(dispatcher) {
    override suspend fun getExecutable(params: Unit): Resource<UIConverterModel<HomeUIModel>> =
        useCaseHandler {
            getHomeRepository.getHome()
        }
}
