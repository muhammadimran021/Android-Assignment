package com.clean.data_layer.remote

import com.clean.domain_layer.model.productCategory.ProductsCategoryRootModel
import com.clean.domain_layer.model.productDetails.ProductDetailsRootModel
import com.clean.domain_layer.model.state.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface WebService {


    @GET("products")
    suspend fun getProducts(
        @Header("Authorization") auth: String?,
        @Query("category_id") categoryId : Int
    ): Response<ProductsCategoryRootModel>



    @GET("product/{product_id}")
    suspend fun getProductDetails(
        @Header("Authorization") auth: String?,
        @Path("product_id") productId : Int
    ): Response<ProductDetailsRootModel>
}