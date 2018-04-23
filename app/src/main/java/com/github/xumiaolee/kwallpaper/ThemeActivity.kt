package com.github.xumiaolee.kwallpaper

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.TypedValue
import android.view.MenuItem
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.xumiaolee.kwallpaper.bean.Theme
import com.github.xumiaolee.kwallpaper.view.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.activity_theme.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import java.util.*

/**
 * @author xuyj
 * @date 2018/4/19 on 15:45
 * @description 切换主题
 */

class ThemeActivity : AppCompatActivity() {

    /**
     * RecyclerView列数
     */
    private val MAX_SPAN_COUNT: Int = 3
    /**
     * 选中的index
     */
    private var mPreCheckedIndex: Int = 0

    private lateinit var mAdapter: ThemeAdapter

    /**
     * Adapter填充数据
     */
    private lateinit var mThemes: MutableList<Theme>
    /**
     * 主题资源列表
     */
    private val mThemeIntArray: Array<Int> = arrayOf(R.style.AppTheme, R.style.GreenTheme,R.style.BrownTheme)
    /**
     * item颜色
     */
    private val mItemColors: Array<Int> = arrayOf(R.color.colorPrimary, R.color.colorGreen,R.color.colorBrown)
    /**
     * 原始index,用于返回的时候判断是否更换了主题
     */
    private var mOriginIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "主题"
        initView()
        initData()

    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(this, MAX_SPAN_COUNT)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addItemDecoration(GridSpacingItemDecoration(MAX_SPAN_COUNT, 3, true))
        //删除动画，解决notifyItemChanged刷新动画闪烁问题
        recyclerView.itemAnimator.changeDuration = 0
        mAdapter = ThemeAdapter()
        recyclerView.adapter = mAdapter

        //添加item点击事件
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            mThemes[mPreCheckedIndex].checked = false
            mThemes[position].checked = true
            //如果点击同一个主题不做刷新和保存操作
            if (mPreCheckedIndex != position) {
                mAdapter.notifyItemChanged(position)
                mAdapter.notifyItemChanged(mPreCheckedIndex)
                updateTheme(position)

            }
            mPreCheckedIndex = position
        }
    }

    private fun updateTheme(position: Int) {
        setTheme(mThemeIntArray[position])
        val colorPrimary = TypedValue()
        val colorPrimaryDark = TypedValue()
        theme.resolveAttribute(R.attr.colorPrimary, colorPrimary, true)
        theme.resolveAttribute(R.attr.colorPrimaryDark, colorPrimaryDark, true)
        toolbar.setBackgroundResource(colorPrimary.resourceId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(colorPrimaryDark.resourceId)
        }
        //保存主题资源和当前item的index
        ThemeHelper.init(this).saveCurrentTheme(mThemeIntArray[position], position)
    }

    private fun initData() {
        //恢复选中的主题index
        mPreCheckedIndex = ThemeHelper.init(this).getSelectedIndex()
        mOriginIndex = ThemeHelper.init(this).getSelectedIndex()
        mThemes = ArrayList()
        mItemColors.forEachIndexed { index, _ ->
            mThemes.add(Theme(mItemColors[index], mPreCheckedIndex == index))
        }
        mAdapter.setNewData(mThemes)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //判断是否切换了主题，否则不通知首页切换
        if (mOriginIndex == mPreCheckedIndex) {
            finish()
        } else {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
