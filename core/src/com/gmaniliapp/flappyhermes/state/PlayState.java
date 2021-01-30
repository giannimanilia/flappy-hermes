package com.gmaniliapp.flappyhermes.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.gmaniliapp.flappyhermes.FlappyHermes;
import com.gmaniliapp.flappyhermes.sprite.Column;
import com.gmaniliapp.flappyhermes.sprite.Ground;
import com.gmaniliapp.flappyhermes.sprite.Sandal;

public class PlayState extends State {

    private static final int COLUMN_SPACING = 125;
    private static final int COLUMN_COUNT = 4;

    private static final int GROUND_COUNT = 2;

    private Texture background;

    private Array<Ground> grounds;

    private Sandal sandal;

    private Array<Column> columns;

    private Sound hit;
    private Sound point;

    protected PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        orthographicCamera.setToOrtho(false, FlappyHermes.WIDTH / 2, FlappyHermes.HEIGHT / 2);

        background = new Texture("background.png");

        grounds = new Array<>();
        for (int i = 0; i < GROUND_COUNT; i++) {
            grounds.add(new Ground(
                    orthographicCamera.position.x - orthographicCamera.viewportWidth / 2, i));
        }

        sandal = new Sandal(50, 320);

        columns = new Array<>();

        for (int i = 1; i <= COLUMN_COUNT; i++) {
            columns.add(new Column(i * (COLUMN_SPACING + Column.WIDTH)));
        }

        hit = Gdx.audio.newSound(Gdx.files.internal("hit.ogg"));
        point = Gdx.audio.newSound(Gdx.files.internal("point.ogg"));
    }

    @Override
    public void update(float deltaTime) {
        handleInput();

        for (int i = 0; i < grounds.size; i++) {
            Ground ground = grounds.get(i);

            if (orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 >
                    ground.getPosition().x + ground.getTexture().getWidth()) {
                ground.reposition(
                        ground.getPosition().x + ground.getTexture().getWidth() * GROUND_COUNT);
            }
            if (ground.collides(sandal.getBounds())) {
                hit.play();
                gameStateManager.set(new MenuState(gameStateManager));
            }
        }

        sandal.update(deltaTime);

        orthographicCamera.position.x = sandal.getPosition().x + 80;

        for (int i = 0; i < columns.size; i++) {
            Column column = columns.get(i);

            if (orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 >
                    column.getPositionTop().x + column.getTop().getWidth()) {
                column.reposition(
                        column.getPositionTop().x +
                                ((Column.WIDTH + COLUMN_SPACING) * COLUMN_COUNT));
                point.play();
            }
            if (column.collides(sandal.getBounds())) {
                hit.play();
                gameStateManager
                        .set(new com.gmaniliapp.flappyhermes.state.MenuState(gameStateManager));
            }
        }

        orthographicCamera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(orthographicCamera.combined);

        spriteBatch.begin();

        spriteBatch.draw(background,
                orthographicCamera.position.x - (orthographicCamera.viewportWidth / 2),
                orthographicCamera.position.y - (orthographicCamera.viewportHeight / 2));

        spriteBatch.draw(sandal.getTexture(), sandal.getPosition().x, sandal.getPosition().y);

        for (Column column : columns) {
            spriteBatch.draw(column.getTop(), column.getPositionTop().x, column.getPositionTop().y);
            spriteBatch
                    .draw(column.getBottom(), column.getPositionBottom().x,
                            column.getPositionBottom().y);
        }

        for (Ground ground : grounds) {
            spriteBatch.draw(ground.getTexture(), ground.getPosition().x, ground.getPosition().y);
        }

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        for (Ground ground : grounds) {
            ground.dispose();
        }
        sandal.dispose();
        for (Column column : columns) {
            column.dispose();
        }
        hit.dispose();
        point.dispose();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            sandal.jump();
        }
    }
}
