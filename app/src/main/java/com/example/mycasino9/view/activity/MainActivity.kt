package com.example.mycasino9.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mycasino9.R
import com.example.mycasino9.constant.APP_PREFERENCES
import com.example.mycasino9.constant.COUNT_WIN_ONE_PLAYER
import com.example.mycasino9.constant.MAIN

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

        //устновка полноэкранного режима
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    //функция обновления количества побед в одноп. режиме
    fun updateCountWinOnePlayer(){
        val countWin = getCountWinOnePlayer()
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        pref.edit()
            .putInt(COUNT_WIN_ONE_PLAYER,countWin+1)
            .apply()
    }

    //функция получения количества побед
    fun getCountWinOnePlayer():Int{
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(COUNT_WIN_ONE_PLAYER, 0)
    }



}