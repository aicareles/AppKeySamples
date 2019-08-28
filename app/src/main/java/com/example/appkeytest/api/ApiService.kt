package com.example.appkeytest.api

import com.example.appkeytest.AppInfo
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 */
interface ApiService {

    @POST("getAppKey")
    fun getAppKey(@Query("appId")appId:String?):Observable<BaseResponse<AppInfo>>

}