package com.github.xumiaolee.kwallpaper.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * @author xuyj
 * @date 2018/4/20 on 10:55
 * @description TODO
 */

open abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onToolbarBackClick()
        }
        return super.onOptionsItemSelected(item)
    }

    abstract fun onToolbarBackClick()
}