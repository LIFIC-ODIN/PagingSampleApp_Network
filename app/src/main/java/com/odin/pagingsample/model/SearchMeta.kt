package com.odin.pagingsample.model

data class SearchMeta(
    val total_count: Int = 0,
    val pageable_count: Int = 0,
    val is_end: Boolean = false
)
