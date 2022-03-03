package com.company.model;

public class EnemyManager {
    Enemy[] enemyArray;

    public void createEnemies(int mapCounter) {

        switch (mapCounter) {
            case 0 -> {
                enemyArray = new Enemy[5];
                for (int i = 0; i < 5; i++) {
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(6, 8);
                enemyArray[1].setPosition(34, 21);
                enemyArray[2].setPosition(18, 5);
                enemyArray[3].setPosition(12, 17);
                enemyArray[4].setPosition(21, 19);

            }
            case 1 -> {
                enemyArray = new Enemy[6];
                for (int i = 0; i < 6; i++) {
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(6, 3);
                enemyArray[1].setPosition(30, 9);
                enemyArray[2].setPosition(16, 17);
                enemyArray[3].setPosition(3, 15);
                enemyArray[4].setPosition(27, 14);
                enemyArray[5].setPosition(40, 21);

            }
            case 2 -> {
                enemyArray = new Enemy[7];
                for (int i = 0; i < 7; i++) {
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(38, 7);
                enemyArray[1].setPosition(14, 5);
                enemyArray[2].setPosition(13, 20);
                enemyArray[3].setPosition(3, 4);
                enemyArray[4].setPosition(19, 14);
                enemyArray[5].setPosition(34, 10);
                enemyArray[6].setPosition(29, 20);
            }
        }
    }

    public Enemy[] getEnemyArray() {
        return enemyArray;
    }

    public void updateEnemyHitboxes() {
        for (Enemy enemy : enemyArray) {
            enemy.updateHitBox(
                    (int) (enemy.getPosition().getX() * 32),
                    (int) (enemy.getPosition().getY() * 32),
                    enemy.hitBoxSize);
        }

    }

    public void setPutinMovmentIMG() {
        for (int i = 0; i < enemyArray.length; i++) {
            enemyArray[i].setEnemyImages("putinFaces");
        }
    }
}
