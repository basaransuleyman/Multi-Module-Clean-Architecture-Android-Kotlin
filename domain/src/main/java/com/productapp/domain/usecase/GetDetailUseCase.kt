package com.productapp.domain.usecase

import com.product.common.base.BaseUseCase
import com.product.common.di.IoDispatcher
import com.productapp.domain.model.detail.DetailModel
import com.product.common.utils.Resource
import com.productapp.domain.mapper.DetailDomainModelMapper
import com.productapp.data.repository.DetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(
    private val getDetailRepository: DetailRepository,
    private val detailMapper: DetailDomainModelMapper,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, DetailModel>(dispatcher) {
    override suspend fun getExecutable(params: Unit): Resource<DetailModel> {
        return when (val resource = getDetailRepository.getDetail()) {
            is Resource.Success -> {
                Resource.Success(detailMapper.mapToDomainModel(resource.data))
            }

            is Resource.Failure -> {
                Resource.Failure(resource.error)
            }
        }
    }
}