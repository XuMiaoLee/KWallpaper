package com.github.xumiaolee.kwallpaper.fragments

import android.view.View
import com.github.xumiaolee.kwallpaper.R
import com.github.xumiaolee.kwallpaper.base.BaseFragment

/**
 * @author xuyj
 * @date 2018/4/23 on 11:50
 * @description TODO
 */

class ComputerFragment : BaseFragment() {
    override fun lazyLoad() {

    }

    override fun initViews(view: View?) {
//        val tabsTitle: Array<String> = resources.getStringArray(R.array.protrait_tabls)
//        val fragments = arrayListOf<Fragment>(PortraitNewsFragment(), PortraitHotsFragment(), PortraitCategoryFragment())
//        val tabsAdapter = TabsAdapter(activity.supportFragmentManager, fragments, tabsTitle)
//        viewPager.adapter = tabsAdapter
//        tabLayout.setupWithViewPager(viewPager)
    }

    override fun getLayoutId(): Int = R.layout.fragment_portrait
}