package com.ssv.kyd.features.home.about.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ssv.kyd.R

class AboutDataViewPagerFragment(val position: Int = 0): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = when(position) {
            1 -> R.layout.about_data_concern_layout
            2 -> R.layout.about_data_why_collected_layout
            3 -> R.layout.about_data_permisssions_intro_layout
            4 -> R.layout.about_data_how_collected_layout
            else -> R.layout.about_data_purpose_layout
        }
        return inflater.inflate(layout, container, false)
    }

}