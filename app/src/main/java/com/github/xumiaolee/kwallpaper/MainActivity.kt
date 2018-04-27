package com.github.xumiaolee.kwallpaper

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.MenuItem
import com.github.xumiaolee.kwallpaper.base.BaseFragment
import com.github.xumiaolee.kwallpaper.fragments.AvatarFragment
import com.github.xumiaolee.kwallpaper.fragments.ComputerFragment
import com.github.xumiaolee.kwallpaper.fragments.PortraitFragment
import com.github.xumiaolee.kwallpaper.utils.ActivityUtils
import com.github.xumiaolee.kwallpaper.utils.ColorStateListUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mCurrentFragment: Fragment
    private lateinit var mFragmentManger: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(android.R.color.transparent)
        }
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initDefaultFragment()
    }

    private fun initDefaultFragment() {
        mFragmentManger = supportFragmentManager
        mCurrentFragment = ActivityUtils.createFragment(PortraitFragment::class.java)
        mFragmentManger.beginTransaction().add(R.id.container, mCurrentFragment).commit()
        nav_view.setCheckedItem(R.id.nav_portrait)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
            System.exit(0)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_theme -> {
                startActivityForResult(Intent(this, ThemeActivity::class.java), 0)
            }
            R.id.nav_portrait -> {
                switchFragments(PortraitFragment::class.java, "手机壁纸")
            }
            R.id.nav_computer -> {
                switchFragments(ComputerFragment::class.java, "电脑壁纸")
            }
            R.id.nav_avatar -> {
                switchFragments(AvatarFragment::class.java, "头像")
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * 切换fragments
     */
    private fun switchFragments(clazz: Class<*>, title: String) {
        toolbar.title = title
        val fragment = ActivityUtils.createFragment(clazz)
        //如果点击的是同一个不进行处理
        if (fragment == mCurrentFragment) return
        //判断该Fragment是否添加
        if (fragment.isAdded)
            mFragmentManger.beginTransaction().hide(mCurrentFragment).show(fragment).commitAllowingStateLoss()
        else
            mFragmentManger.beginTransaction().hide(mCurrentFragment).add(R.id.container, fragment).commitAllowingStateLoss()
        mCurrentFragment = fragment
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            //切换主题，更改颜色
            setTheme(ThemeHelper.init(this).getThemeRes())
            val colorPrimary = TypedValue()
            val colorPrimaryDark = TypedValue()
            val colorNavBackground = TypedValue()
            theme.resolveAttribute(R.attr.colorPrimary, colorPrimary, true)
            theme.resolveAttribute(R.attr.colorPrimaryDark, colorPrimaryDark, true)
            theme.resolveAttribute(R.attr.colorNavBackground, colorNavBackground, true)
            toolbar.setBackgroundResource(colorPrimary.resourceId)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //设置状态栏颜色
                drawer_layout.setStatusBarBackground(colorPrimaryDark.resourceId)
            }
            //设置NavigationView头部颜色
            nav_head.setBackgroundResource(colorNavBackground.resourceId)
            //设置NavigationView Item点击颜色
            nav_view.itemTextColor = ColorStateListUtils.createrColorStateList(colorPrimary.data)
            nav_view.itemIconTintList = ColorStateListUtils.createrColorStateList(colorPrimary.data)

            (mCurrentFragment as BaseFragment).changeTabLayoutTheme(colorPrimary.resourceId)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}
