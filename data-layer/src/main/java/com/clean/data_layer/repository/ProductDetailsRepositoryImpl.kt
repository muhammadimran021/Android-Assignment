package com.clean.data_layer.repository

import com.clean.data_layer.BuildConfig
import com.clean.data_layer.di.IoDispatcher
import com.clean.data_layer.remote.WebService
import com.clean.domain_layer.model.productDetails.ProductDetailsRootModel
import com.clean.domain_layer.model.state.Resource
import com.clean.domain_layer.model.state.Result
import com.clean.domain_layer.repository.productDetailsRepository.ProductDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class ProductDetailsRepositoryImpl @Inject constructor(
    private val webService: WebService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ProductDetailsRepository {

    override fun getProductDetails(
        productId: Int
    ): Flow<Response<ProductDetailsRootModel>> {
        return flow {
            emit(webService.getProductDetails(BuildConfig.AUTH_TOKEN, productId))
        }.flowOn(dispatcher)
    }

}