package com.example.appkeytest

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils

object Utils {
    fun getAppMetaData(context: Context?, key: String): String? {
        if (context == null || TextUtils.isEmpty(key)) {
            return null
        }
        var channelNumber: String? = null
        try {
            val packageManager = context.packageManager
            if (packageManager != null) {
                val applicationInfo = packageManager.getApplicationInfo(
                    context.packageName,
                    PackageManager.GET_META_DATA
                )
                if (applicationInfo.metaData != null) {
                    channelNumber = applicationInfo.metaData.getString(key)
                    if (TextUtils.isEmpty(channelNumber)){
                        channelNumber = applicationInfo.metaData.getInt(key).toString()
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return channelNumber
    }
}