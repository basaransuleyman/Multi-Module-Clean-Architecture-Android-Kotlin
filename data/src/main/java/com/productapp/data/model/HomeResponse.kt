package com.productapp.data.model

import com.google.gson.annotations.SerializedName
import com.product.common.model.ResponseModel

data class HomeResponse(
    @SerializedName("sections")
    val sections: List<Section>
) : ResponseModel

data class Section(
    @SerializedName("sectionData")
    val sectionData: List<HomeSection>,
    val sectionTitle: String? = null,
    val type: Int
) : ResponseModel

data class HomeSection(
    val icon: String? = null,
    val image: String? = null,
    val navigationData: String? = null,
    val productId: String? = null,
    val productImage: String? = null,
    val questions: String? = null,
    val rating: String? = null,
    val review: String? = null,
    val share: String? = null,
    val subText: String? = null,
    val text: String? = null,
    val piece: String? = null,
    val soldOutText: String? = null
) : ResponseModel
