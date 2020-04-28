package com.trongdeptrai.mvvmsample.data.network.responses

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.trongdeptrai.mvvmsample.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInternet(
    context: Context
): Interceptor {
    private  val application = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable()) {
            throw NoInternetException("Make sure you have an action data connection")
        }
        return  chain.proceed(chain.request())
    }

    private  fun isInternetAvailable(): Boolean {
        val manager =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}