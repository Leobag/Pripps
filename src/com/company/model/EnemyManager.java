package com.company.model;

public class EnemyManager {
    Enemy[] enemyArray;
    Position[] posArray;

    /**
     * Creates enemy objects in array style and initializes positions.
     *
     * @param mapCounter - the current map.
     */
    public void createEnemies(int mapCounter) {

        switch (mapCounter) {
            case 0 -> {
                enemyArray = new Enemy[5];
                posArray = new Position[5];
                for (int i = 0; i < 5; i++) {
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(6, 8);
                enemyArray[1].setPosition(34, 21);
                enemyArray[2].setPosition(18, 5);
                enemyArray[3].setPosition(12, 17);
                enemyArray[4].setPosition(21, 19);
                posArray[0] = new Position(6, 8);
                posArray[1] = new Position(34, 21);
                posArray[2] = new Position(18, 5);
                posArray[3] = new Position(12, 17);
                posArray[4] = new Position(21, 19);

            }
            case 1 -> {
                enemyArray = new Enemy[6];
                posArray = new Position[6];
                for (int i = 0; i < 6; i++) {
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(6, 3);
                enemyArray[1].setPosition(30, 9);
                enemyArray[2].setPosition(16, 17);
                enemyArray[3].setPosition(3, 15);
                enemyArray[4].setPosition(27, 14);
                enemyArray[5].setPosition(40, 21);
                posArray[0] = new Position(6, 3);
                posArray[1] = new Position(30, 9);
                posArray[2] = new Position(16, 17);
                posArray[3] = new Position(3, 15);
                posArray[4] = new Position(27, 14);
                posArray[5] = new Position(40, 21);

            }
            case 2 -> {
                enemyArray = new Enemy[10];
                posArray = new Position[10];

                for (int i = 0; i < 10; i++) {
                    enemyArray[i] = new EnemyPutin();
                }
                enemyArray[0].setPosition(38, 7);
                enemyArray[1].setPosition(14, 5);
                enemyArray[2].setPosition(13, 20);
                enemyArray[3].setPosition(3, 4);
                enemyArray[4].setPosition(19, 14);
                enemyArray[5].setPosition(34, 10);
                enemyArray[6].setPosition(29, 20);
                enemyArray[7].setPosition(3, 7);
                enemyArray[8].setPosition(3, 6);
                enemyArray[9].setPosition(3, 5);
                posArray[0] = new Position(38, 7);
                posArray[1] = new Position(14, 5);
                posArray[2] = new Position(13, 20);
                posArray[3] = new Position(3, 4);
                posArray[4] = new Position(19, 14);
                posArray[5] = new Position(34, 10);
                posArray[6] = new Position(29, 20);
                posArray[7] = new Position(3, 7);
                posArray[8] = new Position(3, 6);
                posArray[9] = new Position(3, 5);
            }
        }
    }

    public Enemy[] getEnemyArray() {
        return enemyArray;
    }

    /**
     * Updates hitboxes used to detect collision with player.
     */
    public void updateEnemyHitboxes() {
        for (Enemy enemy : enemyArray) {
            enemy.updateHitBox(
                    (int) (enemy.getPosition().getX() * 32),
                    (int) (enemy.getPosition().getY() * 32),
                    enemy.hitBoxSize);
        }

    }

    /**
     * Updates images of enemies.
     */
    public void setPutinMovmentIMG() {
        for (int i = 0; i < enemyArray.length; i++) {
            enemyArray[i].setEnemyImages("putinFaces");
        }
    }

    public Position[] getPosArray() {
        return posArray;
    }

    public void setPosArray(Position[] posArray) {

        for (int i = 0; i < posArray.length; i++) {
            double x = posArray[i].getX();
            double y = posArray[i].getY();
            enemyArray[i].setPosition(x, y);

        }
    }
}
