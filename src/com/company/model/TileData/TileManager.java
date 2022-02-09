package com.company.model.TileData;

import java.io.*;

import javax.imageio.ImageIO;


public class TileManager {

    Tile[] tile;

    public TileManager(){

        tile = new Tile[10];


        getTileImage();

    }

    public void getTileImage(){

            setImage(tile, "/Images/GrassTile", 0);
            setImage(tile, "/Images/DirtTile", 1);
            setImage(tile,"/Images/WallTile",2);
    }

    private void setImage(Tile[] tile, String imgname, int TileNumber){

        try{
            tile[TileNumber] = new Tile();
            tile[TileNumber].image = ImageIO.read(getClass().getResourceAsStream(imgname));
        } catch(IOException e){
            e.printStackTrace();
        }
    }



}
