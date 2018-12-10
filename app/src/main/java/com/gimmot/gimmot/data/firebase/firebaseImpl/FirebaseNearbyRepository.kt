package com.gimmot.gimmot.data.firebase.firebaseImpl

import android.arch.lifecycle.LiveData
import com.gimmot.gimmot.data.database
import com.gimmot.gimmot.data.firebase.NearbyRepository
import com.gimmot.gimmot.data.liveData
import com.gimmot.gimmot.model.Country
import com.gimmot.gimmot.screens.common.country
import com.gimmot.gimmot.screens.common.map


class FirebaseNearbyRepository : NearbyRepository{

    override fun getCountries(): LiveData<List<Country?>> =
            database.child(country).liveData().map {
                it.children.map{it.getValue(Country::class.java!!)} }
}