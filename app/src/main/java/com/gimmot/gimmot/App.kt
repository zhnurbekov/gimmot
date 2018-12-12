package com.gimmot.gimmot

import android.app.Application
import com.gimmot.gimmot.data.firebase.firebaseImpl.FirebaseAuthManager
import com.gimmot.gimmot.data.firebase.firebaseImpl.FirebaseNearbyRepository
import com.gimmot.gimmot.data.firebase.firebaseImpl.FirebaseUserRepository


class App : Application() {

    val authManager by lazy { FirebaseAuthManager() }
    val nearbyRepository by lazy { FirebaseNearbyRepository() }
    val userRepository by lazy { FirebaseUserRepository() }

    override fun onCreate() {
        super.onCreate()

    }
}