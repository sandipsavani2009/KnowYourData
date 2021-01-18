package com.ssv.kyd.features.helper

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.provider.Settings
import com.ssv.kyd.features.KnowYourDataApplication
import com.ssv.kyd.features.common.Constants
import java.util.*
import kotlin.collections.ArrayList

class DataProvider {

    companion object {

        fun isDataRestricted(dataID: Int): Boolean {
            return when(dataID) {
                Constants.DATA_ID_IMEI,
                Constants.DATA_ID_ANDROID_ID -> true
                else -> false
            }
        }

        fun getPermissionTypeFromDataId(dataID: Int): String {
            return when(dataID) {
                Constants.DATA_ID_IMEI -> Manifest.permission.READ_PHONE_STATE
                else -> ""
            }
        }

        fun getData(dataID: Int): String? {
            return when(dataID) {
                Constants.DATA_ID_ANDROID_ID -> getAndroidId()
                else -> null
            }
        }

        private fun getScreenDensity(): String {
            return KnowYourDataApplication.getInstance().resources.displayMetrics.densityDpi.toString()
        }

        private fun getScreenHeight(): String {
            return KnowYourDataApplication.getInstance().resources.displayMetrics.heightPixels.toString()
        }

        private fun getScreenWidth(): String {
            return KnowYourDataApplication.getInstance().resources.displayMetrics.widthPixels.toString()
        }

        private fun getSystemLanguage(): List<Locale> {
            val localeList = ArrayList<Locale>()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val locales = Resources.getSystem().configuration.locales
                for (temp in 0 until locales.size() - 1) {
                    localeList.add(locales[temp])
                }
            } else {
                localeList.add(Resources.getSystem().configuration.locale)
            }

            return localeList
        }

        private fun getAndroidId(): String? {
            val context = KnowYourDataApplication.getInstance()
            var androidId = ""
            if (context.contentResolver != null) {
                androidId =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                Logger.info("Android-Id", androidId)
            }
            return androidId
        }

        private fun getMacAddress(): String? {
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

        private fun getDeviceTotalRam(): Long {
            var totalRam: Long = 0
            try {
                val actManager = KnowYourDataApplication.getInstance()
                    .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val memInfo = ActivityManager.MemoryInfo()
                actManager.getMemoryInfo(memInfo)
                totalRam = memInfo.totalMem
            } catch (e: Exception) {
            }
            return totalRam
        }

    }
}