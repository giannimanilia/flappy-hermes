package com.gmaniliapp.flappyhermes.state

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import com.gmaniliapp.flappyhermes.FlappyHermes
import com.gmaniliapp.flappyhermes.sprite.Column
import com.gmaniliapp.flappyhermes.sprite.Ground
import com.gmaniliapp.flappyhermes.sprite.Sandal

class PlayState(gameStateManager: GameStateManager) : State(gameStateManager) {

    private val background: Texture
    private val grounds: Array<Ground>
    private val sandal: Sandal
    private val columns: Array<Column>
    private val hit: Sound
    private val point: Sound

    override fun update(deltaTime: Float) {
        handleInput()
        for (i in 0 until grounds.size) {
            val ground = grounds[i]
            if (orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 >
                    ground.position.x + ground.texture.width) {
                ground.reposition(
                        ground.position.x + ground.texture.width * GROUND_COUNT)
            }
            if (ground.collides(sandal.bounds)) {
                hit.play()
                gameStateManager.set(MenuState(gameStateManager))
            }
        }
        sandal.update(deltaTime)
        orthographicCamera.position.x = sandal.position.x + 80
        for (i in 0 until columns.size) {
            val column = columns[i]
            if (orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 >
                    column.positionTop.x + column.top.width) {
                column.reposition(
                        column.positionTop.x +
                                (Column.WIDTH + COLUMN_SPACING) * COLUMN_COUNT)
                point.play()
            }
            if (column.collides(sandal.bounds)) {
                hit.play()
                gameStateManager
                        .set(MenuState(gameStateManager))
            }
        }
        orthographicCamera.update()
    }

    override fun render(spriteBatch: SpriteBatch) {
        spriteBatch.projectionMatrix = orthographicCamera.combined
        spriteBatch.begin()
        spriteBatch.draw(background,
                orthographicCamera.position.x - orthographicCamera.viewportWidth / 2,
                orthographicCamera.position.y - orthographicCamera.viewportHeight / 2)
        spriteBatch.draw(sandal.texture, sandal.position.x, sandal.position.y)
        for (column in columns) {
            spriteBatch.draw(column.top, column.positionTop.x, column.positionTop.y)
            spriteBatch
                    .draw(column.bottom, column.positionBottom.x,
                            column.positionBottom.y)
        }
        for (ground in grounds) {
            spriteBatch.draw(ground.texture, ground.position.x, ground.position.y)
        }
        spriteBatch.end()
    }

    override fun dispose() {
        background.dispose()
        for (ground in grounds) {
            ground.dispose()
        }
        sandal.dispose()
        for (column in columns) {
            column.dispose()
        }
        hit.dispose()
        point.dispose()
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            sandal.jump()
        }
    }

    companion object {
        private const val COLUMN_SPACING = 125
        private const val COLUMN_COUNT = 4
        private const val GROUND_COUNT = 2
    }

    init {
        orthographicCamera.setToOrtho(false, FlappyHermes.WIDTH / 2f, FlappyHermes.HEIGHT / 2f)
        background = Texture("background.png")
        grounds = Array()
        for (i in 0 until GROUND_COUNT) {
            grounds.add(Ground(
                    orthographicCamera.position.x - orthographicCamera.viewportWidth / 2, i))
        }
        sandal = Sandal(50, 320)
        columns = Array()
        for (i in 1..COLUMN_COUNT) {
            columns.add(Column((i * (COLUMN_SPACING + Column.WIDTH)).toFloat()))
        }
        hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"))
        point = Gdx.audio.newSound(Gdx.files.internal("point.ogg"))
    }
}