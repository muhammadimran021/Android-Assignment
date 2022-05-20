package com.clean.domain_layer.repository.produtcsRepository

import androidx.paging.PagingData
import com.clean.domain_layer.model.productCategory.ProductCategoryData
import com.clean.domain_layer.model.productCategory.ProductsCategoryRootModel
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getPagedProducts(): Flow<PagingData<ProductCategoryData>>
}