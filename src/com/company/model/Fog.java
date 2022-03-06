package com.company.model;

import java.awt.*;

/**
 * Calculations for fog of war.
 *
 * @author Emil Berzelius
 * @version 06-03-22
 */
public class Fog {

    MapManager map;
    Player player;

    public Fog(Player player, MapManager map) {
        this.player = player;
        this.map = map;

    }

    /**
     * Calculates the size of the Rectangles used to draw Fog of war surrounding the player.
     * Does so by checking where the player is and where the end of the map is in respective direction.
     *
     * @return - returns an array of Rectangles.
     */
    public Rectangle[] getFogSquares() {
        Rectangle[] fogSquares = new Rectangle[5];

        fogSquares[0] = new Rectangle(player.getHitBox().x + player.getHitBox().width + 32 * 3,
                0, (map.getMaxCol() * 32) - player.getHitBox().x + player.getHitBox().width, map.getMaxRow() * 32);

        fogSquares[1] = new Rectangle(0, 0, player.getHitBox().x - 32 * 3, map.getMaxRow() * 32);

        fogSquares[2] = new Rectangle(0, 0, map.getMaxCol() * 32, player.getHitBox().y - 32 * 3);

        fogSquares[3] = new Rectangle(0, player.getHitBox().y + player.getHitBox().height + 32 * 3,
                map.getMaxCol() * 32, 32 * map.getMaxRow() - player.getHitBox().y - 32 * 3);

        fogSquares[4] = new Rectangle(player.getHitBox().x,
                player.getHitBox().y, 32 * 6 + 32 * 5,
                32 * 6 + 32 * 5);

        return fogSquares;


    }

}