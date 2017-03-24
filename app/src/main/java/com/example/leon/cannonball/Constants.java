package com.example.leon.cannonball;

/**
 * Interface for storing the constants used to manipulate key game mechanics such as scores and
 * movement speed.
 */
public interface Constants {

    int greyScore = 10;
    int magentaScore = 30;
    int yellowScore = 75;
    int blockerScore = -30;

    int greyTimeBonus = 3000;
    int magentaTimeBonus = 4000;
    int yellowTimeBonus = 5000;

    float velocityScale = 4;
    float cannonShootSpeed = 35;

    int delay = 50;
}



