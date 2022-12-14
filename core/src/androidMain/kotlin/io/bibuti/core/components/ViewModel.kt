package io.bibuti.core.components

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

actual open class ViewModel actual constructor() : ViewModel() {

    protected actual val viewModelScope: CoroutineScope
        get() = createViewModelScope()

    public actual override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}