package com.clean.domain_layer.model.productDetails

data class ProductDetailsRootModel(
    val body: ProductDetailsModel,
    val error: List<Any>,
    val exception: Any,
    val message: String,
    val status: Int,
    val update_available: Int
)
