package com.clean.domain_layer.model.productDetails

data class ProductDetailsModel(
    val attributes: List<Attribute>,
    val attributes_options: List<AttributesOption>,
    val base_price: Int,
    val brand_name: String,
    val category: List<String>,
    val custom_question: Any,
    val custom_title: Any,
    val delivery: String,
    val experience_info: List<ExperienceInfo>,
    val final_price_base: Int,
    val final_price_sale: Int,
    val google_place_id: String,
    val images: List<String>,
    val images_low: List<ImagesLow>,
    val images_web: List<Any>,
    val images_web_low: List<Any>,
    val in_wishlist: Int,
    val info: List<Info>,
    val is_custom_message: Int,
    val is_gc: Int,
    val is_new: Any,
    val is_sap: Int,
    val map_category: List<MapCategory>,
    val name: String,
    val on_discount: Int,
    val percent_off: Int,
    val product_id: Int,
    val product_slug: String,
    val product_type: Int,
    val products_description: String,
    val sale_price: Int,
    val shareable_link: String,
    val shipping_time: String,
    val short_description: String,
    val size_and_fit: Any,
    val size_and_fit_image: Any,
    val suggested: List<Suggested>,
    val thumbnail_url: String,
    val vendor_name: String,
    val videos: List<Any>,
    val wrapping_methods: List<Any>
)