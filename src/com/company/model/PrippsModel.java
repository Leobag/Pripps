package com.company.model;

import com.company.model.TileData.TileManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PrippsModel {

    final private int tileSize = 48;
    final private int maxScreenCol = 28;
    final private int maxScreenRow = 16;

    final private String[] mapNames = {"map01.txt"};
    private int mapCounter = 0;

    int mapTileMatrix[][];

    final int screenHeight = tileSize*maxScreenCol;
    final int screenWidth = tileSize*maxScreenRow;

    TileManager tile;

    public PrippsModel() {

    tile = new TileManager();

    }

    public String getCurrentMap(){
            return this.mapNames[mapCounter];
    }

    public void nextMap(){
        mapCounter++;
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


            while(col < screenWidth && row < screenHeight){
                line = br.readLine();

                while(col < screenWidth){
                    numbers = line.split(" ");
                    num = Integer.parseInt(numbers[col]);

                    mapTileMatrix[col][row] = num;
                    col++;
                }
                if(col == screenWidth){
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
