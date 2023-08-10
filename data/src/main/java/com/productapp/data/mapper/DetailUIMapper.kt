package com.productapp.data.mapper

import com.product.common.mapper.DataToUIModelMapper
import com.product.common.model.detail.DetailUIModel
import com.product.common.model.detail.OtherProducts
import com.productapp.data.model.DetailResponse
import com.productapp.data.model.OtherProduct
import javax.inject.Inject

//TODO: Unit Test
class DetailUIMapper @Inject constructor() : DataToUIModelMapper<DetailResponse, DetailUIModel> {
    override fun mapToUIModel(responseModel: DetailResponse?): DetailUIModel {
        val detailResponse = requireNotNull(responseModel)
        return DetailUIModel(
            productImage = detailResponse.productImage,
            productName = detailResponse.productName,
            productId = detailResponse.productId,
            subText = detailResponse.subText,
            review = detailResponse.review ?: "",
            questions = detailResponse.questions ?: "",
            share = detailResponse.share,
            otherProducts = responseProductsToUIProducts(detailResponse.otherProducts),
            productOptions = detailResponse.productOptions
        )
    }

    private fun responseProductsToUIProducts(otherProducts: List<OtherProduct>?): List<OtherProducts>? {
        return otherProducts?.map { responseProduct ->
            OtherProducts(
                productImage = responseProduct.productImage ?: "",
                productName = responseProduct.productName ?: "",
                subText = responseProduct.subText ?: ""
            )
        }
    }

}