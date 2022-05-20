package com.clean.domain_layer.model.productDetails

data class Suggested(
    val base_price: Double,
    val final_price_base: Double,
    val final_price_sale: Int,
    val in_wishlist: Int,
    val is_new: Boolean,
    val name: String,
    val product_id: Int,
    val product_slug: String,
    val sale_price: Double,
    val thumbnail_url: String
)