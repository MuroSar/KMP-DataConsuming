package com.murosar.kmp.dataconsuming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.murosar.kmp.dataconsuming.networking.InsultCensorClient
import com.murosar.kmp.dataconsuming.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(
                client = remember {
                    InsultCensorClient(createHttpClient(OkHttp.create()))
                }
            )
        }
    }
}