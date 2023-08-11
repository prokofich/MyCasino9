package com.example.mycasino9.api

import com.example.mycasino9.model.ResponceWebView
import com.example.mycasino9.model.ResponseText
import com.example.mycasino9.model.RulesText
import retrofit2.Response

class Repository {

    suspend fun setParametersPhone(phone_name:String,locale:String,unique:String): Response<ResponceWebView> {
        return RetrofitInstance.api.setPostParametersPhone(phone_name, locale, unique)
    }

    suspend fun getTextOnePlayer(): Response<ResponseText> {
        return RetrofitInstance.api.getTextOnePlayer()
    }

    suspend fun getTextRulesGame(): Response<RulesText> {
        return RetrofitInstance.api.getTextRulesGame()
    }

}