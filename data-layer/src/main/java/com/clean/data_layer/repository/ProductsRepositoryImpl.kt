package com.clean.data_layer.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.clean.data_layer.remote.WebService
import com.clean.domain_layer.model.productCategory.ProductCategoryData
import com.clean.domain_layer.repository.produtcsRepository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val webService: WebService
) : ProductsRepository {

    override fun getPagedProducts() : Flow<PagingData<ProductCategoryData>>{
        return Pager(
            PagingConfig(pageSize = 20, prefetchDistance = 4),
            pagingSourceFactory = {
                ProductsPagingSource(webService)
            }
        ).flow
    }

}