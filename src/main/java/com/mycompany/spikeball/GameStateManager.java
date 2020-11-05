package com.mycompany.spikeball;

import java.awt.Graphics;
import java.util.Stack;

public class GameStateManager {//The class used to manage what state and stack the program is in (MenueState, GameState, LevelState)

    public Stack<GameState> states;//Creating the stack of the states

    public GameStateManager() {//The constructor of this class
        states = new Stack<GameState>();//Creates a new GameState and pushes the MenueState to this instance
        states.push(new MenuState(this));
    }

    public void tick() {//Does the calculations of the states
        states.peek().tick();
    }

    public void draw(Graphics g) {//Does the drawing of the states
        states.peek().draw(g);
    }

    public void keyPressed(int k) {//Registers the Keypressed of the states
        states.peek().keyPressed(k);
    }

    public void keyReleased(int k) {//Registers the KeyReleased of the states
        states.peek().keyReleased(k);

    }

    public void restart() {//The restart function which pushes a new MenueState to the states stack
        states.push(new MenuState(this));
    }
}
