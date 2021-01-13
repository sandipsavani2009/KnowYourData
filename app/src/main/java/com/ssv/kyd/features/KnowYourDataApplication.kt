package com.ssv.kyd.features

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.provider.Settings
import com.ssv.kyd.features.helper.Logger
import java.util.*
import kotlin.collections.ArrayList

class KnowYourDataApplication: Application() {

    init {
        appInstance = this
    }

    companion object {
        private lateinit var appInstance: KnowYourDataApplication

        fun getInstance(): KnowYourDataApplication {
            return appInstance
        }
    }

    fun getAndroidId(): String? {
        var androidId = ""
        if (contentResolver != null) {
            androidId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            Logger.info("Android-Id", androidId)
        }
        return androidId
    }

    fun getMacAddress(): String? {
        /*try {
            val all: List<NetworkInterface> =
                Collections.list(NetworkInterface.getNetworkInterfaces())
            for (nif in all) {
                if (!nif.name.equals("wlan0", ignoreCase = true)) continue
                val macBytes = nif.hardwareAddress ?: return ""
                val res1 = StringBuilder()
                for (b in macBytes) {
                    res1.append(Integer.toHexString(b  0xFF) + ":")
                }
                if (res1.length > 0) {
                    res1.deleteCharAt(res1.length - 1)
                }
                return res1.toString()
            }
        } catch (ex: Exception) {
        }*/
        return ""
    }

    fun getDeviceTotalRam(): Long {
        var totalRam: Long = 0
        try {
            val actManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memInfo = ActivityManager.MemoryInfo()
            actManager.getMemoryInfo(memInfo)
            totalRam = memInfo.totalMem
        } catch (e: Exception) {}
        return totalRam
    }


}