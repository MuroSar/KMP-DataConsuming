package com.murosar.kmp.dataconsuming.networking

import kotlinx.serialization.Serializable

@Serializable
data class CensoredText(
    val result: String
)
