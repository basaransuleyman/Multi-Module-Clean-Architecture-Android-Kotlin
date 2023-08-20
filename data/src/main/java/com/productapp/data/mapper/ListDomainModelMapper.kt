package com.productapp.data.mapper

import com.product.common.extensions.DataToDomainModelMapper
import com.productapp.domain.model.list.ListProductsModel
import com.productapp.domain.model.list.ListModel
import com.productapp.data.model.ListProducts
import com.productapp.data.model.ListResponse
import javax.inject.Inject

class ListDomainModelMapper @Inject constructor() :
    DataToDomainModelMapper<ListResponse, ListModel> {
    override fun mapToDomainModel(responseModel: ListResponse?): ListModel {
        val domainList = responseModel?.listResponse?.map { responseToDomainProduct(it) }
        return ListModel(
            productList = domainList,
            productLimit = responseModel?.productLimit,
            totalCount = responseModel?.totalCount
        )
    }

    private fun responseToDomainProduct(response: ListProducts): ListProductsModel {
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