package com.clean.domain_layer.model.productDetails

data class Option(
    val base_price: Int,
    val final_price_base: Int,
    val final_price_sale: Int,
    val images_low: List<Any>,
    val invId: Int,
    val invsku: String,
    val name: String,
    val on_discount: Int,
    val quantity: Int,
    val sale_price: Int,
    val sort_order: Int
)