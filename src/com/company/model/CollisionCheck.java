package com.company.model;

import com.company.view.PrippsView;

import java.awt.geom.Point2D;

public class CollisionCheck {
    PrippsModel model;

    public CollisionCheck(PrippsModel model){
        this.model = model;
    }

    double topLeft = model.getPlayer().getPosition().x;
    double topRight = model.getPlayer().getPosition().x + model.getTileSize() * model.getPlayer().size;
    double botLeft = model.getPlayer().getPosition().y + model.getTileSize() * model.getPlayer().size;
    double botRight = model.getPlayer().getPosition().x + model.getPlayer().getPosition().y + model.getTileSize() * model.getPlayer().size;

    int tileLeftCol = (int)topLeft/model.getTileSize();
    int tileRightCol = (int)topRight/model.getTileSize();
    int tileTopRow = (int)botLeft/model.getTileSize();
    int tileBotRow = (int)botRight/model.getTileSize();

    int tileMap[][] = model.getMatrix();
/*
    public boolean isCollison(){
        if(tileMap[tileTopRow][tileLeftCol] ==)
        return false;
    }
    */
}
