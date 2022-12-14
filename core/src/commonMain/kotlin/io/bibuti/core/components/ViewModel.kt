package io.bibuti.core.components

import kotlinx.coroutines.CoroutineScope

expect open class ViewModel() {
    protected val viewModelScope: CoroutineScope
    open fun onCleared()
}