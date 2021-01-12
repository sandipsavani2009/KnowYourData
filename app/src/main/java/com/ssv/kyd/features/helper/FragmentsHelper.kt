package com.ssv.kyd.features.helper

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentsHelper {

    companion object {

        fun replaceFragment(
            activity: AppCompatActivity,
            containerId: Int,
            fragment: Fragment
        ) {
            val fragmentManager = activity.supportFragmentManager
            fragmentManager.beginTransaction().replace(containerId, fragment, fragment.javaClass.simpleName).commit()
            fragmentManager.executePendingTransactions()
        }
    }


}