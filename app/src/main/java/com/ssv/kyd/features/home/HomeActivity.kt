package com.ssv.kyd.features.home

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.ssv.kyd.R
import com.ssv.kyd.features.common.BaseActivity
import com.ssv.kyd.features.helper.FragmentsHelper
import com.ssv.kyd.features.home.about.AboutFragment
import com.ssv.kyd.features.home.appData.AppDataFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: BaseActivity() {

    override fun onDestroy() {
        super.onDestroy()
        bottom_navigation_view.setOnNavigationItemSelectedListener(null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initListeners()
        chooseDefaultTab()
    }

    private fun chooseDefaultTab() {
        bottom_navigation_view.selectedItemId = R.id.navigation_menu_about
    }

    private fun initListeners() {
        bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment = when(item.itemId) {
                R.id.navigation_menu_app_data -> AppDataFragment()
                else -> AboutFragment()
            }
            FragmentsHelper.replaceFragment(this@HomeActivity, R.id.fragment_container, fragment)
            return@setOnNavigationItemSelectedListener false
        }
    }
}