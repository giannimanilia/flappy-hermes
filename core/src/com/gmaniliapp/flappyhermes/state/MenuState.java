package com.gmaniliapp.flappyhermes.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gmaniliapp.flappyhermes.FlappyHermes;

public class MenuState extends State {

    private Texture background;
    private Texture startButton;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);

        orthographicCamera.setToOrtho(false, FlappyHermes.WIDTH / 2, FlappyHermes.HEIGHT / 2);

        background = new Texture("background.png");

        startButton = new Texture("start_button.png");
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

        spriteBatch.begin();

        spriteBatch.draw(background, 0, 0);

        spriteBatch.draw(startButton, orthographicCamera.position.x - startButton.getWidth() / 2,
                orthographicCamera.position.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        startButton.dispose();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }
}
