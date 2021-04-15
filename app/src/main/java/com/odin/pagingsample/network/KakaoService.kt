package com.odin.pagingsample.network

import com.odin.pagingsample.model.ResponseImage
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoService {
    @GET("v2/search/image")
    suspend fun getImageList(
        @Query("query", encoded = true) query: String? = VALUE_DEFAULT_QUERY,
        @Query("sort") sort: String? = VALUE_DEFAULT_SORT,
        @Query("page") page: Int? = VALUE_DEFAULT_PAGE,
        @Query("size") size: Int? = VALUE_DEFAULT_SIZE
    ): ResponseImage

    companion object {
        // default 검색어
        private const val VALUE_DEFAULT_QUERY = "kakao"

        //accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy
        private const val VALUE_DEFAULT_SORT = "recency"

        //결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        private const val VALUE_DEFAULT_PAGE = 1

        //한 페이지에 보여질 문서 수, 1~80 사이의 값, 기본 값 80
        private const val VALUE_DEFAULT_SIZE = 50
    }
}