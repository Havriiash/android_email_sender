package com.dvg.data

import androidx.annotation.WorkerThread

interface EmailSender {

    @WorkerThread
    @Throws(Exception::class)
    fun sendEmail (subject: String, body: String, recipient: String): Boolean

}