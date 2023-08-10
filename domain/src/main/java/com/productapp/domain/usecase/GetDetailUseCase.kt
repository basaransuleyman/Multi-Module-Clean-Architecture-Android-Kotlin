package com.productapp.domain.usecase

import com.product.common.base.BaseUseCase
import com.product.common.di.IoDispatcher
import com.product.common.extensions.useCaseHandler
import com.product.common.mapper.UIConverterModel
import com.product.common.model.detail.DetailUIModel
import com.product.common.utils.Resource
import com.productapp.domain.repository.DetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val getDetailRepository: DetailRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, UIConverterModel<DetailUIModel>>(dispatcher) {
    override suspend fun getExecutable(params: Unit): Resource<UIConverterModel<DetailUIModel>> =
        useCaseHandler {
            getDetailRepository.getDetail()
        }
}
