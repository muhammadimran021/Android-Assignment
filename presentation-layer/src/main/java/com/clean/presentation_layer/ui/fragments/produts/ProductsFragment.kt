package com.clean.presentation_layer.ui.fragments.produts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.clean.presentation_layer.R
import com.clean.presentation_layer.base.BaseFragment
import com.clean.presentation_layer.dapters.ProductCategoryAdapter
import com.clean.presentation_layer.databinding.ProductsFragmentBinding
import com.clean.presentation_layer.utils.gone
import com.clean.presentation_layer.utils.interfaces.ItemClick
import com.clean.presentation_layer.utils.viewbinding.viewBinding
import com.clean.presentation_layer.utils.visible
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsFragment : BaseFragment(R.layout.products_fragment), ItemClick {

    private val binding by viewBinding(ProductsFragmentBinding::bind)
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var listAdapter: ProductCategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        listAdapter = ProductCategoryAdapter(this)
        binding.productsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listAdapter
        }
        getProducts()

        // show the loading state for te first load
        listAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.btnMsg.gone()
                // Show ProgressBar
                binding.progressBar.visible()
            } else {
                // Hide ProgressBar
                binding.progressBar.gone()

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        binding.btnMsg.visible()
                        binding.btnMsg.text = "Please check your internet and try again"
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun navigateToProductDetailsFragment(productId: Int) {
        val action = ProductsFragmentDirections.actionProductsDestToProductDetailsDest(productId)
        findNavController().navigate(action)
    }

    private fun getProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProducts().collectLatest { listData ->
                listAdapter.submitData(listData)
            }
        }
    }

    override fun onItemClick(productId: Int) {
        navigateToProductDetailsFragment(productId)
    }


}
