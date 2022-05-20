package com.clean.domain_layer.usecase.productDetails

import android.content.ContentValues.TAG
import android.util.Log
import com.clean.domain_layer.di.ConnectivityModule
import com.clean.domain_layer.model.productDetails.ProductDetailsModel
import com.clean.domain_layer.model.state.Resource
import com.clean.domain_layer.repository.productDetailsRepository.ProductDetailsRepository
import com.clean.domain_layer.utils.ConnectivityException
import com.clean.domain_layer.utils.NetworkConnectivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository
) {
    operator fun invoke(params: Int): Flow<Resource<ProductDetailsModel>> {
        val responseData = productDetailsRepository.getProductDetails(params)
        return responseData
            .map { data ->
                Log.d(TAG, "execute: ")
                if (data.isSuccessful) {
                    data.body()?.let { model ->
                        if (model.status == 200) {
                            Log.d(TAG, "execute: 200")
                            Resource.Success(model.body)
                        } else {
                            Resource.DataError(Exception(model.message))
                        }
                    } ?: run {
                        Resource.DataError(Exception(data.message()))
                    }
                } else {
                    Resource.DataError(Exception(data.message()))
                }
            }
    }
}