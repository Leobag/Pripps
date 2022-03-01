package com.company.model;

import javax.imageio.ImageIO;

public class EnemyManager {
    Enemy[] enemyArray;

    public void createEnemies(int mapCounter){

        switch (mapCounter){
            case 0 -> {
                enemyArray = new Enemy[5];
                for(int i = 0; i < 5; i++){
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(6,7);
                enemyArray[1].setPosition(6,11);
                enemyArray[2].setPosition(18,11);
                enemyArray[3].setPosition(3,15);
                enemyArray[4].setPosition(21,9);

            }
            case 1 -> {
                enemyArray = new Enemy[5];
                for(int i = 0; i < 5; i++){
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(6,7);
                enemyArray[1].setPosition(6,11);
                enemyArray[2].setPosition(18,11);
                enemyArray[3].setPosition(3,15);
                enemyArray[4].setPosition(21,9);

            }
            case 2 -> {
                enemyArray = new Enemy[5];
                for(int i = 0; i < 5; i++){
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(6,7);
                enemyArray[1].setPosition(6,11);
                enemyArray[2].setPosition(18,11);
                enemyArray[3].setPosition(3,15);
                enemyArray[4].setPosition(21,9);
            }
        }
    }

    public Enemy[] getEnemyArray(){
        return enemyArray;
    }

    public void updateEnemyHitboxes(){
        for (Enemy enemy : enemyArray) {
            enemy.updateHitBox(
                    (int) (enemy.getPosition().x * 32),
                    (int) (enemy.getPosition().y * 32),
                    enemy.hitBoxSize);
        }

    }

    public void setPutinMovmentIMG(){
        for(int i = 0; i < enemyArray.length; i++){
            enemyArray[i].setEnemyImages("putinFaces");
        }
    }
}
