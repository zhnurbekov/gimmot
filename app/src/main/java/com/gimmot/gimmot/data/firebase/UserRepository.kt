package com.gimmot.gimmot.data.firebase

import android.arch.lifecycle.LiveData
import android.net.Uri
import com.gimmot.gimmot.model.Country
import com.gimmot.gimmot.model.User
import com.google.android.gms.tasks.Task


interface UserRepository {

    fun currentUid(): String?
    fun getUser(): LiveData<User>
    fun getUser(uid: String): LiveData<User>
    fun uploadUserPhoto(localImage: Uri): Task<Uri>
    fun updateUserPhoto(downloadUrl: Uri): Task<Unit>
    fun getCountries(): LiveData<List<Country?>>


}