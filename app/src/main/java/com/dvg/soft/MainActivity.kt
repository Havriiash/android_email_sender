package com.dvg.soft

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dvg.data.ExampleSender
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOG_TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_activity__send_btn.setOnClickListener { sendEmailExample() }
    }

    private fun sendEmailExample() {
        val email = main_activity__email_address.editableText.toString()
        val subject = main_activity__email_subject.editableText.toString()
        val content = main_activity__email_content.editableText.toString()

        // use viewmodel or something else instead
        Thread {
            try {
                val result = ExampleSender()
                    .sendEmail(subject, content, email)
                Log.d(LOG_TAG, "Email sent result: $result")
            } catch (ex: Exception) {
                Log.d(LOG_TAG, "Email sent result: ${ex.message}")
            }
        }.start()
    }

}
