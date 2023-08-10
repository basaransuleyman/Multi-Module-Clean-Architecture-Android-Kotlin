package com.productapp.data.mapper

import com.product.common.mapper.DataToUIModelMapper
import com.product.common.model.list.ListProductsUIModel
import com.product.common.model.list.ListUIModel
import com.productapp.data.model.ListProducts
import com.productapp.data.model.ListResponse
import javax.inject.Inject

//TODO: Unit Test
class ListUIMapper @Inject constructor() : DataToUIModelMapper<ListResponse, ListUIModel> {
    override fun mapToUIModel(responseModel: ListResponse?): ListUIModel {
        val domainList = responseModel?.listResponse?.map { fromResponseToDomain(it) }
        return ListUIModel(
            productList = domainList,
            productLimit = responseModel?.productLimit,
            totalCount = responseModel?.totalCount
        )
    }

    private fun fromResponseToDomain(response: ListProducts): ListProductsUIModel {
        return ListProductsUIModel(
            productId = response.productId,
            productImage = response.productImage,
            text = response.text,
            subText = response.subText,
            review = response.review,
            questions = response.questions,
            rating = response.rating
        )
    }
}