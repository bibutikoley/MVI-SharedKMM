package io.bibuti.core.interfaces

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

interface SideEffectInterface {
    val dispatcher: CoroutineDispatcher?
    val coroutineScope: CoroutineScope?
}