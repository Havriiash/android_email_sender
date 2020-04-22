package com.dvg.data.email

import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import javax.activation.DataSource

internal class EmailDataSource(
    private val data: ByteArray,
    var type: String? = null
): DataSource {

    override fun getName(): String = "ByteArrayDataSource"

    override fun getOutputStream(): OutputStream = throw IOException("Not Supported")

    override fun getInputStream(): InputStream = ByteArrayInputStream(data)

    override fun getContentType(): String = type ?: "application/octet-stream"

}