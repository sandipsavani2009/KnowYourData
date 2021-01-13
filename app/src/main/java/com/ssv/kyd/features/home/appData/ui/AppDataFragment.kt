package com.ssv.kyd.features.home.appData.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ssv.kyd.R
import com.ssv.kyd.features.home.appData.adapter.AppDataViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_app_data.*

class AppDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        app_data_viewpager.adapter = AppDataViewPagerAdapter(this)
    }
}