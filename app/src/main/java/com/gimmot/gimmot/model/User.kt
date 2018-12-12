package com.gimmot.gimmot.model

import com.google.firebase.database.Exclude
import java.util.*


data class User(var name: String = "", var birth_date: Date, var about: String = "",
                var growth: Int = 0, var weight: Int = 0, var penis: Int = 0,
                var photo:String? = null, @Exclude val uid: String = "")



