package com.clean.domain_layer.model.productCategory

data class ProductsCategoryRootModel(
    val body: ProductCategoryModel,
    val error: List<Any>,
    val exception: Any,
    val message: String,
    val status: Int,
    val update_available: Int
)