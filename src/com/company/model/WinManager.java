package com.company.model;

import javax.imageio.ImageIO;
import java.io.IOException;



public class WinManager {

    Entity prippsPack = new Entity();

    public WinManager(){
        prippsPack.setPosition(3, 7);
    }

    public void winCondition(int mapCounter){
        try {
            prippsPack.unitImage = ImageIO.read(getClass().getResourceAsStream("/Entities/pripps6pack.png"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        switch (mapCounter){
            case 0, 1 -> {}
            case 2 -> prippsPack.updateHitBox(3 * 32, 7 * 32, 32);

        }
    }

    public Entity getPrippsPack(){
        return prippsPack;
    }
}
