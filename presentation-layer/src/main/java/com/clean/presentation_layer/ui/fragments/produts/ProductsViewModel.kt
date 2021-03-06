package com.clean.presentation_layer.ui.fragments.produts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.clean.domain_layer.model.productCategory.ProductCategoryData
import com.clean.domain_layer.usecase.products.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    fun getProducts(): Flow<PagingData<ProductCategoryData>> {
        return productsUseCase.invoke()
            .map { pagingData -> pagingData.map { it } }
            .cachedIn(viewModelScope)
    }

}