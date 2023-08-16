package com.productapp.domain.usecase

import com.product.common.base.BaseUseCase
import com.product.common.di.IoDispatcher
import com.productapp.domain.model.list.ListModel
import com.product.common.utils.Resource
import com.productapp.domain.mapper.ListDomainModelMapper
import com.productapp.data.repository.ListRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val getListRepository: ListRepository,
    private val getListMapper: ListDomainModelMapper,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<GetListUseCase.Param, ListModel>(dispatcher) {
    override suspend fun getExecutable(params: Param): Resource<ListModel> {

        val page = params.page ?: PAGING_NUMBER_DEFAULT

        val resource = if (page == PAGING_NUMBER_DEFAULT) {
            getListRepository.getList1()
        } else {
            getListRepository.getList2()
        }

        return when (resource) {
            is Resource.Success -> {
                Resource.Success(getListMapper.mapToDomainModel(resource.data))
            }

            is Resource.Failure -> {
                Resource.Failure(resource.error)
            }
        }
    }

    data class Param(
        val page: Int?
    )

    companion object {
        const val PAGING_NUMBER_DEFAULT = 1
    }
}
