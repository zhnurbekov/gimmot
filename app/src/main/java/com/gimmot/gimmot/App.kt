package com.gimmot.gimmot

import android.app.Application
import com.gimmot.gimmot.data.firebase.firebaseImpl.FirebaseAuthManager
import com.gimmot.gimmot.data.firebase.firebaseImpl.FirebaseNearbyRepository


class App : Application() {

    val authManager by lazy { FirebaseAuthManager() }
    val nearbyRepository by lazy { FirebaseNearbyRepository() }

    override fun onCreate() {
        super.onCreate()

    }
}