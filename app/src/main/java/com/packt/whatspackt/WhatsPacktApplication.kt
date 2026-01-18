package com.packt.whatspackt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhatsPacktApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
