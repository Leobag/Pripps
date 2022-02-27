package com.company.model;



public class CollisionCheck {
    Game game;

    public CollisionCheck(Game game){
        this.game = game;
    }
    public void isCollison(Entity entity) {

        double doubleLeftSideX = game.getPlayer().getPosition().x;
        double doubleRightSideX = (game.getPlayer().getPosition().x + game.getPlayer().size);
        double doubleTopY = game.getPlayer().getPosition().y;
        double doubleBotY = (game.getPlayer().getPosition().y + game.getPlayer().size);

        int leftSideX = (int) doubleLeftSideX;
        int rightSideX = (int) doubleRightSideX;
        int topY = (int) doubleTopY;
        int botY = (int) doubleBotY;


        int[][] tileMap = game.getMapTileMatrix();

        int tileNum1, tileNum2, tileNum3, tileNum4;
        switch (entity.getDirection()) {
            case "north" -> {
                topY = (int) ((doubleTopY * 32 - 0.5) / 32);
                tileNum1 = tileMap[leftSideX][topY];
                tileNum2 = tileMap[rightSideX][topY];

                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision()) {
                    entity.collisionOn = true;
                }
            }
            case "south" -> {
                botY = (int) ((doubleBotY * 32 + 0.5) / 32);
                tileNum1 = tileMap[leftSideX][botY];
                tileNum2 = tileMap[rightSideX][botY];
                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision()) {
                    entity.collisionOn = true;
                }
            }
            case "west" -> {
                leftSideX = (int) ((doubleLeftSideX * 32 - 0.5) / 32);
                tileNum1 = tileMap[leftSideX][botY];
                tileNum2 = tileMap[leftSideX][topY];

                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision()) {
                    entity.collisionOn = true;
                }
            }
            case "east" -> {
                rightSideX = (int) ((doubleRightSideX * 32 + 0.5) / 32);
                tileNum1 = tileMap[rightSideX][botY];
                tileNum2 = tileMap[rightSideX][topY];
                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision()) {
                    entity.collisionOn = true;
                }
            }

            case "northEast" -> {
                topY = (int) ((doubleTopY * 32 - 0.5) / 32);
                tileNum1 = tileMap[leftSideX][topY];
                tileNum2 = tileMap[rightSideX][topY];
                rightSideX = (int) ((doubleRightSideX * 32 + 0.1) / 32);
                topY = (int) doubleTopY;
                tileNum3 = tileMap[rightSideX][botY];
                tileNum4 = tileMap[rightSideX][topY];
                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision() ||
                        game.getTile().tile[tileNum3].getCollision() || game.getTile().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
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
                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision() ||
                        game.getTile().tile[tileNum3].getCollision() || game.getTile().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
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
                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision() ||
                        game.getTile().tile[tileNum3].getCollision() || game.getTile().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
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
                if (game.getTile().tile[tileNum1].getCollision() || game.getTile().tile[tileNum2].getCollision() ||
                        game.getTile().tile[tileNum3].getCollision() || game.getTile().tile[tileNum4].getCollision()) {
                    entity.collisionOn = true;
                }
            }
        }
    }

}
