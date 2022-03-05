package com.company.model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Entity {

    public final int hitBoxSize = 15;
    public int unitCounter, unitNum;
    public String oldDir;
    public double movementDirection;

    public Enemy() {
        this.unitCounter = 0;
        this.unitNum = 1;
        this.speed = 2;
        this.size = 0.5;
        changeDirection();
    }

    /**
     * Load the PNG-file and set images for enemies.
     *
     * @param directory - the directory holding the png files.
     */

    public void setEnemyImages(String directory) {

        String tempDir = this.direction;
        if (oldDir != this.direction) {
            oldDir = tempDir;

            try {
                if (tempDir.equals("northEast") || tempDir.equals("southEast")) {
                    tempDir = "east";
                } else if (tempDir.equals("northWest") || tempDir.equals("southWest")) {
                    tempDir = "west";
                }
                this.unitImage = ImageIO.read(getClass().getResourceAsStream("/Entities/" + directory + "/" + tempDir + ".png"));

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * generates a random number used in switch case to change direction of enemies.
     *
     * @return - returns a random number between 1-8.
     */

    private int randomNumberOneToEight() {
        return (int) ((Math.random() * (7) + 1));
    }

    /**
     * Changes the direction to a new random one.
     *
     */
    public void changeDirection(){
        this.movementDirection = setEnemyDirection(randomNumberOneToEight());
        setStringDirection(this.movementDirection);
    }

    /**
     * Translates the direction fron radians to strings used for switch case statements in
     * CollisionCheck.
     * @param movementDirection - the angle of the direction in radians.
     */
    public void setStringDirection(Double movementDirection){
        if(!(movementDirection == null)) {
            if (this.movementDirection == -1.5707963267948966) {
                this.setDirection("north");
            } else if (this.movementDirection == 1.5707963267948966) {
                this.setDirection("south");
            } else if (this.movementDirection == 3.141592653589793) {
                this.setDirection("west");
            } else if (this.movementDirection == 0.0) {
                this.setDirection("east");
            } else if (this.movementDirection == 0.7853981633974483) {
                this.setDirection("southEast");
            } else if (this.movementDirection == -0.7853981633974483) {
                this.setDirection("northEast");
            } else if (this.movementDirection == 2.356194490192345) {
                this.setDirection("southWest");
            } else if (this.movementDirection == -2.356194490192345) {
                this.setDirection("northWest");
            }
        }
    }

    /**
     * Sets radian angle of the enemy according to the random number generated.
     *
     * @param enemyDirectionNumber - A random number between 1-8 to decide the direction.
     *
     * @return - returns the angle in radians which the enemy is to move.
     *
     */
    public double setEnemyDirection(int enemyDirectionNumber){
        switch (enemyDirectionNumber) {
            case 1 -> {
                return 0.0;
            }
            case 2 -> {
                return -0.7853981633974483;
            }
            case 3 -> {
                return -1.5707963267948966;
            }
            case 4 -> {
                return -2.356194490192345;
            }
            case 5 -> {
                return 3.141592653589793;
            }
            case 6 -> {
                return 2.356194490192345;
            }
            case 7 -> {
                return 1.5707963267948966;
            }
            case 8 -> {
                return 0.7853981633974483;
            }
        }
        throw new IllegalStateException();
    }

    public double getMovementDirection() {
        return movementDirection;
    }

}

