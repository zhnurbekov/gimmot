package com.gimmot.gimmot.data.firebase.firebaseImpl

import android.arch.lifecycle.LiveData
import com.gimmot.gimmot.data.firebase.AuthManager
import com.gimmot.gimmot.data.auth
import com.gimmot.gimmot.data.database
import com.gimmot.gimmot.data.liveData
import com.gimmot.gimmot.model.Country
import com.gimmot.gimmot.model.User
import com.gimmot.gimmot.screens.common.country
import com.gimmot.gimmot.screens.common.map
import com.gimmot.gimmot.utils.toUnit
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential

class FirebaseAuthManager : AuthManager {
    override fun signIn(credential: PhoneAuthCredential): Task<AuthResult> =
            auth.signInWithCredential(credential)


    override fun creatUser(user: User,toUid :String): Task<Unit> =
        database.child("users").child(toUid).setValue(user).toUnit()






}
