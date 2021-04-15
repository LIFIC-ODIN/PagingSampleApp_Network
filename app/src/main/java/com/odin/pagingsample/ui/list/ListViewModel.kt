package com.odin.pagingsample.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.odin.pagingsample.data.SearchImagePagingSource
import com.odin.pagingsample.repository.ImageRepository

class ListViewModel @ViewModelInject constructor(
    private val repository: ImageRepository
) : ViewModel() {
    val flow = Pager(PagingConfig(pageSize = 1, prefetchDistance = 1)) {
        SearchImagePagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}