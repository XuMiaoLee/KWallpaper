package com.github.xumiaolee.kwallpaper.base

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_portrait.*

/**
 * @author xuyj
 * @date 2018/4/19 on 15:47
 * @description 懒加载封装
 */

abstract class BaseFragment : Fragment() {

    /** 视图是否加载完毕 */
    private var isViewPrepare = false
    /** 数据是否加载过了*/
    private var hasLoadData = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutId(), container, false)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        lazyLoadDataIfPrepared()
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            initViews(view)
            hasLoadData = true
        }
    }

    fun changeTabLayoutTheme(@ColorRes colorPrimary: Int) {
        tabLayout?.setSelectedTabIndicatorColor(context!!.resources.getColor(colorPrimary))
    }

    abstract fun lazyLoad()

    abstract fun initViews(view: View?)

    @LayoutRes
    abstract fun getLayoutId(): Int
}