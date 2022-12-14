package io.bibuti.core.interfaces

interface ActionsDispatcher<Action : Any> {
    fun action(action: Action)
}