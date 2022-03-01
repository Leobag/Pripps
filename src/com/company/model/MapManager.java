package com.company.model;

import com.company.model.TileData.TileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapManager {

    final private int maxScreenCol = 42;
    final private int maxScreenRow = 24;

    int[][] mapTileMatrix = new int[maxScreenCol][maxScreenRow];

    TileManager tile;
    final private String[] mapNames = {"/Maps/map01.txt", "/Maps/map02.txt", "/Maps/map03.txt"};
    private int mapCounter = 0;
    private int[] startingPositions = new int[2];

    public MapManager(){
        tile = new TileManager();

        loadMap(mapNames[this.mapCounter]);

    }

    public String getCurrentMap() {
        return this.mapNames[mapCounter];
    }

    public void loadCurrentMap(){
        loadMap(mapNames[mapCounter]);
    }

    public void setStartingPositions(int x, int y){
        this.startingPositions[0] = x;
        this.startingPositions[1] = y;
    }

    public int[] getStartingPositions(){
        return this.startingPositions;
    }


    public int getMaxCol() {
        return this.maxScreenCol;
    }

    public int getMaxRow() {
        return this.maxScreenRow;
    }

    public void incrementMap() {

        mapCounter++;

    }

    public int getMapCounter(){
        return this.mapCounter;
    }

    public void nextMap(){
        this.incrementMap();
        this.loadMap(this.mapNames[this.getMapCounter()]);

    }

    public TileManager getTiles(){
        return this.tile;
    }


    public int [][] getMapMatrix(){
        return this.mapTileMatrix;
    }

    public void loadMap(String mapName) {
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            int num;
            String line;
            String numbers[];


            while (col < maxScreenCol && row < maxScreenRow) {
                line = br.readLine();

                while (col < maxScreenCol) {
                    numbers = line.split(" ");
                    num = Integer.parseInt(numbers[col]);

                    mapTileMatrix[col][row] = num;
                    col++;
                }
                if (col == maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
