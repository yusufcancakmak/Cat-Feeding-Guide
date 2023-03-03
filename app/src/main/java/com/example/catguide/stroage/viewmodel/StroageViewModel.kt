package com.example.catguide.stroage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catguide.stroage.repository.StroageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class StroageViewModel @Inject constructor(private val storageRepository: StroageRepository):ViewModel(){


    private val _photoData = MutableLiveData<ByteArray>()
    val photoData: LiveData<ByteArray> = _photoData

    fun downloadFile(){
        viewModelScope.launch {
            val bytes =storageRepository.dowloadFile("path/to/photo.jpg")
            _photoData.value=bytes
        }
    }
}