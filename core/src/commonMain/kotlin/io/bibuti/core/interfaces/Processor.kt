package io.bibuti.core.interfaces

interface Processor<SideEffect : SideEffectInterface, out Action : Any> {
    suspend fun dispatchSideEffect(effect: SideEffect): Action
}