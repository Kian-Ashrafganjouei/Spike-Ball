package com.mycompany.spikeball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevelState extends GameState {//The LevelState class which extends the Player class

    private Player player;//Creating a new player 
    private Ball ball;//Creating a new ball
    private Platform[] b;//Creating a polymorphic array of platforms
    private boolean addScore = true;//Boolean function which determine to add score or not
    Font pixelMplus;//Declaring a new font
    ArrayList<Integer> scores = new ArrayList();//Declaring an arraylist for scores

    public LevelState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {//The intialization method which creates all the objects

        player = new Player(30, 30);//Declares the player
        b = new Platform[49];//Declares 49 platforms
        b[0] = new Platform(0, 590);//Give location to those platforms
        b[1] = new Platform(30, 590);
        b[2] = new Platform(60, 590);
        b[3] = new Platform(90, 590);
        b[4] = new Platform(120, 590);
        b[5] = new Platform(150, 590);
        b[6] = new Platform(180, 590);
        b[7] = new Platform(210, 590);
        b[8] = new Platform(240, 590);
        b[9] = new Platform(270, 590);
        b[10] = new Platform(300, 590);
        b[11] = new Platform(330, 590);
        b[12] = new Platform(360, 590);
        b[13] = new Platform(390, 590);
        b[14] = new Platform(420, 590);
        b[15] = new Platform(450, 590);
        b[16] = new Platform(480, 590);
        b[17] = new Platform(510, 590);
        b[18] = new Platform(540, 590);
        b[19] = new Platform(570, 590);
        b[20] = new Platform(600, 590);
        b[21] = new Platform(630, 590);
        b[22] = new Platform(660, 590);
        b[23] = new Platform(690, 590);
        b[24] = new Platform(720, 590);
        b[25] = new Platform(750, 590);
        b[26] = new Platform(780, 590);
        b[27] = new Platform(810, 590);
        b[28] = new Platform(840, 590);

        b[29] = new Platform(130, 450);
        b[30] = new Platform(170, 450);
        b[31] = new Platform(210, 450);
        b[32] = new Platform(250, 450);
        b[33] = new Platform(290, 450);
        b[34] = new Platform(330, 450);
        b[35] = new Platform(370, 450);
        b[36] = new Platform(410, 450);
        b[37] = new Platform(450, 450);
        b[38] = new Platform(490, 450);
        b[39] = new Platform(530, 450);
        b[40] = new Platform(570, 450);

        b[41] = new Platform(170, 310);
        b[42] = new Platform(210, 310);
        b[43] = new Platform(210, 170);
        b[44] = new Platform(250, 170);

        b[45] = new Platform(530, 310);
        b[46] = new Platform(490, 310);
        b[47] = new Platform(450, 170);
        b[48] = new Platform(490, 170);

        ball = new Ball(80, 80);//Declares a new ball
    }

    @Override
    public void tick() {//The method which runs the calculations of the ball and of the player class
        player.tick(b, ball);
        ball.tick();

    }

    @Override
    public void draw(Graphics g) {//The draw method function
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);//Draws  the background

        player.draw(g);//Initiates the player draw method

        for (int i = 0; i < b.length; i++) {
            b[i].draw(g);//Draws the platforms
        }
        ball.draw(g);//Draws the ball
        if (player.isEnd()) {//If the game ends

            try {//Declaring a new custome font
                pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")).deriveFont(50f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")));
            } catch (Exception e) {
            }
            g.setColor(Color.white);
            g.setFont(pixelMplus);
            g.drawString("GAME OVER", GamePanel.WIDTH / 2 - 220, 100);//Drawing the Game Over text
            try {//Declaring a new custome font
                pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")).deriveFont(30f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PAC-FONT.ttf")));
            } catch (Exception e) {
            }
            g.setColor(Color.white);
            g.setFont(pixelMplus);
            g.drawString("Your Score ", GamePanel.WIDTH / 2 - 220, 100 + 50);// Drawing the your Score text
            g.drawString("Your high score is ", GamePanel.WIDTH / 2 - 335, 100 + 200);//Drawing the Your high score text
            Font newFont = new Font("Serif", Font.BOLD, 50);//Declaring the new font
            g.setFont(newFont);
            g.drawString(player.getScore(), GamePanel.WIDTH / 2 + 90, 100 + 60);//Drawing the player score
            g.setColor(Color.darkGray);
            g.fillRect(30, 20, 50, 10);

            for (int i = 0; i < scores.size(); i++) {//This loop runs through the scores arraylist to check if the described score has been achieved or not
                if (scores.get(i) == Integer.parseInt(player.getScore())) {//If the score has been achieved, addScore is set to false 
                    addScore = false;
                }
            }
            if (addScore) {//If addScore is true, the score is added to the arraylist
                scores.add(Integer.parseInt(player.getScore()));
            }
            addScore = true;//Addscore is set to true

            int temp = 0;
            if (scores.size() > 1) //Check if the number of orders is larger than 1
            {
                //This section is a bubble sort which sorts the arraylist from small to big
                for (int x = 0; x < scores.size(); x++) //Bubble sort outer loop
                {
                    for (int i = 0; i < scores.size() - x - 1; i++) {//Bubble sort inner loop
                        if (scores.get(i).compareTo(scores.get(i + 1)) > 0) {
                            temp = scores.get(i);
                            scores.set(i, scores.get(i + 1));
                            scores.set(i + 1, temp);
                        }
                    }
                }
            }
            g.setColor(Color.white);
            g.drawString(Integer.toString(scores.get(scores.size()-1)), GamePanel.WIDTH / 2+130, 100 + 205);//Draws the number at the end of the sorted arraylist, the highest score
        }
    }

    @Override
    public void keyPressed(int k) {//Registers the keypressed
        player.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {//Registers the keyreleased
        player.keyReleased(k);
    }

}
