package com.company.model;



public class CollisionCheck {
    Game game;

    public CollisionCheck(Game game){
        this.game = game;
    }

    public boolean isCollison(){
        int topLeft = (int)game.getPlayer().getPosition().x;
        int topRight = (int)(game.getPlayer().getPosition().x + game.getPlayer().size);
        double botLeft = game.getPlayer().getPosition().y + game.getPlayer().size;
        double botRight = game.getPlayer().getPosition().x + game.getPlayer().getPosition().y + game.getPlayer().size;

        System.out.println(topLeft);
        System.out.println(topRight);
        System.out.println(botLeft);
        System.out.println(botRight);
        int[][] tileMap = game.getMapTileMatrix();
        return tileMap[topLeft][topRight] == 2;
    }
}
