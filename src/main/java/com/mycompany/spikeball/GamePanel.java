package com.mycompany.spikeball;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {//This class is an extention of the JPanel, a built in class for java, the class implements Runnable and KeyListener

    private static final long serialVersionUID = 1L;//Conventions when implementing the Runnable class

    public static final int HEIGHT = 600;//Setting the width and the heigth of the frame
    public static final int WIDTH = 800;

    private Thread thread;//Creating a private thread
    private boolean isRunning = false;//Creating a private boolean which will determine if the program is running or not

    private int FPS = 100;//Setting the FPS
    private long targetTime = 1000 / FPS;//This long value is used to set a standard for the time that the program will run on, creating a consistent expereince across all computers

    private GameStateManager gsm;//A composition of the GameStateManager class

    public GamePanel() {//The constructor of this class which creates the dimensions of the JPanel and adds the keyListener
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        start();//Calls the start method
        
    }

    private void start() {//Starts a new thread and sets isRunning to true so that the main game loop runs
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {//This method contains the main game loop

        long start, elapsed, wait;//Declaring variables which will be used to regulate the FPS of the monitor

        gsm = new GameStateManager();//Declaring a new game state manager

        while (isRunning) {//The main game loop of the program
            start = System.nanoTime();//Starts a timer

            tick();//calls the tick method, used to do all the game calculations
            repaint();//calls the repaint method, which restart the game panel for a new frame

            elapsed = System.nanoTime() - start;//Gets the elapsed time
            wait = targetTime - elapsed / 1000000;//Gets the wait time in mili seconds 

            if (wait < 0) {//If the program is finishing too fast (wait is less than zero), wait is set to 5
                wait = 5;
            }

            try {//This try catch is used to stop the program for mili seconds to make the experince consistent across different processsors
                Thread.sleep(wait);//Stops the program for the desired amount
            } catch (Exception e) {//If there is an error, prints the error
                e.printStackTrace();
            }
        }
        
    }

    public void tick() {//This method will do all the calculations per frame
        gsm.tick();
    }

    @Override
    public void paintComponent(Graphics g) {//This method will paint all the desired graphics per frame
        super.paintComponent(g);
        
        g.clearRect(0,0,WIDTH,HEIGHT);//This clears the screen 
        
        gsm.draw(g);//This draws all the desired components
    }

    @Override
    public void keyTyped(KeyEvent e) {//The method used to register typing 
    }

    @Override
    public void keyPressed(KeyEvent e) {//The method used to register pressing 
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {//The method used to register releasing 
        gsm.keyReleased(e.getKeyCode());
    }

}
