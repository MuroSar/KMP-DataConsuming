package com.murosar.kmp.dataconsuming

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.murosar.kmp.dataconsuming.networking.InsultCensorClient
import com.murosar.kmp.dataconsuming.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-DataConsuming",
    ) {
        App(
            client = remember {
                InsultCensorClient(createHttpClient(OkHttp.create()))
            }
        )
    }
}