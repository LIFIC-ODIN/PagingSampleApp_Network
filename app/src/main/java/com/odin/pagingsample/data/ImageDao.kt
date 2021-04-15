package com.odin.pagingsample.data

import androidx.room.*
import com.odin.pagingsample.model.SearchDocuments

@Dao
interface ImageDao {
    @Query("SELECT * FROM images LIMIT 30 OFFSET :offset")
    suspend fun getImageList(offset: Int): MutableList<SearchDocuments>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: SearchDocuments)

}