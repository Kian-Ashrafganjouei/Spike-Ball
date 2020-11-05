package com.mycompany.spikeball;

import java.awt.Point;

public class Physics {//This class is used for the collision detection 

    public static boolean PlayerPlatform(Point p, Platform b) {//Boolean method to check if the player and platform are at collision
        return b.contains(p);
    }

    public static boolean PlayerBall(Point p, BallHitBox b) {//Boolean method to check if the player and ball are at collision
        return b.contains(p);
    }
}
