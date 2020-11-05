package com.mycompany.spikeball;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;

public class Player {//The player class

    Font pixelMplus;//Declaring a new font
    private boolean right = false, left = false, jumping = false, falling = false, topCollision = false;//Boolean values that assist with player collision and movement 
    private BallHitBox hitBox;//Declaring a composition of the ball hit box

    private String score;//The string value for the score

    private boolean lock = false;//Variables used to lock and reset ceratin methods
    private boolean reset = false;

    private double x, y;//The x an y location of the player
    private int width, height;//The Width and Height of the player

    private double jumpSpeed = 6;//The maximum jump speed
    private double currentJumpSpeed = jumpSpeed;//The variable that describes the current jump speed

    private boolean end = false;//The boolean that determines if the end screen should pop or not

    private double maxFallSpeed = 5;//The varialbe that sets the max fall speed
    private double currentFallSpeed = 0.1;//The variable that sets the current fall speed

    public Player(int width, int height) {//The constructor for player
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2 + 50;
        this.width = width;
        this.height = height;
    }

    public boolean isEnd() {//The getter for end
        return end;
    }

    public String getScore() {//The getter for score, used for resetting 
        return score;
    }

    public void setEnd(boolean end) {//The setter for end
        this.end = end;
    }

    public boolean isLock() {//The getter for lock
        return lock;
    }

    public void tick(Platform[] b, Ball ball) {//The method which does all the calculations for this class
        for (int i = 0; i < b.length; i++) {//Goes through every platform and checks if they collide with the player
            if (Physics.PlayerPlatform(new Point((int) x + width, (int) y + 2), b[i]) || Physics.PlayerPlatform(new Point((int) x + width, (int) y + height - 1), b[i])) {//Checks if the player collides with the platfom
                right = false;
            }

            if (Physics.PlayerPlatform(new Point((int) x - 1, (int) y + 2), b[i]) || Physics.PlayerPlatform(new Point((int) x - 1, (int) y + height - 1), b[i])) {//Checks if the player collides with the platfom
                left = false;
            }

            if (Physics.PlayerPlatform(new Point((int) x + 1, (int) y), b[i]) || Physics.PlayerPlatform(new Point((int) x + width - 1, (int) y), b[i])) {//Checks if the player collides with the platfom
                jumping = false;
                falling = true;
            }

            if (Physics.PlayerPlatform(new Point((int) x + 2, (int) y + height + 1), b[i]) || Physics.PlayerPlatform(new Point((int) x + width - 1, (int) y + height + 1), b[i])) {//Checks if the player collides with the platfom
                y = b[i].getY() - height;//Fixes the problem if player sunks into platform
                falling = false;
                topCollision = true;
            } else {
                if (!topCollision && !jumping) {//If not jumping and not touching a platform from the top, set falling to true
                    falling = true;
                }
            }

        }
        topCollision = false;//Reset top collosion

        hitBox = new BallHitBox((int) ball.x, (int) ball.y);//Declaring a new hit box with the same coordiantes as the ball

        if (Physics.PlayerBall(new Point((int) x + width, (int) y + 2), hitBox) || Physics.PlayerBall(new Point((int) x + width, (int) y + height - 1), hitBox)) {//Checks if the player collides with the ball
            if (!lock) {//If lock is false, set end to true, get the score, and set lock to true
                end = true;
                score = Integer.toString(ball.getScore());
                lock = true;
            }
        }
        if (Physics.PlayerBall(new Point((int) x - 1, (int) y + 2), hitBox) || Physics.PlayerBall(new Point((int) x - 1, (int) y + height - 1), hitBox)) {//Checks if the player collides with the ball
            if (!lock) {//If lock is false, set end to true, get the score, and set lock to true
                end = true;
                score = Integer.toString(ball.getScore());
                lock = true;
            }

        }
        if (Physics.PlayerBall(new Point((int) x + 1, (int) y), hitBox) || Physics.PlayerBall(new Point((int) x + width - 1, (int) y), hitBox)) {//Checks if the player collides with the ball
            if (!lock) {//If lock is false, set end to true, get the score, and set lock to true
                end = true;
                score = Integer.toString(ball.getScore());
                lock = true;
            }
        }
        if (Physics.PlayerBall(new Point((int) x + 2, (int) y + height + 1), hitBox) || Physics.PlayerBall(new Point((int) x + width - 1, (int) y + height + 1), hitBox)) {//Checks if the player collides with the ball
            if (!lock) {//If lock is false, set end to true, get the score, and set lock to true
                end = true;
                score = Integer.toString(ball.getScore());
                lock = true;
            }
        }
        if (right) {//If the player attempts to move further right than the bounds, the player is restricted, else the move right
            if (x >= 770) {
                x = 770;
            } else {
                x += 2;
            }
        }
        if (left) {//If the player attempts to move further left than the bounds, the player is restricted, else the move left
            if (x <= 0) {
                x = 0;
            } else {
                x -= 2;
            }
        }
        if (jumping) {//This method runs the jump code
            y -= currentJumpSpeed;
            currentJumpSpeed -= 0.1;
            if (currentJumpSpeed <= 0) {
                currentJumpSpeed = jumpSpeed;
                jumping = false;
                falling = true;
            }
        }
        if (falling) {//This method runs the fall code
            y += currentFallSpeed;
            if (currentFallSpeed < maxFallSpeed) {
                currentFallSpeed += 0.1;
            }
        }
        if (!falling) {//If falling is false, rest current fall speed
            currentFallSpeed = 0.1;
        }
        if (reset) {//The rest function, reset all the object features after ENTER is pressed
            x = GamePanel.WIDTH / 2;
            y = GamePanel.HEIGHT / 2 + 50;
            ball.setX(GamePanel.WIDTH / 2 - 10);
            ball.setY(GamePanel.HEIGHT / 3);
            ball.width = 80;
            ball.height = 80;
            ball.setSpeed(1);
            ball.setScore(0);
            lock = false;
            reset = false;
            setEnd(false);

        }
    }

    public void draw(Graphics g) {//Draws the player
        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, width, height);

    }

    public void keyPressed(int k) {//Registers movement and restricts movement if end screen is shown
        if (k == KeyEvent.VK_RIGHT && lock == false) {
            right = true;
        }
        if (k == KeyEvent.VK_LEFT && lock == false) {
            left = true;
        }
        if (k == KeyEvent.VK_UP && !jumping && !falling && lock == false) {
            jumping = true;
        }
        if (k == KeyEvent.VK_ENTER) {
            reset = true;
        }

    }

    public void keyReleased(int k) {//Registers released keys which determine when the player stops
        if (k == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (k == KeyEvent.VK_LEFT) {
            left = false;
        }
    }
}
