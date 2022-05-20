package com.clean.domain_layer.repository.productDetailsRepository

import com.clean.domain_layer.model.productDetails.ProductDetailsRootModel
import com.clean.domain_layer.model.state.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductDetailsRepository {
    fun getProductDetails(
        productId: Int
    ): Flow<Response<ProductDetailsRootModel>>
}