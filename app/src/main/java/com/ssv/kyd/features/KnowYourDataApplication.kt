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



}