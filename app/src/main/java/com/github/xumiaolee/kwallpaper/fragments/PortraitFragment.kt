package com.github.xumiaolee.kwallpaper.fragments

import android.support.v4.app.Fragment
import android.view.View
import com.github.xumiaolee.kwallpaper.R
import com.github.xumiaolee.kwallpaper.adapter.TabsAdapter
import com.github.xumiaolee.kwallpaper.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_portrait.*

/**
 * @author xuyj
 * @date 2018/4/19 on 15:45
 * @description TODO
 */

class PortraitFragment : BaseFragment() {
    override fun lazyLoad() {
    }

    override fun initViews(view: View?) {

        val tabsTitle: Array<String> = resources.getStringArray(R.array.protrait_tabls)
        //设置Tab文字
        tabsTitle.forEachIndexed { index, s -> tabLayout.getTabAt(index)?.text = s }
        val fragments = arrayListOf<Fragment>(PortraitNewsFragment(), PortraitHotsFragment(), PortraitCategoryFragment())
        val tabsAdapter = TabsAdapter(activity.supportFragmentManager, fragments, tabsTitle)
        viewPager.adapter = tabsAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun getLayoutId(): Int = R.layout.fragment_portrait


}