package com.ssv.kyd.features.home.about.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ssv.kyd.R
import com.ssv.kyd.features.common.BaseFragment
import com.ssv.kyd.features.common.uiLibs.BookFlipPageTransformer
import com.ssv.kyd.features.common.uiLibs.DepthViewPagerTransformer
import com.ssv.kyd.features.common.uiLibs.ZoomInViewPagerTransformer
import com.ssv.kyd.features.common.uiLibs.ZoomOutViewPagerTransformer
import com.ssv.kyd.features.home.about.adapter.AboutDataViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_app_data.*

class AboutFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transformer = BookFlipPageTransformer()
        about_data_viewpager.setPageTransformer(transformer)

        about_data_viewpager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

        about_data_viewpager.adapter = AboutDataViewPagerAdapter(this)
    }

}