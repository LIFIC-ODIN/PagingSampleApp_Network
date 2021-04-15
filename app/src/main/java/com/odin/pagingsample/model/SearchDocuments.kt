package com.odin.pagingsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "images")
data class SearchDocuments(
    @SerializedName("collection")
    val collection: String = "",
    @SerializedName("thumbnail_url")
    val thumbnail_url: String = "",
    @PrimaryKey
    @SerializedName("image_url")
    val image_url: String = "",
    @SerializedName("width")
    val width: Int = 0,
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("display_sitename")
    val display_sitename: String = "",
    @SerializedName("doc_url")
    val doc_url: String = "",
    @SerializedName("datetime")
    val datetime: Date = Date()
)