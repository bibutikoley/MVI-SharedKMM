package io.bibuti.core.interfaces

interface StateMapper<State : Any, RenderState : Any> {
    fun mapToRenderState(state: State): RenderState
}