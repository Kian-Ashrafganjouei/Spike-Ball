package com.mycompany.spikeball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class BallHitBox extends Rectangle {//this class extends rectangle and creates a hitbox for the ball

    private static final long serialVersionUID = 1L;//The conventions for the Rectangle class
    private static final int size = 42;//Declaring the size of the square 

    public BallHitBox(int x, int y) {//The method that creates the hit box based on the given location
        setBounds(x, y, 80, 80);
    }   
}
