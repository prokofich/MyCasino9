package com.example.mycasino9.model.application

import android.app.Application
import com.onesignal.OneSignal

class OneSignalApplication:Application() {

    private val ONESIGNAL_APP_ID = "b575de5b-fe75-46b7-a5e8-519212f47d6e"

    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

    }
}