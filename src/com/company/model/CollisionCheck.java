package com.company.model;


public class CollisionCheck {
    Game game;

    public CollisionCheck(Game game) {
        this.game = game;
    }

    /**
     * Takes the player position and sets each end of its hitbox as a variable.
     *
     * Places the variables inside the tile map and checks + 0.5 pixels ahead.
     * Discovers all the aspects of the tile 0.5 pixels ahead, mainly collision
     * in respective direction.
     *
     * The function is also used to detect if the player has entered a tile with next map condition.
     * If this is true, the new map is loaded, the new enemies are spawned and the winCondition is
     * placed out if it is the final map.
     *
     * @param entity - the object which collision is checked for against tiles.
     */

    public void isCollison(Entity entity) {

        double doubleLeftSideX = entity.getPosition().getX();
        double doubleRightSideX = (entity.getPosition().getX() + game.getPlayer().size);
        double doubleTopY = entity.getPosition().getY();
        double doubleBotY = (entity.getPosition().getY() + game.getPlayer().size);

        int leftSideX = (int) doubleLeftSideX;
        int rightSideX = (int) doubleRightSideX;
        int topY = (int) doubleTopY;
        int botY = (int) doubleBotY;


        int[][] tileMap = game.map.getMapMatrix();

        int tileNum1, tileNum2, tileNum3, tileNum4;

        switch (entity.getDirection()) {
            case "north" -> {
                topY = (int) ((doubleTopY * 32 - 0.5) / 32);
                tileNum1 = tileMap[leftSideX][topY];
                tileNum2 = tileMap[rightSideX][topY];


                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision()) {
                    entity.collisionOn = true;
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() ||
                        game.map.getTiles().tile[tileNum2].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());

                }
            }
            case "south" -> {
                botY = (int) ((doubleBotY * 32 + 0.5) / 32);
                tileNum1 = tileMap[leftSideX][botY];
                tileNum2 = tileMap[rightSideX][botY];

                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision()) {
                    entity.setCollisionOn(true);
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() ||
                        game.map.getTiles().tile[tileNum2].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());
                }
            }
            case "west" -> {
                leftSideX = (int) ((doubleLeftSideX * 32 - 0.5) / 32);
                tileNum1 = tileMap[leftSideX][botY];
                tileNum2 = tileMap[leftSideX][topY];

                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision()) {
                    entity.collisionOn = true;
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() ||
                        game.map.getTiles().tile[tileNum2].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());

                }
            }
            case "east" -> {
                rightSideX = (int) ((doubleRightSideX * 32 + 0.5) / 32);
                tileNum1 = tileMap[rightSideX][botY];
                tileNum2 = tileMap[rightSideX][topY];
                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision()) {
                    entity.collisionOn = true;
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() ||
                        game.map.getTiles().tile[tileNum2].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());

                }
            }

            case "northEast" -> {
                topY = (int) ((doubleTopY * 32 - 0.5) / 32);
                tileNum1 = tileMap[leftSideX][topY];
                tileNum2 = tileMap[rightSideX][topY];
                rightSideX = (int) ((doubleRightSideX * 32 + 0.5) / 32);
                topY = (int) doubleTopY;
                tileNum3 = tileMap[rightSideX][botY];
                tileNum4 = tileMap[rightSideX][topY];
                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision() ||
                        game.map.getTiles().tile[tileNum3].getCollision() || game.map.getTiles().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() || game.map.getTiles().tile[tileNum2].getNextMapBool()
                        || game.map.getTiles().tile[tileNum3].getNextMapBool() || game.map.getTiles().tile[tileNum4].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());

                }
            }
            case "southEast" -> {
                botY = (int) ((doubleBotY * 32 + 0.5) / 32);
                tileNum1 = tileMap[leftSideX][botY];
                tileNum2 = tileMap[rightSideX][botY];
                rightSideX = (int) ((doubleRightSideX * 32 + 0.5) / 32);
                botY = (int) doubleBotY;
                tileNum3 = tileMap[rightSideX][botY];
                tileNum4 = tileMap[rightSideX][topY];
                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision() ||
                        game.map.getTiles().tile[tileNum3].getCollision() || game.map.getTiles().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() || game.map.getTiles().tile[tileNum2].getNextMapBool()
                        || game.map.getTiles().tile[tileNum3].getNextMapBool() || game.map.getTiles().tile[tileNum4].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());

                }
            }
            case "northWest" -> {
                topY = (int) ((doubleTopY * 32 - 0.5) / 32);
                tileNum1 = tileMap[leftSideX][topY];
                tileNum2 = tileMap[rightSideX][topY];
                leftSideX = (int) ((doubleLeftSideX * 32 - 0.5) / 32);
                topY = (int) doubleTopY;
                tileNum3 = tileMap[leftSideX][botY];
                tileNum4 = tileMap[leftSideX][topY];
                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision() ||
                        game.map.getTiles().tile[tileNum3].getCollision() || game.map.getTiles().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() || game.map.getTiles().tile[tileNum2].getNextMapBool()
                        || game.map.getTiles().tile[tileNum3].getNextMapBool() || game.map.getTiles().tile[tileNum4].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());

                }
            }
            case "southWest" -> {
                botY = (int) ((doubleBotY * 32 + 0.5) / 32);
                tileNum1 = tileMap[leftSideX][botY];
                tileNum2 = tileMap[rightSideX][botY];
                leftSideX = (int) ((doubleLeftSideX * 32 - 0.5) / 32);
                botY = (int) doubleBotY;
                tileNum3 = tileMap[leftSideX][botY];
                tileNum4 = tileMap[leftSideX][topY];
                if (game.map.getTiles().tile[tileNum1].getCollision() || game.map.getTiles().tile[tileNum2].getCollision() ||
                        game.map.getTiles().tile[tileNum3].getCollision() || game.map.getTiles().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
                } else if ((entity instanceof Player) && (game.map.getTiles().tile[tileNum1].getNextMapBool() || game.map.getTiles().tile[tileNum2].getNextMapBool()
                        || game.map.getTiles().tile[tileNum3].getNextMapBool() || game.map.getTiles().tile[tileNum4].getNextMapBool())) {
                    game.map.nextMap();
                    game.spawnEnemies(game.map.getMapCounter());
                    game.setWinCondition(game.map.getMapCounter());

                }
            }
        }
    }

}
