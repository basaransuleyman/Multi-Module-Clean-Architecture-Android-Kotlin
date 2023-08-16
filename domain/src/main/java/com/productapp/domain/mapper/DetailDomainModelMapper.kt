package com.productapp.domain.mapper

import com.product.common.extensions.DataToDomainModelMapper
import com.productapp.domain.model.detail.DetailModel
import com.productapp.domain.model.detail.OtherProducts
import com.productapp.data.model.DetailResponse
import com.productapp.data.model.OtherProduct
import javax.inject.Inject

class DetailDomainModelMapper @Inject constructor() :
    DataToDomainModelMapper<DetailResponse, DetailModel> {
    override fun mapToDomainModel(responseModel: DetailResponse?): DetailModel {
        val detailResponse = requireNotNull(responseModel)
        return DetailModel(
            productImage = detailResponse.productImage,
            productName = detailResponse.productName,
            productId = detailResponse.productId,
            subText = detailResponse.subText,
            review = detailResponse.review ?: "",
            questions = detailResponse.questions ?: "",
            share = detailResponse.share,
            otherProducts = dataProductsToDomainProducts(detailResponse.otherProducts),
            productOptions = detailResponse.productOptions
        )
    }

    private fun dataProductsToDomainProducts(otherProducts: List<OtherProduct>?): List<OtherProducts>? {
        return otherProducts?.map { responseProduct ->
            OtherProducts(
                productImage = responseProduct.productImage ?: "",
                productName = responseProduct.productName ?: "",
                subText = responseProduct.subText ?: ""
            )
        }
    }

}