package com.example.appkeytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dianruisdk.ad.AdManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        SDK.init(baseContext)

        AdManager.setAppKey(baseContext, "ssss")
        AdManager.showCenter(this)

    }
}
