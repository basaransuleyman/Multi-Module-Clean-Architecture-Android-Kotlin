package com.productapp.domain.usecase

import com.product.common.utils.Resource
import com.productapp.domain.model.home.HomeModel
import com.product.common.base.BaseUseCase
import com.product.common.di.IoDispatcher
import com.productapp.data.repository.HomeRepository
import com.productapp.domain.mapper.HomeDomainModelMapper
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetHomeUseCase @Inject constructor(
    private val getHomeRepository: HomeRepository,
    private val homeMapper: HomeDomainModelMapper,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, HomeModel>(dispatcher) {
    override suspend fun getExecutable(params: Unit): Resource<HomeModel> {
        return when (val resource = getHomeRepository.getHome()) {
            is Resource.Success -> {
                Resource.Success(homeMapper.mapToDomainModel(resource.data))
            }
            is Resource.Failure -> {
                Resource.Failure(resource.error)
            }
        }
    }
}
