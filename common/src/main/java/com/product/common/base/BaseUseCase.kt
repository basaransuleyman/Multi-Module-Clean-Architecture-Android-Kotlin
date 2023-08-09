package com.product.common.base

import com.product.common.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class BaseUseCase<in Params, Type> constructor(
    private val dispatcher: CoroutineDispatcher
) {
    abstract suspend fun getExecutable(params: Params): Resource<Type>

    suspend operator fun invoke(params: Params): Resource<Type> {
        return withContext(dispatcher) {
            getExecutable(params)
        }
    }
}