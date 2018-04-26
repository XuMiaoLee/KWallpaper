package com.github.xumiaolee.kwallpaper.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @author xuyj
 * @date 2018/4/26 on 17:11
 * @description
 */

class TabsAdapter(fm: FragmentManager, val fragments: List<Fragment>, val titles: Array<String>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}