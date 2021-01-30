package com.gmaniliapp.flappyhermes.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground {

    private static final int GROUND_OFFSET = -30;

    private Texture texture;

    private Vector2 position;

    private Rectangle bounds;

    public Ground(float x, int index) {
        texture = new Texture("ground.png");

        position = new Vector2(x + index * texture.getWidth(), GROUND_OFFSET);

        bounds = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void reposition(float x) {
        position.set(x, GROUND_OFFSET);

        bounds.setPosition(position.x, position.y);
    }

    public boolean collides(Rectangle rectangle) {
        return rectangle.overlaps(bounds);
    }

    public void dispose() {
        texture.dispose();
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }
}
