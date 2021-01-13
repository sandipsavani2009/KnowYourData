package com.ssv.kyd.features.common.uiLibs

import android.view.View
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class CardFlipPagerTransformer : ViewPager2.PageTransformer {

    var isScalable = false

    override fun transformPage(page: View, position: Float) {
        val percentage = 1 - Math.abs(position)
        page.setCameraDistance(30000F)
        setVisibility(page, position)
        setTranslation(page)
        setSize(page, position, percentage)
        setRotation(page, position, percentage)
    }

    private fun setVisibility(page: View, position: Float) {
        if (position < 0.5 && position > -0.5) {
            page.setVisibility(View.VISIBLE)
        } else {
            page.setVisibility(View.INVISIBLE)
        }
    }

    private fun setTranslation(page: View) {
        val viewPager = requireViewPager(page)
        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            val scroll: Int = viewPager.scrollX - page.getLeft()
            page.setTranslationX(scroll.toFloat())
        } else {
            val scroll: Int = viewPager.scrollY - page.getTop()
            page.setTranslationY(scroll.toFloat())
        }
    }

    private fun setSize(page: View, position: Float, percentage: Float) {
        // Do nothing, if its not scalable
        if (!isScalable) return
        page.setScaleX(if (position != 0f && position != 1f) percentage else 1F)
        page.setScaleY(if (position != 0f && position != 1f) percentage else 1F)
    }

    private fun setRotation(
        page: View,
        position: Float,
        percentage: Float
    ) {
        val viewPager = requireViewPager(page)
        if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (position > 0) {
                page.setRotationY(-180 * (percentage + 1))
            } else {
                page.setRotationY(180 * (percentage + 1))
            }
        } else {
            if (position > 0) {
                page.setRotationX(-180 * (percentage + 1))
            } else {
                page.setRotationX(180 * (percentage + 1))
            }
        }
    }

    private fun requireViewPager(page: View): ViewPager2 {
        val parent: ViewParent = page.getParent()
        val parentParent = parent.parent
        if (parent is RecyclerView && parentParent is ViewPager2) {
            return parentParent
        }
        throw IllegalStateException(
            "Expected the page view to be managed by a ViewPager2 instance."
        )
    }

}