package com.example.mycasino9.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycasino9.model.api.Repository
import com.example.mycasino9.model.RulesText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class TutorialsViewModel:ViewModel() {

    private val repository = Repository()
    val text: MutableLiveData<Response<RulesText>> = MutableLiveData()

    fun getTextTutorialsGame(){
        viewModelScope.launch(Dispatchers.IO) {
            val responce = repository.getTextRulesGame()
            withContext(Dispatchers.Main){
                text.value = responce
            }
        }
    }

}