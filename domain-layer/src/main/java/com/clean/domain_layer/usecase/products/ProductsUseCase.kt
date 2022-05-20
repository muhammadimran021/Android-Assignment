package com.clean.domain_layer.usecase.products

import com.clean.domain_layer.repository.produtcsRepository.ProductsRepository
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val movieRepository: ProductsRepository,
) {
   operator fun invoke() = movieRepository.getPagedProducts()
}