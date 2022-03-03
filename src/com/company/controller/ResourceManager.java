package com.company.controller;

import java.io.*;

public class ResourceManager {

    public static void save(Serializable data, String file) throws IOException, NullPointerException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
            oos.flush();
        }
    }

    public static Object load(String file) throws IOException, ClassNotFoundException, NullPointerException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } 
    }
}
