package com.gmaniliapp.flappyhermes.state

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class State protected constructor(protected var gameStateManager: GameStateManager) {

    protected var orthographicCamera: OrthographicCamera = OrthographicCamera()

    abstract fun update(deltaTime: Float)

    abstract fun render(spriteBatch: SpriteBatch)

    abstract fun dispose()

    protected abstract fun handleInput()

}