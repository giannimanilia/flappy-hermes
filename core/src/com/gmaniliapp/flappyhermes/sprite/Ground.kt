package com.gmaniliapp.flappyhermes.sprite

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Ground(x: Float, index: Int) {

    private val bounds: Rectangle

    val texture: Texture = Texture("ground.png")
    val position: Vector2

    fun reposition(x: Float) {
        position[x] = GROUND_OFFSET.toFloat()
        bounds.setPosition(position.x, position.y)
    }

    fun collides(rectangle: Rectangle): Boolean {
        return rectangle.overlaps(bounds)
    }

    fun dispose() {
        texture.dispose()
    }

    companion object {
        private const val GROUND_OFFSET = -30
    }

    init {
        position = Vector2(x + index * texture.width, GROUND_OFFSET.toFloat())
        bounds = Rectangle(position.x, position.y, texture.width.toFloat(), texture.height.toFloat())
    }
}