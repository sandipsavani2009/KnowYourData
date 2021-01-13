package com.ssv.kyd.features.home.about.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssv.kyd.features.home.about.ui.AboutDataViewPagerFragment

class AboutDataViewPagerAdapter(parentFragment: Fragment) : FragmentStateAdapter(parentFragment) {

    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
        return AboutDataViewPagerFragment(position)
    }

}
