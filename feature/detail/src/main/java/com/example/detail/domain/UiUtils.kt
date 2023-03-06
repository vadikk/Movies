package com.example.detail.domain

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton

internal fun TextView?.setCustomText(firstText: String, secondText: String) {
    if (this == null) return
    text = SpannableStringBuilder(firstText).apply {
        val typeFace = Typeface.defaultFromStyle(Typeface.BOLD)
        val startLength = length
        setSpan(
            StyleSpan(typeFace?.style ?: -1),
            0,
            startLength,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        insert(startLength, " $secondText")
    }
}

internal fun View?.showSkeletonAnimation(@ColorRes color: Int, isShow: Boolean, cornerRadius: Float = 30F) {
    if (this == null) return
    apply {
        if (isShow) loadSkeleton {
            color(color)
            cornerRadius(cornerRadius)
            shimmer(true)
        }
        else hideSkeleton()
    }
}