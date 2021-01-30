package com.gmaniliapp.flappyhermes

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.gmaniliapp.flappyhermes.state.GameStateManager
import com.gmaniliapp.flappyhermes.state.MenuState

class FlappyHermes : ApplicationAdapter() {

    private var spriteBatch: SpriteBatch? = null
    private var gameStateManager: GameStateManager? = null

    override fun create() {
        spriteBatch = SpriteBatch()
        gameStateManager = GameStateManager()
        Gdx.gl.glClearColor(0f, 1f, 0f, 1f)
        gameStateManager!!.push(MenuState(gameStateManager!!))
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameStateManager!!.update(Gdx.graphics.deltaTime)
        gameStateManager!!.render(spriteBatch!!)
    }

    override fun dispose() {
        spriteBatch!!.dispose()
    }

    companion object {
        const val WIDTH = 480
        const val HEIGHT = 720
        const val TITLE = "Flappy Hermes"
    }
}