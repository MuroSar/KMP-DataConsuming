package com.murosar.kmp.dataconsuming

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform