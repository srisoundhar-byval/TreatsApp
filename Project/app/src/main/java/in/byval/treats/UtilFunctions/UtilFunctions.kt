package `in`.byval.assist.UtilFunctions

import android.content.Context
import android.net.ConnectivityManager
import android.os.Environment
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

object UtilFunctions {
    var INTENTCOUNT = 1
    const val NETWORK_UNAVAILABLE = "Network Not Available"
    const val SUCCESS = "SUCCESS"
    const val TRUE = "TRUE"
    const val FALSE = "FALSE"
    const val DELETED = "DELETED"
    const val PREFS_NAME = "TREATS"
    const val PREFS_USEREMAIL = "USEREMAIL"
    const val PREFS_USERTYPE = "USERTYPE"
    const val DARK = "DARK"
    const val LIGHT = "LIGHT"
    const val PREFS_APP_THEME = "PREFS_APP_THEME"

    //****************
    /*   //local
   // public static final String URL_FTP_IP_ADDRESS = "ftp://192.168.0.22";
    public static final String FTP_IP_ADDRESS = "192.168.0.22";
    public static final int FTP_PORT = 21;
    public static final String FTP_USERNAME = "anonymous";
    public static final String FTP_PASSWORD = "anonymous";*/
    fun appendLog(exception: Exception) {
        val writetext = exceptionStacktraceToString(exception)
        val file = File(
            Environment.getExternalStorageDirectory().path,
            "TreatsTemp"
        )
        if (!file.exists()) {
            file.mkdirs()
        }
        val uriSting =
            file.absolutePath + "/TreatsTempLog" + currentDateTimeForImage + ".txt"
        val logFile = File(uriSting)
        if (!logFile.exists()) {
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
        try {
            //BufferedWriter for performance, true to set append to file flag
            val buf =
                BufferedWriter(FileWriter(logFile, true))
            buf.newLine()
            buf.append(writetext)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    fun exceptionStacktraceToString(e: Exception): String {
        val baos = ByteArrayOutputStream()
        val ps = PrintStream(baos)
        e.printStackTrace(ps)
        ps.close()
        return baos.toString()
    }

    val currentDateTimeForImage: String
        get() {
            var CurDate = ""
            try {
                val df = SimpleDateFormat("yyyyMMddHHmmssSSS")
                val date = Date()
                CurDate = "" + df.format(date)
            } catch (e: Exception) {
                appendLog(e)
                e.printStackTrace()
            }
            return CurDate
        }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun setPreference(
        c: Context,
        key: String?,
        value: String?
    ): Boolean {
        val settings = c.getSharedPreferences(PREFS_NAME, 0)
        // settings = c.getSharedPreferences(PREFS_NAME, 0);
        val editor = settings.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    fun getPreference(c: Context, key: String?): String? {
        val settings = c.getSharedPreferences(PREFS_NAME, 0)
        // settings = c.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString(key, "")
    }

    fun clearPreference(mContext: Context) {
        val settings = mContext.getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit()
        editor.clear()
        editor.commit()
    }
}