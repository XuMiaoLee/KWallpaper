package com.github.xumiaolee.kwallpaper.fragments

import android.view.View
import com.github.xumiaolee.kwallpaper.R
import com.github.xumiaolee.kwallpaper.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_portrait.*

/**
 * @author xuyj
 * @date 2018/4/23 on 11:50
 * @description TODO
 */

class ComputerFragment : BaseFragment() {
    override fun lazyLoad() {

    }

    override fun initViews(view: View?) {
        val tabs: Array<String> = resources.getStringArray(R.array.protrait_tabls)
        //添加Tab
        tabs.forEach { tabLayout.addTab(tabLayout.newTab()) }
        //设置Tab文字
        tabs.forEachIndexed { index, s -> tabLayout.getTabAt(index)?.text = s }
    }

    override fun getLayoutId(): Int = R.layout.fragment_portrait
}