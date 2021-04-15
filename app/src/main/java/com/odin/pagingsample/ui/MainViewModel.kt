package com.odin.pagingsample.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.odin.pagingsample.data.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {

    fun clearDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            appDatabase.clearAllTables()
        }
    }

}