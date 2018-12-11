package com.gimmot.gimmot.data.firebase

import android.arch.lifecycle.LiveData
import com.gimmot.gimmot.model.Country
import com.gimmot.gimmot.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential


interface AuthManager {

    fun signIn(credential: PhoneAuthCredential): Task<AuthResult>

    fun creatUser(user: User,toUid :String): Task<Unit>

}