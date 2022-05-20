package com.clean.presentation_layer.ui.fragments.products_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.clean.domain_layer.model.productDetails.ProductDetailsModel
import com.clean.domain_layer.model.state.Resource
import com.clean.presentation_layer.R
import com.clean.presentation_layer.base.BaseFragment
import com.clean.presentation_layer.databinding.ProductDetailsFragmentBinding
import com.clean.presentation_layer.utils.viewbinding.viewBinding
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.anko.support.v4.toast

class ProductDetailsFragment : BaseFragment(R.layout.product_details_fragment) {
    private val binding by viewBinding(ProductDetailsFragmentBinding::bind)
    private val viewModel: ProductDetailsViewModel by viewModels()
    private val navArgs: ProductDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeGetProductState()
        getProductDetails()
    }

    private fun observeGetProductState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.flow.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        showLoader()
                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            updateUI(data)
                        }
                        hideLoader()
                    }
                    is Resource.DataError -> {
                        toast(it.e.toString())
                        hideLoader()
                    }
                }
            }
        }
    }

    private fun updateUI(data: ProductDetailsModel) {
        addImagesInSlider(data.images,data.name)
        data.brand_name.isNotEmpty().let {
            binding.tvBrandName.text = "Brand: ${data.brand_name}"
        }
       binding.tvBrandPrice.text = "Price: ${data.final_price_sale}"
    }

    private fun addImagesInSlider(images: List<String>, name: String) {
        val imagesList = arrayListOf<SlideModel>()
        /**
         * add images in custom slide model to load images
         */
        images.forEach {
            imagesList.add(SlideModel(it, name))
        }
        binding.imgProductCover.setImageList(imagesList)
    }


    private fun getProductDetails() {
        viewModel.getProductsDetails(navArgs.productId)
    }
}