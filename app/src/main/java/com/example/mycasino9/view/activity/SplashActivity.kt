package com.example.mycasino9.view.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mycasino9.R
import com.example.mycasino9.constant.APP_PREFERENCES
import com.example.mycasino9.constant.ID
import com.example.mycasino9.constant.url_image_background
import com.example.mycasino9.constant.url_image_emblema_dice
import com.example.mycasino9.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*
import java.util.*

class SplashActivity : AppCompatActivity() {

    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        //устновка полноэкранного режима
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        var namePhone = Build.MODEL.toString()
        var locale = Locale.getDefault().getDisplayLanguage().toString()
        var id = ""

        if (getMyId()!=""){
            id = getMyId()
        }else{
            id = UUID.randomUUID().toString()
            setMyId(id)
        }


        //загрузка фоновой картинки
        loadBackgroundImage(url_image_background,id_splash_img)

        //загрузка эмблемы игры
        loadBackgroundImage(url_image_emblema_dice,id_splash_iv_img_dice)

        splashViewModel.setPostParametersPhone(namePhone,locale,id)

        splashViewModel.webViewUrl.observe(this){ responce ->
            when(responce.body()!!.url){
                "no" -> { goToMainPush() }
                "nopush" -> { goToMainNoPush() }
                else -> { goToWeb(responce.body()!!.url) }
            }
        }





    }

    //выход по кнопку назад
    override fun onBackPressed() {
        super.onBackPressed()
        job.cancel()
        finishAffinity()
    }

    //функция загрузки картинки
    private fun loadBackgroundImage(url:String,id: ImageView){
        Glide.with(this)
            .load(url)
            .into(id)
    }

    fun getMyId():String{
        var preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).getString(ID,"")
        return preferences ?: ""
    }

    fun setMyId(id:String){
        var preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        preferences.edit()
            .putString(ID,id)
            .apply()
    }

    fun goToMainPush() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }
    }

    fun goToMainNoPush() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }
    }

    fun goToWeb(url:String) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            var intent = Intent(this@SplashActivity,WebViewActivity::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }
    }


}