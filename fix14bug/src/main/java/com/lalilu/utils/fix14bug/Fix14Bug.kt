package com.lalilu.utils.fix14bug

import com.bytedance.shadowhook.ShadowHook

object Fix14Bug {
    init {
        System.loadLibrary("fix14bug")
        ShadowHook.init(
            ShadowHook.ConfigBuilder()
                .setMode(ShadowHook.Mode.UNIQUE)
                .build()
        )
    }

    external fun fix14DebugBug()
}