package com.gmaniliapp.flappyhermes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gmaniliapp.flappyhermes.FlappyHermes;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyHermes.WIDTH;
		config.height = FlappyHermes.HEIGHT;
		config.title = FlappyHermes.TITLE;
		new LwjglApplication(new FlappyHermes(), config);
	}
}
