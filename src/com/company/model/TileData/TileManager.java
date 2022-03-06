package com.company.model.TileData;

import java.io.*;

import javax.imageio.ImageIO;

/**
 * @author - Emil Berzelius
 */
public class TileManager {

    public Tile[] tile;

    public TileManager() {

        tile = new Tile[10];
        setTileImage();
    }

    /**
     * Load the PNG-files of the tiles and associates them with their respective number.
     * Set conditions for tiles i.e. collision and nextMap which is discovered by entities.
     * - Emil Berzelius
     */
    public void setTileImage() {

        setImage(tile, "/Images/Tiles/GrassTile.png", 0);
        setImage(tile, "/Images/Tiles/DirtTile.png",  1);
        setImage(tile, "/Images/Tiles/WallTile.png",  2);
        setImage(tile, "/Images/Tiles/StairTile.png", 3);
        tile[2].setCollision(true);
        tile[3].setNextMapBool(true);
    }


    /**
     * Function to simplify adding new tiles.
     *
     * @param tile - The array of tiles.
     * @param imgname - Source path to image.
     * @param TileNumber - The tile number associated with the tile.
     */

    private void setImage(Tile[] tile, String imgname, int TileNumber) {

        try {
            tile[TileNumber] = new Tile();
            tile[TileNumber].image = ImageIO.read(getClass().getResourceAsStream(imgname));
            if(TileNumber == 2){
                tile[TileNumber].setCollision(true);
            }
        } catch (IOException e) {
            System.out.println("Could not find tileImage" + e.getMessage());
        }
    }
}
