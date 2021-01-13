package com.ssv.kyd.features.common.uiLibs

import android.view.View
import androidx.viewpager.widget.ViewPager


class CubeOutPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, pos: Float) {
        page.setPivotX(if (pos < 0f) page.getWidth().toFloat() else 0f)
        page.setPivotY(page.getHeight() * 0.5f)
        page.setRotationY(90f * pos)
    }
}