package com.murosar.kmp.dataconsuming

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.murosar.kmp.dataconsuming.database.getTextTransformationDatabase
import com.murosar.kmp.dataconsuming.datastore.createDataStore
import com.murosar.kmp.dataconsuming.networking.InsultCensorClient
import com.murosar.kmp.dataconsuming.networking.createHttpClient
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController {
    val textTransformationDao = remember {
        getTextTransformationDatabase().textTransformationDao()
    }
    App(
        client = remember {
            InsultCensorClient(createHttpClient(Darwin.create()))
        },
        prefs = remember {
            createDataStore()
        },
        textTransformationDao = textTransformationDao
    )
}