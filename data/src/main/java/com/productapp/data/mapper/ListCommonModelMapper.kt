package com.productapp.data.mapper

import com.product.common.mapper.DataToCommonModelMapper
import com.product.common.model.list.ListProductsModel
import com.product.common.model.list.ListModel
import com.productapp.data.model.ListProducts
import com.productapp.data.model.ListResponse
import javax.inject.Inject

class ListCommonModelMapper @Inject constructor() : DataToCommonModelMapper<ListResponse, ListModel> {
    override fun mapToCommonModel(responseModel: ListResponse?): ListModel {
        val domainList = responseModel?.listResponse?.map { responseToCommonProduct(it) }
        return ListModel(
            productList = domainList,
            productLimit = responseModel?.productLimit,
            totalCount = responseModel?.totalCount
        )
    }

    private fun responseToCommonProduct(response: ListProducts): ListProductsModel {
        return ListProductsModel(
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