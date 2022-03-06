package com.company.model;

import com.company.model.TileData.TileManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Reads the map file and creates the mapMatrix used to paint the map.
 * MapManager class gives information to game and collisionCheck.
 *
 * @author Leonard Bagiu
 * @version 04-03-22
 */
public class MapManager {

    final private int maxScreenCol = 42;
    final private int maxScreenRow = 24;
    final private String[] mapNames = {"/Maps/map01.txt", "/Maps/map02.txt", "/Maps/map03.txt"};
    int[][] mapTileMatrix = new int[maxScreenCol][maxScreenRow];
    TileManager tile;
    private int mapCounter = 0;
    private int[] startingPositions = new int[2];

    public MapManager() {
        tile = new TileManager();

        loadMap(mapNames[this.mapCounter]);

    }

    public String getCurrentMap() {
        return this.mapNames[mapCounter];
    }

    /**
     * Initializes the map according to map number.
     */
    public void loadCurrentMap() {
        loadMap(mapNames[mapCounter]);
    }

    /**
     * Increments map counter by one.
     */
    public void incrementMap() {
        mapCounter++;
    }


    public int getMapCounter() {
        return this.mapCounter;
    }

    public void setMapCounter(int mapCounter) {
        this.mapCounter = mapCounter;
    }

    /**
     * Incrementing map number by one and initializing the new map.
     */
    public void nextMap() {
        this.incrementMap();
        this.loadMap(this.mapNames[this.getMapCounter()]);

    }

    public TileManager getTiles() {
        return this.tile;
    }

    public int[][] getMapMatrix() {
        return this.mapTileMatrix;
    }

    /**
     * reads the .txt file containing the map information for mapMatrix.
     * Reads the file line by line and at every " " it splits the array and puts the previous
     * number in a variable and then into the appropriate position in the mapMatrix.
     *
     * @param mapName - current map.
     */
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

    public void resetMap() {
        mapCounter = 0;
    }

    public int getMaxCol() {
        return maxScreenCol;
    }

    public int getMaxRow() {
        return maxScreenRow;
    }
}