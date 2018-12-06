package com.gimmot.gimmot.data.firebase.common.farebase

import android.arch.lifecycle.LiveData
import com.gimmot.gimmot.data.firebase.AuthManager
import com.gimmot.gimmot.data.auth
import com.gimmot.gimmot.data.database
import com.gimmot.gimmot.data.liveData
import com.gimmot.gimmot.model.Country
import com.gimmot.gimmot.screens.common.country
import com.gimmot.gimmot.screens.common.map
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential

class FirebaseAuthManager : AuthManager {

    override fun signIn(credential: PhoneAuthCredential): Task<AuthResult> =
            auth.signInWithCredential(credential)


    override fun getCountries(): LiveData<List<Country?>> =
            database.child(country).liveData().map {
                it.children.map{it.getValue(Country::class.java!!)} }


}
