package com.gmaniliapp.flappyhermes.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.gmaniliapp.flappyhermes.FlappyHermes

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = FlappyHermes.TITLE
        config.width = FlappyHermes.WIDTH
        config.height = FlappyHermes.HEIGHT
        LwjglApplication(FlappyHermes(), config)
    }
}