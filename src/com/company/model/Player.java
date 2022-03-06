package com.company.model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * The player which user controls by keyboard.
 * Keeps track of player information and loads the player images.
 *
 * @author Emil Berzelius
 * @version 06-03-22
 */
public class Player extends Entity {


    public final int hitBoxSize = 16;
    private int unitCounter, unitNum;
    private Double inputDirection;
    private boolean dead = false;

    /**
     * Constructs player object.
     */
    public Player() {
        this.speed = 4;
        this.size = 0.5;
        this.unitCounter = 0;
        this.unitNum = 1;
        this.setDirection("south");
    }

    /**
     * Load and set PNG-file for player depending on direction. Sets a new image each update
     * cycle creating a "walk looking" effect.
     */
    public void setMovementImages() {

        if (dead) {
            try {
                this.unitImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entities/PlayerImages/realExplosion.png")));
            } catch (IOException e) {
                System.out.println("cant find explosionIMG");
            }
        } else {
            String tempDir = this.direction;
            if (this.unitCounter > 30) {

                if (this.unitNum == 1) {
                    this.unitNum = 2;
                } else if (this.unitNum == 2) {
                    this.unitNum = 1;
                }
                try {
                    if (tempDir.equals("northEast") || tempDir.equals("southEast")) {
                        tempDir = "east";
                    } else if (tempDir.equals("northWest") || tempDir.equals("southWest")) {
                        tempDir = "west";
                    }

                    this.unitImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entities/PlayerImages/" + tempDir + unitNum + ".png")));

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
            this.unitCounter++;
        }
    }

    public double setPlayerX(Double deltaTime) {
        return (getPosition().getX() + Math.cos(getInputDirection()) * speed * deltaTime);
    }

    public double setPlayerY(Double deltaTime) {
        return (getPosition().getY() + Math.sin(getInputDirection()) * speed * deltaTime);
    }

    /**
     * Sets the sting direction associated with the angle in radians.
     *
     * @param inputDirection - The angle of the direction according to the unit circle.
     */
    public void setStringDirection(Double inputDirection) {
        if (!(inputDirection == null)) {
            if (this.inputDirection == -1.5707963267948966) {
                this.setDirection("north");
            } else if (this.inputDirection == 1.5707963267948966) {
                this.setDirection("south");
            } else if (this.inputDirection == 3.141592653589793) {
                this.setDirection("west");
            } else if (this.inputDirection == 0.0) {
                this.setDirection("east");
            } else if (this.inputDirection == 0.7853981633974483) {
                this.setDirection("southEast");
            } else if (this.inputDirection == -0.7853981633974483) {
                this.setDirection("northEast");
            } else if (this.inputDirection == 2.356194490192345) {
                this.setDirection("southWest");
            } else if (this.inputDirection == -2.356194490192345) {
                this.setDirection("northWest");
            }
        }
    }

    public Double getInputDirection() {
        return this.inputDirection;
    }

    public void setInputDirection(Double inputDirection) {
        this.inputDirection = inputDirection;
    }

    public boolean getDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}