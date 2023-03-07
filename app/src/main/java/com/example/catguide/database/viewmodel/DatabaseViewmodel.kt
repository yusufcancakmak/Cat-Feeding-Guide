package com.example.catguide.database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catguide.database.model.Veri
import com.example.catguide.database.repository.DatabaseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DatabaseViewmodel @Inject constructor( val databaseRepository: DatabaseRepository):ViewModel() {

    private val _myData = MutableLiveData<List<Veri>>()
    val myData:LiveData<List<Veri>> = _myData

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val result = databaseRepository.getVeriler()
            _myData.value=result.value

        }
    }
}