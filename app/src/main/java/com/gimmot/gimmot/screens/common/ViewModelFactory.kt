package com.gimmot.gimmot.screens.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gimmot.gimmot.App
import com.gimmot.gimmot.screens.login.LoginViewModel
import com.google.android.gms.tasks.OnFailureListener


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val app: App,
                       private val commonViewModel: CommonViewModel,
                       private val onFailureListener: OnFailureListener) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val authManager = app.authManager

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(authManager,onFailureListener) as T
        } else {
            error("Unknown view model class $modelClass")
        }
    }
}
