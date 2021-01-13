package com.ssv.kyd.features.common.uiLibs

import android.view.View
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class BookFlipPageTransformer: ViewPager2.PageTransformer {

    private val LEFT = -1
    private val RIGHT = 1
    private val CENTER = 0

    //region Getters/Setters
    var scaleAmountPercent = 5f

    //endregion
    var isEnableScale = true

    override fun transformPage(page: View, position: Float) {
        val percentage = 1 - Math.abs(position)
        val viewPager = requireViewPager(page)
        // Don't move pages once they are on left or right
        if (position > CENTER && position <= RIGHT) {
            if (viewPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
                // This is behind page
                page.setTranslationX(-position * page.getWidth())
                page.setTranslationY(0F)
                page.setTranslationZ(-1F)
                page.setRotation(0F)
                if (isEnableScale) {
                    val amount =
                        (100 - scaleAmountPercent + scaleAmountPercent * percentage) / 100
                    setSize(page, position, amount)
                }
            } else {
                // This is behind page
                page.setTranslationY(-position * page.getHeight())
                page.setTranslationX(0F)
                page.setTranslationZ(-1F)
                page.setRotation(0F)
                if (isEnableScale) {
                    val amount =
                        (100 - scaleAmountPercent + scaleAmountPercent * percentage) / 100
                    setSize(page, position, amount)
                }
            }
        } else {
            page.setVisibility(View.VISIBLE)
            flipPage(page, position, percentage)
        }
    }

    private fun flipPage(
        page: View,
        position: Float,
        percentage: Float
    ) {

        // Flip this page
        page.setCameraDistance(-30000F)
        setVisibility(page, position)
        setTranslation(page)
        if (requireViewPager(page).orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            setPivot(page, 0f, page.getHeight() * 0.5f)
        } else if (requireViewPager(page).orientation == ViewPager2.ORIENTATION_VERTICAL) {
            setPivot(page, page.getWidth() * 0.5f, 0f)
        }
        setRotation(page, position, percentage)
    }

    private fun setPivot(page: View, pivotX: Float, pivotY: Float) {
        page.setPivotX(pivotX)
        page.setPivotY(pivotY)
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
        } else if (viewPager.orientation == ViewPager2.ORIENTATION_VERTICAL) {
            val scroll: Int = viewPager.scrollY - page.getTop()
            page.setTranslationY(scroll.toFloat())
        }
        page.setTranslationZ(1f)
    }

    private fun setSize(page: View, position: Float, percentage: Float) {
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
        } else if (viewPager.orientation == ViewPager2.ORIENTATION_VERTICAL) {
            if (position > 0) {
                page.setRotationX(180 * (percentage + 1))
            } else {
                page.setRotationX(-180 * (percentage + 1))
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