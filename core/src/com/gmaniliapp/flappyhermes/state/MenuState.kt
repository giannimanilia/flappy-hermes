package com.gmaniliapp.flappyhermes.state

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.gmaniliapp.flappyhermes.FlappyHermes

class MenuState(gameStateManager: GameStateManager) : State(gameStateManager) {

    private val background: Texture
    private val startButton: Texture

    override fun update(deltaTime: Float) {
        handleInput()
    }

    override fun render(spriteBatch: SpriteBatch) {
        spriteBatch.projectionMatrix = orthographicCamera.combined
        spriteBatch.begin()
        spriteBatch.draw(background, 0f, 0f)
        spriteBatch.draw(startButton, orthographicCamera.position.x - startButton.width / 2f,
                orthographicCamera.position.y)
        spriteBatch.end()
    }

    override fun dispose() {
        background.dispose()
        startButton.dispose()
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(PlayState(gameStateManager))
        }
    }

    init {
        orthographicCamera.setToOrtho(false, FlappyHermes.WIDTH / 2f, FlappyHermes.HEIGHT / 2f)
        background = Texture("background.png")
        startButton = Texture("start_button.png")
    }
}