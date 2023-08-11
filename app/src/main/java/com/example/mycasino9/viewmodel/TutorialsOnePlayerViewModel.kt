package com.example.mycasino9.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino9.api.Repository
import com.example.mycasino9.model.ResponseText
import com.example.mycasino9.model.RulesText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TutorialsOnePlayerViewModel:ViewModel() {

    val repo = Repository()
    val Text: MutableLiveData<Response<ResponseText>> = MutableLiveData()

    fun getTextOnePlayer(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repo.getTextOnePlayer()
            withContext(Dispatchers.Main){
                Text.value = responce
            }
        }
    }

}