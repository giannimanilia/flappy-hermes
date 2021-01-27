package com.gmaniliapp.flappyhermes.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Sandal {

    private static final int GRAVITY = -15;
    private static final int SPEED = 100;

    private Vector3 position;
    private Vector3 velocity;

    private Array<Texture> frames;

    private int frameCount;
    private int currentFrameIndex;

    private float maxFrameTime;
    private float currentFrameTime;

    private Rectangle bounds;

    private Sound wing;

    public Sandal(int x, int y) {
        position = new Vector3(x, y, 0);

        velocity = new Vector3(0, 0, 0);

        frames = new Array<>();
        frames.add(new Texture("sandal_downflap.png"));
        frames.add(new Texture("sandal_midflap.png"));
        frames.add(new Texture("sandal_upflap.png"));

        frameCount = frames.size;
        currentFrameIndex = 0;
        maxFrameTime = 0.5f / frameCount;

        bounds = new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight());

        wing = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));
    }

    public void update(float deltaTime) {
        currentFrameTime += deltaTime;
        if (currentFrameTime > maxFrameTime) {
            currentFrameIndex++;
            currentFrameTime = 0;
        }
        if (currentFrameIndex >= frameCount) {
            currentFrameIndex = 0;
        }

        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(deltaTime);

        position.add(SPEED * deltaTime, velocity.y, 0);

        if (position.y < 0) {
            position.y = 0;
        }

        velocity.scl(1 / deltaTime);

        bounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = 300;
        wing.play();
    }

    public void dispose() {
        for (int i = 0; i < frames.size; i++) {
            frames.get(i).dispose();
        }
        wing.dispose();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return frames.get(currentFrameIndex);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
