package com.example.appkeytest

import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.example.appkeytest.api.HttpUtils

class SDK {

    companion object {
        fun init(context: Context){
            val appi_id = Utils.getAppMetaData(context, "com.heaton.advertisersdk.appid")
            val app_key = Utils.getAppMetaData(context, "com.heaton.advertisersdk.appkey")
            //从服务器查询是否存在appid
            HttpUtils.create(HttpUtils.apiService().getAppKey(appi_id)){
                if (it.isSuccess){
                    it.data?.apply{
                        if (!TextUtils.equals(app_key, appKey)){
                            throw SDKInitException("appkey is incorrect")
                        }else {
                            Log.e("SDK-TAG", "sdk init success")
                        }
                    }
                }else if (it.isFail){
                    Log.e("SDK-TAG", "sdk init fail")
                    throw SDKInitException("please register to menifast file appid and appkey")
                }
            }
        }
    }

}