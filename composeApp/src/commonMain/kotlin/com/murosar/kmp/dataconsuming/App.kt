package com.murosar.kmp.dataconsuming

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.murosar.kmp.dataconsuming.networking.InsultCensorClient
import com.murosar.kmp.dataconsuming.util.NetworkError
import com.murosar.kmp.dataconsuming.util.onError
import com.murosar.kmp.dataconsuming.util.onSuccess
import io.ktor.util.date.getTimeMillis
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    client: InsultCensorClient,
    prefs: DataStore<Preferences>
) {
    val transformations by prefs
        .data
        .map {
            it.asMap().values.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(", ", "\n")
        }
        .collectAsState("")

    val scope = rememberCoroutineScope()

    MaterialTheme {
        var censoredText by remember {
            mutableStateOf<String?>(null)
        }
        var uncensoredText by remember {
            mutableStateOf("")
        }
        var isLoading by remember {
            mutableStateOf(false)
        }
        var errorMessage by remember {
            mutableStateOf<NetworkError?>(null)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = "Example of how to call a service with Ktor",
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
                Text(
                    text = "(The API censor insults)",
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
                TextField(
                    value = uncensoredText,
                    onValueChange = { uncensoredText = it },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    placeholder = {
                        Text("Uncensored text")
                    }
                )
                Button(onClick = {
                    scope.launch {
                        isLoading = true
                        errorMessage = null

                        client.censorWords(uncensoredText)
                            .onSuccess {
                                censoredText = it
                            }
                            .onError {
                                errorMessage = it
                            }
                        isLoading = false
                    }
                }) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(15.dp),
                            strokeWidth = 1.dp,
                            color = Color.White
                        )
                    } else {
                        Text("Censor!")
                    }
                }
                censoredText?.let {
                    Text(it)
                }
                errorMessage?.let {
                    Text(
                        text = it.name,
                        color = Color.Red
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.LightGray),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = "Example of how to use a Preference DataStore",
                    textAlign = TextAlign.Center,
                    color = Color.Red
                )
                Button(onClick = {
                    scope.launch {
                        prefs.edit { dataStore ->
                            val counterKey = stringPreferencesKey(getTimeMillis().toString())
                            dataStore[counterKey] = "$uncensoredText --> $censoredText"
                        }
                    }
                }) {
                    Text("Save!")
                }
                Text(
                    text = transformations,
                    textAlign = TextAlign.Center,
                    color = Color.Blue
                )
            }
        }
    }
}