package com.odin.pagingsample.repository

import com.odin.pagingsample.model.SearchDocuments

interface ImageRepository {

    suspend fun fetchImageList(page: Int, loadSize: Int, query: String): MutableList<SearchDocuments>

}