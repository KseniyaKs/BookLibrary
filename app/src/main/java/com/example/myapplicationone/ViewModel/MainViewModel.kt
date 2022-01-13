package com.example.myapplicationone.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationone.dataclass.ListBook
import com.example.myapplicationone.request.Repository

class MainViewModel(val repository: Repository): ViewModel() {

    var data: MutableLiveData<ListBook> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.d("TAG", "onCleared")
    }

    init {
        Log.d("TAG", "create MyViewModel")
        loadSearchData(searchTxt = "book")
    }

    fun loadSearchData(searchTxt : String?){
        repository.callSearchBookList(data, searchTxt)
    }
}