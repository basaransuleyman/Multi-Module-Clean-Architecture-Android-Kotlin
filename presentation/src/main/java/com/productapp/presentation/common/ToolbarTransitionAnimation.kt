package com.productapp.presentation.common

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.PorterDuff
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.AppBarLayout
import com.product.common.model.detail.DetailUIModel
import com.product.sideapp.home.R
import com.productapp.presentation.common.extension.hide
import com.productapp.presentation.common.extension.show
import kotlin.math.abs

class ToolbarTransitionAnimation {

    var isToolbarVisible = true

    fun collapsingToolbarTransition(
        appBarLayout: AppBarLayout,
        context: Context,
        imageView: ImageView?,
        textView: TextView,
        icon:ImageView?,
        data: DetailUIModel
    ) {
        val evaluator = ArgbEvaluator()
        appBarLayout.addOnOffsetChangedListener { _, verticalOffset ->
            val maxScroll = appBarLayout.totalScrollRange
            val percentage = ((abs(verticalOffset).toFloat()) / maxScroll.toFloat())

            val startColor = ContextCompat.getColor(context, R.color.transparent)
            val endColor = ContextCompat.getColor(context, R.color.md_theme_dark_secondary)

            val color = if (percentage == 1f) endColor else evaluator.evaluate(
                percentage,
                startColor,
                endColor
            ) as Int
            imageView?.setColorFilter(color, PorterDuff.Mode.SRC_OVER)

            if (percentage == 1f) {
                animateText(textView)
                icon?.show()
                textView.show()
                textView.setTextColor(
                    ContextCompat.getColor(
                        context, R.color.md_theme_light_onSurface
                    )
                )
                textView.text = data.productName
                isToolbarVisible = false
            } else {
                if (!isToolbarVisible) {
                    animateTextDown(textView)
                    textView.hide()
                    icon?.hide()
                    isToolbarVisible = true
                }
            }
        }
    }

    private fun animateText(textView: TextView) {

        val translationAnimation = ObjectAnimator.ofFloat(
            textView,
            PROPERTY_NAME_Y_AXIS,
            Y_AXIS_DEFAULT_FLOAT,
            Y_AXIS_TRANSITIONS_FLOAT
        )
        translationAnimation.duration = DEFAULT_DURATION_TOP
        translationAnimation.interpolator = AccelerateDecelerateInterpolator()

        val alphaAnimation = ObjectAnimator.ofFloat(
            textView,
            PROPERTY_NAME_ALPHA,
            Y_AXIS_TRANSITIONS_FLOAT,
            Y_AXIS_ALPHA_FLOAT
        )
        alphaAnimation.duration = DEFAULT_DURATION_TOP
        alphaAnimation.interpolator = AccelerateDecelerateInterpolator()

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(alphaAnimation, translationAnimation)

        animatorSet.start()
    }

    private fun animateTextDown(textView: TextView) {
        val translationAnimation = ObjectAnimator.ofFloat(
            textView,
            PROPERTY_NAME_Y_AXIS,
            -textView.height.toFloat(),
            Y_AXIS_TRANSITIONS_FLOAT
        )

        translationAnimation.duration = DEFAULT_DURATION_DOWN

        val animatorSet = AnimatorSet()
        animatorSet.play(translationAnimation)
        animatorSet.start()
    }

    companion object {
        const val PROPERTY_NAME_Y_AXIS = "translationY"
        const val Y_AXIS_DEFAULT_FLOAT = 16f
        const val Y_AXIS_TRANSITIONS_FLOAT = 0F
        const val Y_AXIS_ALPHA_FLOAT = 1F
        const val PROPERTY_NAME_ALPHA = "alpha"
        const val DEFAULT_DURATION_TOP = 600L
        const val DEFAULT_DURATION_DOWN = 400L
    }
}