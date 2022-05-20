package com.clean.domain_layer.model.productCategory

data class ProductCategoryData(
    var base_price: Double,
    val brand_name: String,
    val category: List<String>,
    val final_price_base: Double,
    val final_price_sale: Int,
    val in_wishlist: Int,
    val invId: Int,
    val is_bundle: Int,
    val is_new: Boolean,
    val map_category: List<MapCategory>,
    val name: String,
    val on_discount: Int,
    val out_of_stock: Int,
    val percent_off: Int,
    var product_id: Int,
    val product_slug: String,
    val product_type: Int,
    val products_name: String,
    val sale_price: Double,
    val show_on_top: Int,
    val thumbnail_url: String,
    val vendor_name: String
)