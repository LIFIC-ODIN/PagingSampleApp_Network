package com.odin.pagingsample.repository

import com.odin.pagingsample.model.SearchDocuments
interface ImageRepository {

    suspend fun fetchImageList(nextPage: Int, query: String): MutableList<SearchDocuments>

}