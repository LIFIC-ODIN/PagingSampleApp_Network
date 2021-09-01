package com.odin.pagingsample.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.odin.pagingsample.model.SearchDocuments
import com.odin.pagingsample.repository.ImageRepository
import java.net.UnknownHostException

class SearchImagePagingSource(
    private val imageRepository: ImageRepository,
    private val keyword : String
) : PagingSource<Int, SearchDocuments>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchDocuments> {

        val page = params.key ?: 1
        return try {
            val items = imageRepository.fetchImageList(page, params.loadSize, keyword)
            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = page+1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, SearchDocuments>): Int? {
        return null
    }
}