package io.bibuti.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform