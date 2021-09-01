package com.odin.pagingsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.odin.pagingsample.model.SearchDocuments

@Dao
interface ImageDao {
    @Query("SELECT * FROM images LIMIT :loadSize OFFSET (:page-1) * :loadSize")
    suspend fun getImageList(page: Int, loadSize: Int): MutableList<SearchDocuments>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: SearchDocuments)

}