package com.odin.pagingsample.ui.list

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.odin.pagingsample.data.SearchImagePagingSource
import com.odin.pagingsample.repository.ImageRepository
import com.odin.pagingsample.util.GET_DATA_COUNT

class ListViewModel @ViewModelInject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    val searchData = ObservableField("아이유")

    val flow = Pager(
        PagingConfig(
            pageSize = GET_DATA_COUNT,
            prefetchDistance = 1
        )
    ) {
        SearchImagePagingSource(repository, searchData.get() ?: "아이유")
    }.flow.cachedIn(viewModelScope)
}