package com.clean.presentation_layer.ui.fragments.products_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clean.domain_layer.model.productDetails.ProductDetailsModel
import com.clean.domain_layer.model.productDetails.ProductDetailsRootModel
import com.clean.domain_layer.model.state.ErrorHandler
import com.clean.domain_layer.model.state.Resource
import com.clean.domain_layer.usecase.productDetails.ProductDetailsUseCase
import com.clean.domain_layer.utils.ConnectivityException
import com.clean.domain_layer.utils.NetworkConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productDetailsUseCase: ProductDetailsUseCase,
    private val networkConnectivity: NetworkConnectivity
) : ViewModel() {


    private val _flow = MutableStateFlow<Resource<ProductDetailsModel>>(Resource.Loading(null))
    val flow: StateFlow<Resource<ProductDetailsModel>> = _flow

    fun getProductsDetails(
        productId: Int
    ) {
        viewModelScope.launch {
            if (!networkConnectivity.isConnected()) {
                _flow.emit(Resource.DataError(ConnectivityException()))
            } else {
                productDetailsUseCase(productId)
                    .catch { e ->
                        _flow.emit(Resource.DataError(Exception(e.toString())))
                    }.collectLatest {
                        _flow.emit(it)
                    }
            }
        }
    }
}