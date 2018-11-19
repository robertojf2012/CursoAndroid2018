package com.example.robert.playnotifications

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FCMService: FirebaseMessagingService() {

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
        Log.d(TAG, "Refreshed token: $s")
    }

}
