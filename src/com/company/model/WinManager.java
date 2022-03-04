package com.company.model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;


public class WinManager {

    Entity prippsPack = new Entity();

    /**
     * initializes the winning condition object.
     */
    public WinManager() {
        prippsPack.setPosition(3, 7);
    }

    /**
     * Load the PNG-file and it according to position.
     * Updates hitbox used to detect collision with player.
     * @param mapCounter - the current map used to decide if the PrippsPack should be spawned.
     */
    public void winCondition(int mapCounter) {
        try {
            prippsPack.unitImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Entities/pripps6pack.png")));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        switch (mapCounter){
            case 0, 1 -> prippsPack.updateHitBox(0,0,0);

            case 2 -> prippsPack.updateHitBox(3 * 32, 7 * 32, 32);

        }
    }

    public Entity getPrippsPack() {
        return prippsPack;
    }
}
