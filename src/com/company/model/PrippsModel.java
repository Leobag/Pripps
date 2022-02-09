package com.company.model;

import com.company.model.TileData.Tile;
import com.company.model.TileData.TileManager;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PrippsModel {

    final private int tileSize = 32;
    final private int maxScreenCol = 42;
    final private int maxScreenRow = 24;

    final private String[] mapNames = {"/Maps/map01.txt", "/Maps/map02.txt"};
    private int mapCounter = 0;


    final int screenWidth = tileSize*maxScreenCol;
    final int screenHeight = tileSize*maxScreenRow;

    int[][] mapTileMatrix = new int[maxScreenCol][maxScreenRow];

    TileManager tile;



    public PrippsModel() {

    tile = new TileManager();

    loadMap(mapNames[1]);

    }

    public BufferedImage getTileImage(int num){
        return tile.tile[num].image;
    }

    public int[][] getMatrix(){
        return this.mapTileMatrix;
    }

    public String getCurrentMap(){
            return this.mapNames[mapCounter];
    }

    public void nextMap(){
        mapCounter++;
    }

    public int getMaxCol(){
        return this.maxScreenCol;
    }

    public int getMaxRow(){
        return this.maxScreenRow;
    }

    public int getTileSize(){
        return this.tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public TileManager getTile(){
        return this.tile;
    }

    public void loadMap(String mapName){
        try{
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            int num;
            String line;
            String numbers[];


            while(col < maxScreenCol && row < maxScreenRow){
                line = br.readLine();

                while(col < maxScreenCol){
                    numbers = line.split(" ");
                    num = Integer.parseInt(numbers[col]);

                    mapTileMatrix[col][row] = num;
                    col++;
                }
                if(col == maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }



}
