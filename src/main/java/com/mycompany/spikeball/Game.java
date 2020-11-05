package com.mycompany.spikeball;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

/*
Kian Ashrafganjouei
mr.Morrison
CPT
Spike Ball
DATE STARTED: June 9th, 2020
LAST UPDATE: June 19th, 2020
 */

public class Game {//The class that contains the main method

    public static void main(String[] args) {//This method creates the JFrame and gives it its characteristics
        JFrame frame = new JFrame("Platformer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new GamePanel(), BorderLayout.CENTER);//Adds the game panel to the JFrame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
