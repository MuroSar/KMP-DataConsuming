package com.murosar.kmp.dataconsuming

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-DataConsuming",
    ) {
        App()
    }
}