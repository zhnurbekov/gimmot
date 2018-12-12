package com.gimmot.gimmot.screens.login

import android.arch.lifecycle.LiveData
import android.net.Uri
import com.gimmot.gimmot.common.SingleLiveEvent
import com.gimmot.gimmot.data.firebase.AuthManager
import com.gimmot.gimmot.data.firebase.NearbyRepository
import com.gimmot.gimmot.data.firebase.UserRepository
import com.gimmot.gimmot.model.User
import com.gimmot.gimmot.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.PhoneAuthCredential
import java.net.URI


class LoginViewModel(private val authManager: AuthManager,
                     private val userRepository: UserRepository,
                     onFailureListener: OnFailureListener) : BaseViewModel(onFailureListener) {


    private val _goToMainScreen = SingleLiveEvent<Unit>()
    val goToMainScreen: LiveData<Unit> = _goToMainScreen
    val country = userRepository.getCountries()
    val user: LiveData<User> = userRepository.getUser()


    fun onLoginClick(credential: PhoneAuthCredential) {
        authManager.signIn(credential).addOnSuccessListener {
            _goToMainScreen.value = Unit
        }.addOnFailureListener(onFailureListener)
    }

    fun creatUser(user: User, uid: String): Task<Unit> =
        authManager.creatUser(user = user, toUid = uid).addOnFailureListener(onFailureListener)


    fun uploadAndSetUserPhoto(localImage: Uri): Task<Unit> =
            userRepository.uploadUserPhoto(localImage).onSuccessTask { downloadUrl ->
                userRepository.updateUserPhoto(downloadUrl!!)
            }.addOnFailureListener(onFailureListener)
}