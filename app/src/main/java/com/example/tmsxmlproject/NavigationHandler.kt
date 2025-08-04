package com.example.tmsxmlproject

import android.app.Activity
import android.content.Intent

fun Activity.startActivity(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
}