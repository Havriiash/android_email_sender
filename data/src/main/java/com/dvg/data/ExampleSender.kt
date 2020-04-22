package com.dvg.data

import com.dvg.data.email.GMailSender

class ExampleSender: EmailSender {

    private val gMailSender = GMailSender()

    override fun sendEmail(subject: String, body: String, recipient: String): Boolean {
        return gMailSender.sendEmail(subject, body, recipient)
    }

}