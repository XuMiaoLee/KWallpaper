package com.github.xumiaolee.kwallpaper

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.xumiaolee.kwallpaper.bean.Theme

/**
 * @author xuyj
 * @date 2018/4/20 on 11:51
 * @description TODO
 */

class ThemeAdapter : BaseQuickAdapter<Theme, BaseViewHolder>(R.layout.listview_item_theme) {
    override fun convert(helper: BaseViewHolder?, item: Theme?) {
        helper?.setBackgroundColor(R.id.colorView, mContext.resources.getColor(item?.colorRes!!))
        helper?.setVisible(R.id.iv_checked, item?.checked!!)
    }
}