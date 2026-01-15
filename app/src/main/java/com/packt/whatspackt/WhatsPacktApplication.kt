package com.packt.whatspackt

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.packt.feature.create_chat.ui.CreateNewChat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhatsPacktApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
