package com.gmaniliapp.flappyhermes.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Column {

    public static final int WIDTH = 52;

    private static final int FLUCTUATION = 130;
    private static final int GAP = 100;
    private static final int LOWEST_OPENING = 120;

    private Texture top;
    private Texture bottom;

    private Vector2 positionTop;
    private Vector2 positionBottom;

    private Random random;

    private Rectangle boundsTop;
    private Rectangle boundsBottom;

    public Column(float x) {
        top = new Texture("column_top.png");
        bottom = new Texture("column_bottom.png");

        random = new Random();

        positionTop = new Vector2(x, random.nextInt(FLUCTUATION) + GAP + LOWEST_OPENING);
        positionBottom = new Vector2(x, positionTop.y - GAP - bottom.getHeight());

        boundsTop = new Rectangle(positionTop.x, positionTop.y, top.getWidth(), top.getHeight());
        boundsBottom = new Rectangle(positionBottom.x, positionBottom.y, bottom.getWidth(),
                bottom.getHeight());
    }

    public void reposition(float x) {
        positionTop.set(x, random.nextInt(FLUCTUATION) + GAP + LOWEST_OPENING);
        positionBottom.set(x, positionTop.y - GAP - bottom.getHeight());

        boundsTop.setPosition(positionTop.x, positionTop.y);
        boundsBottom.setPosition(positionBottom.x, positionBottom.y);
    }

    public boolean collides(Rectangle rectangle) {
        return rectangle.overlaps(boundsTop) || rectangle.overlaps(boundsBottom);
    }

    public void dispose() {
        top.dispose();
        bottom.dispose();
    }

    public Texture getTop() {
        return top;
    }

    public void setTop(Texture top) {
        this.top = top;
    }

    public Texture getBottom() {
        return bottom;
    }

    public void setBottom(Texture bottom) {
        this.bottom = bottom;
    }

    public Vector2 getPositionTop() {
        return positionTop;
    }

    public void setPositionTop(Vector2 positionTop) {
        this.positionTop = positionTop;
    }

    public Vector2 getPositionBottom() {
        return positionBottom;
    }

    public void setPositionBottom(Vector2 positionBottom) {
        this.positionBottom = positionBottom;
    }
}
