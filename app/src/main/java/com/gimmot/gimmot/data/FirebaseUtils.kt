package com.gimmot.gimmot.data

import android.arch.lifecycle.LiveData
import com.gimmot.gimmot.data.firebase.common.FirebaseLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

val auth: FirebaseAuth = FirebaseAuth.getInstance()
val database: DatabaseReference = FirebaseDatabase.getInstance().reference
fun DatabaseReference.liveData(): LiveData<DataSnapshot> = FirebaseLiveData(this)


