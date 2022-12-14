package io.bibuti.samplemvi

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform