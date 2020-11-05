package com.mycompany.spikeball;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends Rectangle {//The ball class that extends from the Rectangle

    public double x, y;//The double variables for the coordiantes of the ball
    private double xDirection = 1;//The direction of the x
    private double yDirection = 1;//The direction of the y 

    private int maxBallSpeed = 15;//Setting the maximum ball speed 
    private int maxBallSize = 120;//Setting the maximum ball size 
    public int width, height;//The width and the height of the ball
    private int speed = 1;//The inital speed of the ball
    private int score = 0;//The score 
    private int hitCounter = 0;//The counter for the number of time sthe ball bounces

    public int getScore() {//Gets the score
        return score;
    }

    public Ball(int width, int height) {//The ball constructor 
        x = GamePanel.WIDTH / 2 - 10;//The starting location of the ball
        y = GamePanel.HEIGHT / 3;
        this.width = width;//The width and the height of the ball
        this.height = height;
    }

    public void draw(Graphics g) {//The method which draws an oval at very instance
        g.setColor(Color.lightGray);
        g.fillOval((int) x, (int) y, width, height);
        g.drawString(Integer.toString(score), 30, 30);//Draws the score on the top right
    }

    public void setX(double x) {//Setting the x values, used for resetting the ball location
        this.x = x;
    }

    public void setY(double y) {//Setting the y values, used for resetting the ball location 
        this.y = y;
    }

    public void setSpeed(int speed) {//Setting the speed of the ball
        this.speed = speed;
    }

    public void setScore(int score) {//Setting the score
        this.score = score;
    }
    
    public void tick() {//The tick method which does all the calculation in regards to  the ball 
        if (x + xDirection < 0) {//If the ball is moving in this direction
            score++;//Add to score
            xDirection = speed;//Determining the speed
            hitCounter++;//Adding to the hit counter
            if (hitCounter == 3) {//If hit counter is three
                speed++;//Increase speed, width and height
                width++;
                height++;
                hitCounter = 0;//Reset hit counter
                if (speed >= maxBallSpeed) {//If speed is greater than the speed, set speed to max
                    speed = maxBallSpeed;
                }
                if (height >= maxBallSize && width >= maxBallSize) {//If height is greater than max height and width is greater than max size, reset size to limit
                    height = maxBallSize;
                    width = maxBallSize;
                }
            }
        } else if (x + xDirection > GamePanel.WIDTH - width) {
            xDirection = -speed;//Determining the speed
            hitCounter++;//Adding to the hit counter
            score++;//Add to score
            if (hitCounter == 3) {//If hit counter is three
                speed++;//Increase speed, width and height
                width++;
                height++;
                hitCounter = 0;//Reset hit counter
                if (speed >= maxBallSpeed) {//If speed is greater than the speed, set speed to max
                    speed = maxBallSpeed;
                }

                if (height >= maxBallSize && width >= maxBallSize) {//If height is greater than max height and width is greater than max size, reset size to limit
                    height = maxBallSize;
                    width = maxBallSize;
                }
            }

        } else if (y + yDirection < 0) {
            yDirection = speed;//Determining the speed
            hitCounter++;//Adding to the hit counter
            score++;//Add to score
            if (hitCounter == 3) {//If hit counter is three
                speed++;//Increase speed, width and height
                width++;
                height++;
                hitCounter = 0;//Reset hit counter
                if (speed >= maxBallSpeed) {//If speed is greater than the speed, set speed to max
                    speed = maxBallSpeed;
                }

                if (height >= maxBallSize && width >= maxBallSize) {//If height is greater than max height and width is greater than max size, reset size to limit
                    height = maxBallSize;
                    width = maxBallSize;
                }
            }

        } else if (y + yDirection > GamePanel.HEIGHT - height) {
            yDirection = -speed;//Determining the speed
            hitCounter++;//Adding to the hit counter
            score++;//Add to score
            if (hitCounter == 3) {//If hit counter is three
                speed++;//Increase speed, width and height
                width++;
                height++;
                hitCounter = 0;//Reset hit counter
                if (speed >= maxBallSpeed) {//If speed is greater than the speed, set speed to max
                    speed = maxBallSpeed;
                }

                if (height >= maxBallSize && width >= maxBallSize) {//If height is greater than max height and width is greater than max size, reset size to limit
                    height = maxBallSize;
                    width = maxBallSize;
                }
            }

        }

        x += xDirection;//Adding to the coordinates
        y += yDirection;

    }
}
