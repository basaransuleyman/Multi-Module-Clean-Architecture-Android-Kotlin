package com.productapp.data.mapper

import com.product.common.mapper.DataToCommonModelMapper
import com.product.common.model.detail.DetailModel
import com.product.common.model.detail.OtherProducts
import com.productapp.data.model.DetailResponse
import com.productapp.data.model.OtherProduct
import javax.inject.Inject

class DetailCommonModelMapper @Inject constructor() : DataToCommonModelMapper<DetailResponse, DetailModel> {
    override fun mapToCommonModel(responseModel: DetailResponse?): DetailModel {
        val detailResponse = requireNotNull(responseModel)
        return DetailModel(
            productImage = detailResponse.productImage,
            productName = detailResponse.productName,
            productId = detailResponse.productId,
            subText = detailResponse.subText,
            review = detailResponse.review ?: "",
            questions = detailResponse.questions ?: "",
            share = detailResponse.share,
            otherProducts = responseProductsToCommonProducts(detailResponse.otherProducts),
            productOptions = detailResponse.productOptions
        )
    }

    private fun responseProductsToCommonProducts(otherProducts: List<OtherProduct>?): List<OtherProducts>? {
        return otherProducts?.map { responseProduct ->
            OtherProducts(
                productImage = responseProduct.productImage ?: "",
                productName = responseProduct.productName ?: "",
                subText = responseProduct.subText ?: ""
            )
        }
    }

}