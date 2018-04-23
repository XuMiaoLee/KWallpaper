package com.github.xumiaolee.kwallpaper.utils

import android.content.res.ColorStateList
import android.graphics.Color

/**
 * @author xuyj
 * @date 2018/4/23 on 11:00
 * @description ColorStateListUtils
 */
object ColorStateListUtils {

    fun createrColorStateList(selectedColor: Int, normalColor: Int = Color.parseColor("#6E6E6E")): ColorStateList {
        val statePressed = android.R.attr.state_checked
        val stateChecked = android.R.attr.state_checked
        val state = arrayOf(intArrayOf(statePressed), intArrayOf(-statePressed), intArrayOf(stateChecked), intArrayOf(-stateChecked))
        val colors = intArrayOf(selectedColor, normalColor, selectedColor, normalColor)
        return ColorStateList(state, colors)
    }
}