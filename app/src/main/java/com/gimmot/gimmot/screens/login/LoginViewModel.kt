package com.gimmot.gimmot.screens.login

import android.arch.lifecycle.LiveData
import com.gimmot.gimmot.common.SingleLiveEvent
import com.gimmot.gimmot.data.firebase.AuthManager
import com.gimmot.gimmot.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.PhoneAuthCredential


class LoginViewModel(private val authManager: AuthManager,
                     onFailureListener: OnFailureListener): BaseViewModel(onFailureListener){


    private val _goToMainScreen = SingleLiveEvent<Unit>()
    val goToMainScreen: LiveData<Unit> = _goToMainScreen
    val country = authManager.getCountries()

    fun onLoginClick(credential: PhoneAuthCredential) {
            authManager.signIn(credential).addOnSuccessListener {
                _goToMainScreen.value = Unit
            }.addOnFailureListener(onFailureListener)
    }



}