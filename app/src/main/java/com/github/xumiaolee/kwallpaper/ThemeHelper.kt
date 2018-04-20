package com.github.xumiaolee.kwallpaper

import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.StyleRes

/**
 * @author xuyj
 * @date 2018/4/20 on 16:38
 * @description TODO
 */

object ThemeHelper {
    private const val NAME = "theme"
    private const val THEME_INTRES = "themeIntRes"
    private const val SELECTED_INDEX = "selectedIndex"


    private lateinit var mPrefs: SharedPreferences


    fun init(context: Context): ThemeHelper {
        mPrefs = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        return this
    }


    /**
     * @themeRes 记录切换后的主题
     * @selectedIndex 记录选中的index
     */
    fun saveCurrentTheme(@StyleRes themeRes: Int, selectedIndex: Int) {
        val edit = mPrefs.edit()
        edit.putInt(THEME_INTRES, themeRes)
        edit.putInt(SELECTED_INDEX, selectedIndex)
        edit.apply()
    }

    /**
     * 获取切换的主题
     */
    @StyleRes
    fun getThemeRes(): Int = mPrefs.getInt(THEME_INTRES, -1)

    fun getSelectedIndex(): Int = mPrefs.getInt(SELECTED_INDEX, 0)
}