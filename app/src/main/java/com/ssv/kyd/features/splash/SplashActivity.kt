package com.ssv.kyd.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.ssv.kyd.R
import com.ssv.kyd.features.common.BaseActivity
import com.ssv.kyd.features.home.HomeActivity

class SplashActivity: BaseActivity() {

    private val mHomeLaunchHandler = Handler()

    override fun onDestroy() {
        mHomeLaunchHandler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        launchHome()
    }

    private fun launchHome() {
        mHomeLaunchHandler.postDelayed({ startActivity(Intent(this, HomeActivity::class.java)) }, 1000)
    }

}