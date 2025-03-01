package com.murosar.kmp.dataconsuming

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.murosar.kmp.dataconsuming.networking.InsultCensorClient
import com.murosar.kmp.dataconsuming.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController {
    App(
        client = remember {
            InsultCensorClient(createHttpClient(Darwin.create()))
        }
    )
}