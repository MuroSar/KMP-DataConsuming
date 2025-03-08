package com.murosar.kmp.dataconsuming

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.murosar.kmp.dataconsuming.database.getTextTransformationDatabase
import com.murosar.kmp.dataconsuming.datastore.DATA_STORE_FILE_NAME
import com.murosar.kmp.dataconsuming.datastore.createDataStore
import com.murosar.kmp.dataconsuming.networking.InsultCensorClient
import com.murosar.kmp.dataconsuming.networking.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp

fun main() = application {
    val prefs = createDataStore { DATA_STORE_FILE_NAME }

    val textTransformationDao = getTextTransformationDatabase().textTransformationDao()

    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-DataConsuming",
    ) {
        App(
            client = remember {
                InsultCensorClient(createHttpClient(OkHttp.create()))
            },
            prefs = prefs,
            textTransformationDao = textTransformationDao
        )
    }
}