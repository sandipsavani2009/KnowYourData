package com.ssv.kyd.features.helper

import android.content.res.Resources
import android.os.Build
import com.ssv.kyd.features.KnowYourDataApplication
import java.util.*
import kotlin.collections.ArrayList

class ConfigurationDataHelper {


    fun getScreenDensity(): String {
        return KnowYourDataApplication.getInstance().resources.displayMetrics.densityDpi.toString()
    }

    fun getScreenHeight(): String {
        return KnowYourDataApplication.getInstance().resources.displayMetrics.heightPixels.toString()
    }

    fun getScreenWidth(): String {
        return KnowYourDataApplication.getInstance().resources.displayMetrics.widthPixels.toString()
    }

    fun getSystemLanguage(): List<Locale> {
        val localeList = ArrayList<Locale>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val locales = Resources.getSystem().configuration.locales
            for (temp in 0 until locales.size()-1) {
                localeList.add(locales[temp])
            }
        } else {
            localeList.add(Resources.getSystem().configuration.locale)
        }

        return localeList
    }

}