package com.example.mycasino9.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mycasino9.R
import com.example.mycasino9.model.constant.APP_PREFERENCES
import com.example.mycasino9.model.constant.COUNT_WIN_ONE_PLAYER
import com.example.mycasino9.model.constant.MAIN

class MainActivity : AppCompatActivity() {

    var navController : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN = this
        navController = Navigation.findNavController(this,R.id.id_nav_host)

    }

    //функция обновления количества побед в одноп. режиме
    fun updateCountWinOnePlayer() {
        val countWin = getCountWinOnePlayer()
        getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit().putInt(COUNT_WIN_ONE_PLAYER,countWin+1).apply()
    }

    //функция получения количества побед
    fun getCountWinOnePlayer() : Int {
        return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(COUNT_WIN_ONE_PLAYER, 0)
    }

}