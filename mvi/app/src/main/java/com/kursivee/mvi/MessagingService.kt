package com.kursivee.mvi

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        println("${remoteMessage.from}")
        println("${remoteMessage.messageId}")
        println("${remoteMessage.messageType}")
        println("${remoteMessage.notification?.body}")
        println("${remoteMessage.notification?.title}")
    }

    override fun onNewToken(token: String) {
        println(token)
    }
}