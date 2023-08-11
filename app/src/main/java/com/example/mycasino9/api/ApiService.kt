package com.example.mycasino9.api

import com.example.mycasino9.model.ResponceWebView
import com.example.mycasino9.model.ResponseText
import com.example.mycasino9.model.RulesText
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("splash.php")
    suspend fun setPostParametersPhone(
        @Field("phone_name") phone_name:String,
        @Field("locale") locale:String,
        @Field("unique") unique:String
    ): Response<ResponceWebView>


    @GET("16/tutorialOne.json")
    suspend fun getTextOnePlayer():Response<ResponseText>

    @GET("16/tutorialsGame.json")
    suspend fun getTextRulesGame():Response<RulesText>

}