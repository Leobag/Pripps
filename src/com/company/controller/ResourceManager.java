package com.company.controller;

import java.io.*;

/**
 * Save and load methods for the game.
 * @author Andy Alavinasab
 * @version 03-03-22
 */
public class ResourceManager {

    /**
     * Stores the data from SaveData on a file
     * @param data - Information to be stored.
     * @param file - The name for the created file
     */
    public static void save(Serializable data, String file) throws IOException, NullPointerException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
            oos.flush();
        }
    }

    /**
     * Loads the file saved in above method.
     * @param file - The name of the file to be loaded
     */
    public static Object load(String file) throws IOException, ClassNotFoundException, NullPointerException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } 
    }
}
