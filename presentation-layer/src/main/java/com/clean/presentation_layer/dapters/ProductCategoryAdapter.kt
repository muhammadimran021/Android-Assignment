package com.clean.presentation_layer.dapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.clean.domain_layer.model.productCategory.ProductCategoryData
import com.clean.presentation_layer.databinding.ProductRowItemBinding
import com.clean.presentation_layer.utils.interfaces.ItemClick
import com.clean.presentation_layer.utils.loadImage

class ProductCategoryAdapter(val itemClick: ItemClick) :
    PagingDataAdapter<ProductCategoryData, ProductCategoryAdapter.MyViewHolder>(
        CategoryDataComparator
    ) {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productDataModel: ProductCategoryData = getItem(position)!!
        Log.d("TAG", "onBindViewHolder: ${productDataModel.brand_name}")
        holder.bind(productDataModel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductCategoryAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ProductRowItemBinding = ProductRowItemBinding.inflate(
            layoutInflater, parent, false
        )
        return MyViewHolder(itemBinding)
    }


    inner class MyViewHolder(val binding: ProductRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductCategoryData) {
            binding.tvProductTitle.text = item.name
            binding.tvProductSubTitle.text = item.products_name
            binding.tvProductPrice.text = "Price: ${item.final_price_base}"
            binding.image.loadImage(item.thumbnail_url)
            binding.root.setOnClickListener { itemClick.onItemClick(item.product_id) }
        }
    }


    companion object {
        private val CategoryDataComparator = object : DiffUtil.ItemCallback<ProductCategoryData>() {
            override fun areItemsTheSame(
                oldItem: ProductCategoryData,
                newItem: ProductCategoryData
            ): Boolean {
                return (oldItem.product_id == newItem.product_id)
            }

            override fun areContentsTheSame(
                oldItem: ProductCategoryData,
                newItem: ProductCategoryData
            ): Boolean =
                oldItem == newItem
        }
    }


}