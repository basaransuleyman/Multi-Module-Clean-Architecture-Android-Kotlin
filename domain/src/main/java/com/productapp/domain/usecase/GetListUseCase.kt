package com.productapp.domain.usecase

import com.product.common.base.BaseUseCase
import com.product.common.di.IoDispatcher
import com.product.common.extensions.useCaseHandler
import com.product.common.mapper.CommonConverterModel
import com.product.common.model.list.ListModel
import com.product.common.utils.Resource
import com.productapp.domain.repository.ListRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val getDetailRepository: ListRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<GetListUseCase.Param, CommonConverterModel<ListModel>>(dispatcher) {
    override suspend fun getExecutable(params: Param): Resource<CommonConverterModel<ListModel>> =
        useCaseHandler {
            with(getDetailRepository) {
                when (params.page) {
                    PAGING_NUMBER_DEFAULT -> getList1()
                    PAGING_NUMBER_SECOND -> getList2()
                    else -> getList1()
                }
            }
        }

    data class Param(
        val page: Int?
    )

    companion object {
        const val PAGING_NUMBER_DEFAULT = 1
        const val PAGING_NUMBER_SECOND = 2
    }
}
