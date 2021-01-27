package com.gmaniliapp.flappyhermes.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<com.gmaniliapp.flappyhermes.state.State> states;

    public GameStateManager() {
        states = new Stack<>();
    }

    public void push(com.gmaniliapp.flappyhermes.state.State state) {
        states.push(state);
    }

    public void pop() {
        states.pop().dispose();
    }

    public void set(State state) {
        pop();
        push(state);
    }

    public void update(float deltaTime) {
        states.peek().update(deltaTime);
    }

    public void render(SpriteBatch spriteBatch) {
        states.peek().render(spriteBatch);
    }
}
