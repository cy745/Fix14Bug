package com.lalilu.utils.fix14bug

import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Build
import android.util.Log
import androidx.startup.Initializer


class StartUp : Initializer<Unit> {
    override fun create(context: Context) {
        if (Build.VERSION.SDK_INT == 34 && (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            Fix14Bug.fix14DebugBug()
            Log.i("Fix14DebugBug", "Fix14DebugBug StartUp")
        }
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return arrayListOf()
    }
}