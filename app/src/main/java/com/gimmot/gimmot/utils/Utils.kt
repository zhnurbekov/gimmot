package com.gimmot.gimmot.utils

import android.content.Context
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks


fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Task<*>.toUnit(): Task<Unit> =
        onSuccessTask { Tasks.forResult(Unit) }

/*
fun Context.showToast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    text?.let { Toast.makeText(this, it, duration).show() }
}*/
