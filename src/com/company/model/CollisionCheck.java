package com.company.model;

import com.company.view.PrippsView;

import java.awt.geom.Point2D;

public class CollisionCheck {
    PrippsModel model;

    public CollisionCheck(PrippsModel model){
        this.model = model;
    }

    public boolean isCollison(){
        int topLeft = (int)model.getPlayer().getPosition().x;
        int topRight = (int)(model.getPlayer().getPosition().x + model.getPlayer().size);
        double botLeft = model.getPlayer().getPosition().y + model.getTileSize() * model.getPlayer().size;
        double botRight = model.getPlayer().getPosition().x + model.getPlayer().getPosition().y + model.getTileSize() * model.getPlayer().size;

        System.out.println(topLeft);
        System.out.println(topRight);
        System.out.println(botLeft);
        System.out.println(botRight);
        int[][] tileMap = model.getMatrix();

        return tileMap[topLeft][topRight] == 2;
    }

}
