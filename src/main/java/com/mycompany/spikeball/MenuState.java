package com.mycompany.spikeball;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuState extends GameState {//An extention of GameState, contains all of the methods of GameState

    private String title = "Spike Ball";//Holding the string title 
    private String[] options = {"Start", "Instructions", "Quit"};//Holding the string options
    private int currentSelection = 0;//Declaring an integer for current selection
    Font pixelMplus;//Declaring a new font

    public MenuState(GameStateManager gsm) {//The cunstructor which updates the game state manager to this instance
        super(gsm);
    }

    @Override
    public void init() {//No initialization needed
    }

    @Override
    public void tick() {//No calculations needed 
    }

    @Override
    public void draw(Graphics g) {//Draws all the necessary components of the menue state

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);//Draws the background

        for (int i = 0; i < options.length; i++) {//This loop prints the options and allows the user to select one of the option
            if (i == currentSelection) {//If the selection and the i value match, that color is set to green
                g.setColor(Color.green);
            } else {//Else is set to white
                g.setColor(Color.white);
            }

            try {//This try catch locates the custome PAC-FONT for the options
                pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")).deriveFont(30f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")));
            } catch (Exception e) {
            }

            g.setFont(pixelMplus);
            g.drawString(options[i], GamePanel.WIDTH / 5 - 50, 400 + i * 50);//Draws the options

        }

        try {//This try catch locates the custome PAC-FONT for the title
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")));
        } catch (Exception e) {
        }
        g.setColor(Color.white);
        g.setFont(pixelMplus);
        g.drawString(title, GamePanel.WIDTH / 2 - 220, 100);//Draws the title

    }

    @Override
    public void keyPressed(int k) {//The method for keyPressed registeration
        if (k == KeyEvent.VK_DOWN) {//If down is pressed, the current selection increases
            currentSelection++;
            if (currentSelection >= 3) {//If current selection is greater than 2, it is set to 0
                currentSelection = 0;
            }

        } else if (k == KeyEvent.VK_UP) {//If up is pressed, current selection decreases
            currentSelection--;
            if (currentSelection < 0) {//If selection is less than 0, it is set to 2
                currentSelection = 2;
            }
        }

        if (k == KeyEvent.VK_ENTER) {//If enter is pressed
            if (currentSelection == 0) {//If selection is zero, pushes a new level state
                gsm.states.push(new LevelState(gsm));
            } else if (currentSelection == 1) {//If selection is 1, creates a new instructions object and create a new frame 
                Instructions instructions = new Instructions();
                
                JFrame frame = new JFrame("Instructions");
                frame.add(instructions);//Instructions JPanel is added to the new frame 
                frame.setSize(600, 400);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                
            } else if (currentSelection == 2) {//If selection is 2, the program closes
                System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(int k) {
    }

}
