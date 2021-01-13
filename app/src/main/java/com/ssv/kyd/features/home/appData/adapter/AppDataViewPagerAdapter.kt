package com.ssv.kyd.features.home.appData.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssv.kyd.features.home.appData.ui.AppDataViewPagerFragment

class AppDataViewPagerAdapter(parentFragment: Fragment) : FragmentStateAdapter(parentFragment) {

    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return AppDataViewPagerFragment(position)
    }

}
