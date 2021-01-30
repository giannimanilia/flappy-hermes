package com.gmaniliapp.flappyhermes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gmaniliapp.flappyhermes.state.GameStateManager;
import com.gmaniliapp.flappyhermes.state.MenuState;

public class FlappyHermes extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 720;

    public static final String TITLE = "Flappy Hermes";

    private SpriteBatch spriteBatch;
    private GameStateManager gameStateManager;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        gameStateManager = new GameStateManager();

        Gdx.gl.glClearColor(0, 1, 0, 1);

        gameStateManager.push(new MenuState(gameStateManager));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(spriteBatch);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
