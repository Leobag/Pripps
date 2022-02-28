package com.company.model;

public class EnemyManager {
    Enemy[] enemyArray;

    public void createEnemies(int mapCounter){

        switch (mapCounter){
            case 0 -> {
                enemyArray = new Enemy[5];
                for(int i = 0; i < 5; i++){
                    enemyArray[i] = new Enemy();
                }
                enemyArray[0].setPosition(3,11);
                enemyArray[1].setPosition(6,11);
                enemyArray[2].setPosition(18,11);
                enemyArray[3].setPosition(3,15);
                enemyArray[4].setPosition(21,9);
            }
            case 1 -> {
                enemyArray = new Enemy[7];
                for(int i = 0; i < 7; i++){
                    enemyArray[i] = new Enemy();
                }
                enemyArray[0].setPosition(3,11);
                enemyArray[1].setPosition(6,11);
                enemyArray[2].setPosition(18,11);
                enemyArray[3].setPosition(3,30);
                enemyArray[4].setPosition(21,24);
                enemyArray[5].setPosition(21,24);
                enemyArray[6].setPosition(21,24);

            }
            case 2 -> {
                enemyArray = new Enemy[10];
                for(int i = 0; i < 10; i++){
                    enemyArray[i] = new Enemy();
                }
                enemyArray[0].setPosition(14,11);
                enemyArray[1].setPosition(2,11);
                enemyArray[2].setPosition(18,11);
                enemyArray[3].setPosition(3,30);
                enemyArray[4].setPosition(21,24);
                enemyArray[5].setPosition(21,24);
                enemyArray[6].setPosition(21,24);
                enemyArray[7].setPosition(21,24);
                enemyArray[8].setPosition(21,24);
                enemyArray[9].setPosition(21,24);
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
}
