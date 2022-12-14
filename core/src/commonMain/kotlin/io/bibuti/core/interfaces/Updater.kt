package io.bibuti.core.interfaces

import io.bibuti.core.components.Next

interface Updater<Action : Any, State : Any, SideEffect : SideEffectInterface, Event : Any> {
    fun onNewAction(action: Action, currentState: State): Next<State, SideEffect, Event>
}