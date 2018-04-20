package com.github.xumiaolee.kwallpaper.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.github.xumiaolee.kwallpaper.ThemeHelper

/**
 * @author xuyj
 * @date 2018/4/20 on 16:05
 * @description TODO
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                activity?.setTheme(ThemeHelper.init(this@App).getThemeRes())
            }

        })
    }
}