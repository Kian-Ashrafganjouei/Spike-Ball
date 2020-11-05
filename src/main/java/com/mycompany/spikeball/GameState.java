package com.mycompany.spikeball;

import java.awt.Graphics;


public abstract class GameState {//An abstarct class which contains the methods that the different states will use
    protected GameStateManager gsm;//Creating a composition of a GameStateManager, calling it gsm

    public GameState(GameStateManager gsm) {//This constructor sets the gsm to the current instance
        this.gsm = gsm;
        init();//Initializes the the new state
    }
    
    public abstract void init();//The method used for initializing 
    public abstract void tick();//The method used for the calculations 
    public abstract void draw(Graphics g);//The method used for graphics
    public abstract void keyPressed(int k);//The method used for registering keys that are pressed
    public abstract void keyReleased(int k);//The method used for registering keys that are realeased
    
}
