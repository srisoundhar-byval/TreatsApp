package `in`.byval.assist.UtilFunctions

import android.util.Base64
import android.util.Log
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets

object JWTUtils {
    @Throws(Exception::class)
    fun decoded(JWTEncoded: String): String? {
        return try {
            val split =
                JWTEncoded.split("\\.".toRegex()).toTypedArray()
           /* Log.d("JWT_DECODED", "Header: " + getJson(split[0]))
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]))*/
            getJson(split[1])
        } catch (e: UnsupportedEncodingException) {
            //Error
            e.message
        }
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, StandardCharsets.UTF_8)
    }
}