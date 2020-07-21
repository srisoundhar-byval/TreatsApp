package `in`.byval.assist.API

import `in`.byval.assist.INTERFACE.ApiInterface
import `in`.byval.assist.UtilFunctions.TreatsServiceCall
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .build()
    private var retrofit: Retrofit? = null

    // change your base URL
    //Creating object for our interface
    val client: ApiInterface? // return the APIInterface object
        get() {


            // change your base URL
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(TreatsServiceCall.ServiceURI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
            }
            //Creating object for our interface
            return retrofit?.create<ApiInterface>(ApiInterface::class.java) // return the APIInterface object
        }
}