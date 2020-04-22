package com.dvg.data.email

import androidx.annotation.WorkerThread
import com.dvg.data.EmailSender
import java.security.Security
import java.util.*
import javax.activation.DataHandler
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

internal class GMailSender: Authenticator(), EmailSender {

    private companion object {
        private const val SMTP_PORT_TLS = "587"

        private const val SENDER = "your_mail_address@gmail.com"
        private const val SENDER_PASS = "your_password"

        init {
            Security.addProvider(JSSEProvider())
        }
    }


    private val session: Session


    init {
        // setup TLS session
        val props = Properties()
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"

        props["mail.host"] = "smtp.gmail.com"
        props["mail.smtp.port"] = SMTP_PORT_TLS

        session = Session.getDefaultInstance(props, this)
    }

    override fun getPasswordAuthentication(): PasswordAuthentication = PasswordAuthentication(
        SENDER,
        SENDER_PASS
    )


    @WorkerThread
    @Synchronized
    @Throws(Exception::class)
    override fun sendEmail(subject: String, body: String, recipient: String): Boolean {
        Transport.send(
            buildMessage(
                subject = subject,
                body = body,
                recipient = recipient
            )
        )
        return true
    }


    private fun buildMessage(subject: String, body: String, recipient: String) : MimeMessage {
        val message = MimeMessage(session)
        val handler = DataHandler(EmailDataSource(body.toByteArray(), "text/plain"))

        message.sender = InternetAddress(SENDER)
        message.subject = subject
        message.dataHandler = handler

        message.setRecipient(Message.RecipientType.TO, InternetAddress(recipient))

        return message
    }

}