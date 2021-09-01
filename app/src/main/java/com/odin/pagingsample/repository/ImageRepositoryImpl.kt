package com.odin.pagingsample.repository

import com.odin.pagingsample.data.ImageDao
import com.odin.pagingsample.model.SearchDocuments
import com.odin.pagingsample.network.KakaoService
import timber.log.Timber
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val imageDao: ImageDao,
    private val kakaoService: KakaoService
) : ImageRepository {
    override suspend fun fetchImageList(page: Int, loadSize: Int, query: String): MutableList<SearchDocuments> {
        try {
            val getImageList = kakaoService.getImageList(
                query = query,
                page = if (page == 0) 1 else page + 1
            )
            getImageList.documents.map {
                imageDao.insertAll(it)
            }
            Timber.w("success :: $getImageList")
        } catch (e: Exception) {
            Timber.e("failed :: $e")
        }
        return imageDao.getImageList(page, loadSize)
    }
}