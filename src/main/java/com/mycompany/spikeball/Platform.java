package com.mycompany.spikeball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform extends Rectangle {//The platform class which extends the rectangle class 

    private static final long serialVersionUID = 1L;//Conventions used for the Rectangle class
    private static final int size = 42;//The size of the platforms

    public Platform(int x, int y) {//The constructor which declares the platform 
        setBounds(x, y, size, size);
    }

    public void draw(Graphics g) {//The method which draws the platform
        g.setColor(Color.lightGray);
        g.fillRect(x, y, size, size);
    }
}
