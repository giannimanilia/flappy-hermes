package com.gmaniliapp.flappyhermes.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*

class GameStateManager {

    private val states: Stack<State> = Stack()

    fun push(state: State) {
        states.push(state)
    }

    fun pop() {
        states.pop().dispose()
    }

    fun set(state: State) {
        pop()
        push(state)
    }

    fun update(deltaTime: Float) {
        states.peek().update(deltaTime)
    }

    fun render(spriteBatch: SpriteBatch) {
        states.peek().render(spriteBatch)
    }

}