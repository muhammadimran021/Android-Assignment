package com.clean.data_layer.repository

import android.app.Application
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.clean.data_layer.BuildConfig
import com.clean.data_layer.remote.WebService
import com.clean.domain_layer.model.productCategory.ProductCategoryData
import retrofit2.HttpException
import java.io.IOException

class ProductsPagingSource(
    private val webService: WebService
) : PagingSource<Int, ProductCategoryData>() {

    //todo https://developer.android.com/topic/libraries/architecture/paging/test
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, ProductCategoryData> {
        return try {
            val response = webService.getProducts(
                auth = BuildConfig.AUTH_TOKEN,
                categoryId = 27
            ).body()

            response ?: run {
                return LoadResult.Error(Throwable())
            }
            Log.d("TAG", "load: ${params.key}}")
            var nextPage = 1
            if (params.key != null) {
                val nextValue = params.key.toString().toInt()
                nextPage += nextValue
            }
            LoadResult.Page(
                data = response.body.data,
                prevKey = null, // Only paging forward.
                nextKey = nextPage
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    // anchorPage is the initial page, so just return null.
    override fun getRefreshKey(state: PagingState<Int, ProductCategoryData>): Int? = null
}