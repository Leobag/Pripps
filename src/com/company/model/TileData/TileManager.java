package com.company.model.TileData;

import java.io.*;

import javax.imageio.ImageIO;


public class TileManager {

    public Tile[] tile;

    public TileManager() {

        tile = new Tile[10];
        setTileImage();
    }

    //TODO: kan sättas som enum i stället
    public void setTileImage() {

        setImage(tile, "/Images/Tiles/GrassTile.png", 0);
        setImage(tile, "/Images/Tiles/DirtTile.png",  1);
        setImage(tile, "/Images/Tiles/WallTile.png",  2);
        setImage(tile, "/Images/Tiles/StairTile.png", 3);
        tile[2].setCollision(true);
        tile[3].setNextMapBool(true);
    }



    private void setImage(Tile[] tile, String imgname, int TileNumber) {

        try {
            tile[TileNumber] = new Tile();
            tile[TileNumber].image = ImageIO.read(getClass().getResourceAsStream(imgname));
            if(TileNumber == 2){
                tile[TileNumber].setCollision(true);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
