package com.mycompany.spikeball;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Instructions extends JPanel {//The class which extends from the JPanel and contains the instructions

    public void paintComponent(Graphics g) {//The method which draws the instructions on the instructions frame
        g.drawString("CONTROLS", 30, 30);
        g.drawString("UP Arrow Key -- Jump", 30, 50);
        g.drawString("LEFT Arrow Key -- Move Left", 30, 70);
        g.drawString("RIGHT Arrow Key -- Move Right", 30, 90);
        g.drawString("ENTER Key -- Restart Game", 30, 110);

        g.drawString("HOW TO PLAY", 230, 30);
        g.drawString("Control the black square to evade the spike ball", 230, 50);
        g.drawString("Your score is shown on the top left", 230, 70);
        g.drawString("The score increases by 1 every time the ball bounces", 230, 90);
        g.drawString("As time goes on, the spike ball gets bigger and faster", 230, 110);

        g.drawString("TIPS & TRICKS", 30, 140);
        g.drawString("Use the white platforms to your advantage", 30, 160);
        g.drawString("Do not jump constantly as your jumps will become weaker", 30, 180);
        g.drawString("Try to predict the motion of the ball", 30, 200);

        g.drawString("GOOD LCUK! ", 230, 300);
        g.drawString("     YOU GOT THIS! ", 230, 320);

    }
}
