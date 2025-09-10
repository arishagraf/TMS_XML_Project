package com.example.tmsxmlproject.networking.data.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InternetConnectionManager @Inject constructor(
    private val context: Context
) {
    //change Dispatcher to Main because was called form IO.
    suspend fun isOnline() = withContext(Dispatchers.Main) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return@withContext false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return@withContext false

        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Toast.makeText(context, "Connected via WiFi", Toast.LENGTH_SHORT).show()
                true
            }

            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Toast.makeText(context, "Connected via Mobile Data", Toast.LENGTH_SHORT).show()
                true
            }

            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Toast.makeText(context, "Connected via Ethernet", Toast.LENGTH_SHORT).show()
                true
            }

            else -> false
        }
    }
}