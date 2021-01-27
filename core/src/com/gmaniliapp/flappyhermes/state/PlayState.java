package com.gmaniliapp.flappyhermes.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.gmaniliapp.flappyhermes.FlappyHermes;
import com.gmaniliapp.flappyhermes.sprite.Sandal;
import com.gmaniliapp.flappyhermes.sprite.Column;

public class PlayState extends State {

    private static final int COLUMN_SPACING = 125;
    private static final int COLUMN_COUNT = 4;
    private static final int GROUND_OFFSET = -30;

    private Sandal sandal;

    private Texture background;
    private Texture ground;

    private Vector2 positionGround1;
    private Vector2 positionGround2;

    private Array<Column> columns;

    private Sound hit;
    private Sound point;

    protected PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        orthographicCamera.setToOrtho(false, FlappyHermes.WIDTH / 2, FlappyHermes.HEIGHT / 2);

        sandal = new Sandal(50, 320);

        background = new Texture("background.png");
        ground = new Texture("ground.png");

        positionGround1 =
                new Vector2(orthographicCamera.position.x - orthographicCamera.viewportWidth / 2,
                        GROUND_OFFSET);
        positionGround2 =
                new Vector2(orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 +
                        ground.getWidth(), GROUND_OFFSET);

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

        updateGround();

        sandal.update(deltaTime);

        orthographicCamera.position.x = sandal.getPosition().x + 80;

        for (int i = 0; i < columns.size; i++) {
            Column column = columns.get(i);

            if (orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 >
                    column.getPositionTop().x + column.getTop().getWidth()) {
                column.reposition(
                        column.getPositionTop().x + ((Column.WIDTH + COLUMN_SPACING) * COLUMN_COUNT));
                point.play();
            }
            if (column.collides(sandal.getBounds())) {
                hit.play();
                gameStateManager.set(new com.gmaniliapp.flappyhermes.state.MenuState(gameStateManager));
            }
        }

        if (sandal.getPosition().y <= ground.getHeight() + GROUND_OFFSET) {
            hit.play();
            gameStateManager.set(new MenuState(gameStateManager));
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
                    .draw(column.getBottom(), column.getPositionBottom().x, column.getPositionBottom().y);
        }

        spriteBatch.draw(ground, positionGround1.x, positionGround1.y);
        spriteBatch.draw(ground, positionGround2.x, positionGround2.y);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        sandal.dispose();
        for (Column column : columns) {
            column.dispose();
        }
        ground.dispose();
        hit.dispose();
        point.dispose();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            sandal.jump();
        }
    }

    private void updateGround() {
        if (orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 > positionGround1.x + ground.getWidth()) {
            positionGround1.add(ground.getWidth() * 2, 0);
        }
        if (orthographicCamera.position.x - orthographicCamera.viewportWidth / 2 > positionGround2.x + ground.getWidth()) {
            positionGround2.add(ground.getWidth() * 2, 0);
        }
    }
}
