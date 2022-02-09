package com.company.model.TileData;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;


public class TileManager {

    public Tile[] tile;

    public TileManager(){

        tile = new Tile[10];


        setTileImage();

    }



    public void setTileImage(){

            setImage(tile, "/Images/GrassTile.png", 0);
            setImage(tile, "/Images/DirtTile.png", 1);
            setImage(tile, "/Images/WallTile.png",2);
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
