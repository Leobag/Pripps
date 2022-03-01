package com.company.model;


import java.awt.*;
import java.util.Map;

public class Fog {

    MapManager map;
    Player player;

    public Fog(Player player, MapManager map){
        this.player = player;
        this.map = map;

    }

    public Rectangle[] getFogSquares(){
        Rectangle[] fogSqueares = new Rectangle[5];

        fogSqueares[0] = new Rectangle(player.getHitBox().x + player.getHitBox().width + 32 * 3,
                0, (map.getMaxCol() * 32) - player.getHitBox().x + player.getHitBox().width, map.getMaxRow() * 32);

        fogSqueares[1] = new Rectangle(0, 0, player.getHitBox().x - 32 * 3, map.getMaxRow() * 32);

        fogSqueares[2] = new Rectangle(0, 0, map.getMaxCol() * 32, player.getHitBox().y - 32 * 3);

        fogSqueares[3] = new Rectangle(0, player.getHitBox().y + player.getHitBox().height + 32 * 3,
                map.getMaxCol() * 32, 32 * map.getMaxRow() - player.getHitBox().y - 32 * 3);

        fogSqueares[4] = new Rectangle(player.getHitBox().x,
                player.getHitBox().y, 32 * 6 + 32 * 5,
                32 * 6 + 32 * 5);

        return fogSqueares;
    }

}