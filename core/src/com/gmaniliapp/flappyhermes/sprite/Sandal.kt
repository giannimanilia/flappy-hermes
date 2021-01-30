package com.gmaniliapp.flappyhermes.sprite

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.Array

class Sandal(x: Int, y: Int) {

    private val velocity: Vector3 = Vector3(0f, 0f, 0f)
    private val frames: Array<Texture> = Array()
    private val frameCount: Int
    private var currentFrameIndex: Int
    private val maxFrameTime: Float
    private var currentFrameTime = 0f
    private val wing: Sound

    val position: Vector3 = Vector3(x.toFloat(), y.toFloat(), 0f)

    val bounds: Rectangle

    fun update(deltaTime: Float) {
        currentFrameTime += deltaTime
        if (currentFrameTime > maxFrameTime) {
            currentFrameIndex++
            currentFrameTime = 0f
        }
        if (currentFrameIndex >= frameCount) {
            currentFrameIndex = 0
        }
        if (position.y > 0) {
            velocity.add(0f, GRAVITY.toFloat(), 0f)
        }
        velocity.scl(deltaTime)
        position.add(SPEED * deltaTime, velocity.y, 0f)
        if (position.y < 0) {
            position.y = 0f
        }
        velocity.scl(1 / deltaTime)
        bounds.setPosition(position.x, position.y)
    }

    fun jump() {
        velocity.y = 300f
        wing.play()
    }

    fun dispose() {
        for (i in 0 until frames.size) {
            frames[i].dispose()
        }
        wing.dispose()
    }

    val texture: Texture
        get() = frames[currentFrameIndex]

    companion object {
        private const val GRAVITY = -15
        private const val SPEED = 100
    }

    init {
        frames.add(Texture("sandal_downflap.png"))
        frames.add(Texture("sandal_midflap.png"))
        frames.add(Texture("sandal_upflap.png"))
        frameCount = frames.size
        currentFrameIndex = 0
        maxFrameTime = 0.5f / frameCount
        bounds = Rectangle(x.toFloat(), y.toFloat(), texture.width.toFloat(), texture.height.toFloat())
        wing = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"))
    }
}