package com.gmaniliapp.flappyhermes.sprite

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.util.*

class Column(x: Float) {

    private val random: Random = Random()
    private val boundsTop: Rectangle
    private val boundsBottom: Rectangle

    val top: Texture = Texture("column_top.png")
    val bottom: Texture = Texture("column_bottom.png")
    val positionTop: Vector2
    val positionBottom: Vector2

    fun reposition(x: Float) {
        positionTop[x] = (random.nextInt(FLUCTUATION) + GAP + LOWEST_OPENING).toFloat()
        positionBottom[x] = positionTop.y - GAP - bottom.height
        boundsTop.setPosition(positionTop.x, positionTop.y)
        boundsBottom.setPosition(positionBottom.x, positionBottom.y)
    }

    fun collides(rectangle: Rectangle): Boolean {
        return rectangle.overlaps(boundsTop) || rectangle.overlaps(boundsBottom)
    }

    fun dispose() {
        top.dispose()
        bottom.dispose()
    }

    companion object {
        const val WIDTH = 52
        private const val FLUCTUATION = 130
        private const val GAP = 100
        private const val LOWEST_OPENING = 120
    }

    init {
        positionTop = Vector2(x, (random.nextInt(FLUCTUATION) + GAP + LOWEST_OPENING).toFloat())
        positionBottom = Vector2(x, positionTop.y - GAP - bottom.height)
        boundsTop = Rectangle(positionTop.x, positionTop.y, top.width.toFloat(), top.height.toFloat())
        boundsBottom = Rectangle(positionBottom.x, positionBottom.y, bottom.width.toFloat(),
                bottom.height.toFloat())
    }
}