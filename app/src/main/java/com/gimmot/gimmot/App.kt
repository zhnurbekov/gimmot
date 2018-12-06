package com.gimmot.gimmot

import android.app.Application
import com.gimmot.gimmot.data.firebase.common.farebase.FirebaseAuthManager


class App : Application() {

    val authManager by lazy { FirebaseAuthManager() }

    override fun onCreate() {
        super.onCreate()

    }
}