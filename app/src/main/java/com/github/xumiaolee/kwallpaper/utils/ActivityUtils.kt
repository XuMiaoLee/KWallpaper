package com.github.xumiaolee.kwallpaper.utils

import android.support.v4.app.Fragment
import java.util.*

/**
 * @author xuyj
 * @date 2018/4/23 on 11:27
 * @description TODO
 */
object ActivityUtils {

    private val fragments: HashMap<String, Fragment> = HashMap()

    fun createFragment(clazz: Class<*>): Fragment {
        val className = clazz.name
        var fragment: Fragment? = null
        if (fragments.containsKey(className)) {
            fragment = fragments[className]
        } else {
            fragment = Class.forName(className).newInstance() as Fragment
            fragments[className] = fragment
        }
        return fragment!!
    }


}