package com.example.appkeytest.api

import com.example.appkeytest.BuildConfig
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * description $desc$
 * created by jerry on 2019/4/23.
 * object 指静态类
 */
object HttpUtils {
    private val instance by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl("https://apecoder.top/")
                .build()
        retrofit.create(ApiService::class.java)
    }

    fun apiService(): ApiService {
        return instance
    }

    fun <T> create(observable: Observable<BaseResponse<T>>, listener: (Resource<T>) -> Unit){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(BaseSubscriber<T>(listener))
    }
}