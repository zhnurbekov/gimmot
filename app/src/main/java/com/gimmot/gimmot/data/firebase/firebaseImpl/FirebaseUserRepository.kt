package com.gimmot.gimmot.data.firebase.firebaseImpl

import android.arch.lifecycle.LiveData
import android.net.Uri
import com.gimmot.gimmot.data.database
import com.gimmot.gimmot.data.firebase.UserRepository
import com.gimmot.gimmot.data.liveData
import com.gimmot.gimmot.data.storage
import com.gimmot.gimmot.model.Country
import com.gimmot.gimmot.model.User
import com.gimmot.gimmot.screens.common.country
import com.gimmot.gimmot.screens.common.map
import com.gimmot.gimmot.utils.task
import com.gimmot.gimmot.utils.toUnit
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot


class FirebaseUserRepository : UserRepository{


    override fun currentUid() = FirebaseAuth.getInstance().currentUser?.uid

    override fun getUser(): LiveData<User> = getUser(currentUid()!!)

    override fun getUser(uid: String): LiveData<User> =
            database.child("users").child(uid).liveData().map {
                it.asUser()!!
            }

    private fun DataSnapshot.asUser(): User? =
            getValue(User::class.java)


    override fun getCountries(): LiveData<List<Country?>> =
            database.child(country).liveData().map {
                it.children.map{it.getValue(Country::class.java!!)} }

    override fun updateUserPhoto(downloadUrl: Uri): Task<Unit> =
            database.child("users/${currentUid()!!}/photo").setValue(downloadUrl.toString()).toUnit()


    override fun uploadUserPhoto(localImage: Uri): Task<Uri> =
            task { taskSource ->
                storage.child("users/${currentUid()!!}/photo").putFile(localImage)
                        .addOnSuccessListener {
                            taskSource.setResult(it?.uploadSessionUri!!)
                        }
            }


}