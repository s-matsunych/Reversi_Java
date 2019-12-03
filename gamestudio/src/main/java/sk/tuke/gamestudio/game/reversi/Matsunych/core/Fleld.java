package sk.tuke.gamestudio.game.reversi.Matsunych.core;

import java.lang.reflect.Field;

public class Fleld {
    private static int x = 8;
    private static int y = 8;
    private Tile[][] tiles;



    public Fleld() {
        this.tiles = new Tile[x][y];
    }

    public void genrerFled() {

        for (int i = 0; y > i; i++) {
            for (int j = 0; x > j; j++) {
                tiles[i][j] = new Tile();
            }
        }
        int x1 = tiles.length / 2 - 1;
        int y1 = tiles[0].length / 2 - 1;
        tiles[x1][y1].setState(TileState.WHITE);
        tiles[x1 + 1][y1 + 1].setState(TileState.WHITE);
        tiles[x1 + 1][y1].setState(TileState.BLACK);
        tiles[x1][y1 + 1].setState(TileState.BLACK);




//        tiles[0][0].setState(TileState.WHITE);
//        tiles[1][0].setState(TileState.WHITE);
//        tiles[2][0].setState(TileState.WHITE);
//        tiles[0][1].setState(TileState.BLACK);
//        tiles[1][1].setState(TileState.BLACK);
//        tiles[2][1].setState(TileState.BLACK);
//        tiles[3][6].setState(TileState.BLACK);
//        tiles[3][0].setState(TileState.BLACK);
//        tiles[1][4].setState(TileState.BLACK);



    }

    public void printFled() {
        if (tiles != null) {
            for (int i = 0; y > i; i++) {
                for (int j = 0; x > j; j++) {
                    System.out.print(tiles[j][i].printState());
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println("-------------------\n" +
                "///////////////////\n" +
                "-------------------");
        } else return;
    }

    public void revers(int x, int y) {
        if (tiles[x][y].getState() == TileState.WHITE) tiles[x][y].setState(TileState.BLACK);
        else if (tiles[x][y].getState() == TileState.BLACK) tiles[x][y].setState(TileState.WHITE);
    }

    public void PutTile(int s, int x, int y) {
        if ((tiles[x][y].getState() == TileState.EMPTY) && (s % 2 == 0)) tiles[x][y].setState(TileState.BLACK);

        if ((tiles[x][y].getState() == TileState.EMPTY) && (s % 2 != 0)) tiles[x][y].setState(TileState.WHITE);
    }

    public void chekAndRevers(int s, int x, int y) {

        if (tiles[x][y].getState() == TileState.EMPTY) {
            if (chekUp(s, x, y) || chekDown(s, x, y) || chekLeft(s, x, y) || chekRight(s, x, y) || chekLU(s, x, y) || chekLD(s, x, y) || chekRU(s, x, y) || chekRD(s, x, y)) {

                U_ARchek(s, x, y);
                D_ARchek(s, x, y);
                L_ARchek(s, x, y);
                R_ARchek(s, x, y);
                LU_ARchek(s, x, y);
                LD_ARchek(s, x, y);
                RU_ARchek(s, x, y);
                RD_ARchek(s, x, y);
                PutTile(s, x, y);
            }
        }
    }

    public void U_ARchek(int s, int x, int y) {
        if (chekUp(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;
            while (tiles[x1][y1 - 1].getState() != tiles[x][y].getState()) {
                y1--;
                revers(x1, y1);
            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    public void D_ARchek(int s, int x, int y) {
        if (chekDown(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;

            while (tiles[x1][y1 + 1].getState() != tiles[x][y].getState()) {
                y1++;
                revers(x1, y1);

            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    public void L_ARchek(int s, int x, int y) {
        if (chekLeft(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;

            while (tiles[x1 - 1][y1].getState() != tiles[x][y].getState()) {
                x1--;
                revers(x1, y1);
            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    public void R_ARchek(int s, int x, int y) {
        if (chekRight(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;

            while (tiles[x1 + 1][y1].getState() != tiles[x][y].getState()) {
                x1++;
                revers(x1, y1);
            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    public void LU_ARchek(int s, int x, int y) {
        if (chekLU(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;

            while (tiles[x1 - 1][y1 - 1].getState() != tiles[x][y].getState()) {
                x1--;
                y1--;
                revers(x1, y1);
            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    public void LD_ARchek(int s, int x, int y) {
        if (chekLD(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;

            while (tiles[x1 - 1][y1 + 1].getState() != tiles[x][y].getState()) {
                x1--;
                y1++;
                revers(x1, y1);
            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    public void RU_ARchek(int s, int x, int y) {
        if (chekRU(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;

            while (tiles[x1 + 1][y1 - 1].getState() != tiles[x][y].getState()) {
                x1++;
                y1--;
                revers(x1, y1);
            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    public void RD_ARchek(int s, int x, int y) {
        if (chekRD(s, x, y)) {
            PutTile(s, x, y);
            int x1 = x;
            int y1 = y;

            while (tiles[x1 + 1][y1 + 1].getState() != tiles[x][y].getState()) {
                x1++;
                y1++;
                revers(x1, y1);
            }
        }
        tiles[x][y].setState(TileState.EMPTY);
    }

    //-------------------------------------------------------------------------------------
    public boolean chekUp(int s, int x, int y) {
        int x1 = x;
        int y1 = y;

        if (tiles[x][y].getState() == TileState.EMPTY && y > 0 && tiles[x1][y1 - 1].getState() == TileState.WHITE && s % 2 == 0) {
            while (y1 > 0) {
                y1--;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK ) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && y > 0 && tiles[x1][y1 - 1].getState() == TileState.BLACK && s % 2 != 0) {
                while (y1 > 0) {
                    y1--;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                return false;
            }
        }
        return false;
    }

    public boolean chekDown(int s, int x, int y) {
        int x1 = x;
        int y1 = y;

        if (tiles[x][y].getState() == TileState.EMPTY && y < tiles[0].length - 1 && tiles[x1][y1 + 1].getState() == TileState.WHITE && s % 2 == 0) {
            while (y1 < tiles[0].length - 1) {
                y1++;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && y < tiles[0].length - 1 && tiles[x1][y1 + 1].getState() == TileState.BLACK && s % 2 != 0) {
                while (y1 < tiles[0].length - 1) {
                    y1++;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                return false;
            }
        }
        return false;
    }

    public boolean chekRight(int s, int x, int y) {
        int x1 = x;
        int y1 = y;

        if (tiles[x][y].getState() == TileState.EMPTY && x < tiles.length - 1 && tiles[x1 + 1][y1].getState() == TileState.WHITE && s % 2 == 0) {
            while (x1 < tiles.length - 1) {
                x1++;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && x < tiles.length - 1 && tiles[x1 + 1][y1].getState() == TileState.BLACK && s % 2 != 0) {
                while (x1 < tiles.length - 1) {
                    x1++;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                //return false;
            }
        }
        return false;
    }

    public boolean chekLeft(int s, int x, int y) {
        int x1 = x;
        int y1 = y;

        if (tiles[x][y].getState() == TileState.EMPTY && x > 0 && tiles[x1 - 1][y1].getState() == TileState.WHITE && s % 2 == 0) {
            while (x1 > 0) {
                x1--;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && x > 0 && tiles[x1 - 1][y1].getState() == TileState.BLACK && s % 2 != 0) {
                while (x1 > 0) {
                    x1--;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                //return false;
            }
        }
        return false;
    }

    public boolean chekLU(int s, int x, int y) {
        int x1 = x;
        int y1 = y;

        if (tiles[x][y].getState() == TileState.EMPTY && (x > 0 && y > 0) && tiles[x1 - 1][y1 - 1].getState() == TileState.WHITE && s % 2 == 0) {
            while (x1 > 0 && y1 > 0) {
                x1--;
                y1--;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && (x > 0 && y > 0) && tiles[x1 - 1][y1 - 1].getState() == TileState.BLACK && s % 2 != 0) {
                while (x1 > 0 && y1 > 0) {
                    x1--;
                    y1--;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                return false;
            }
        }
        return false;// gfsd
    }


    public boolean chekLD(int s, int x, int y) {
        int x1 = x;
        int y1 = y;
        if (tiles[x][y].getState() == TileState.EMPTY && (x > 0 && y < tiles[0].length - 1) && tiles[x1 - 1][y1 + 1].getState() == TileState.WHITE && s % 2 == 0) {
            while (x1 > 0 && y1 < tiles[0].length - 1) {
                x1--;
                y1++;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && (x > 0 && y < tiles[0].length - 1) && tiles[x1 - 1][y1 + 1].getState() == TileState.BLACK && s % 2 != 0) {
                while (x1 > 0 && y1 < tiles[0].length - 1) {
                    x1--;
                    y1++;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                return false;
            }
        }
        return false;// gfsd
    }

    public boolean chekRU(int s, int x, int y) {
        int x1 = x;
        int y1 = y;

        if (tiles[x][y].getState() == TileState.EMPTY && (x < tiles.length - 1 && y > 0) && tiles[x1 + 1][y1 - 1].getState() == TileState.WHITE && s % 2 == 0) {
            while (x1 < tiles.length-1 && y1 > 0) {
                x1++;
                y1--;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && (x < tiles.length - 1 && y > 0) && tiles[x1 + 1][y1 - 1].getState() == TileState.BLACK && s % 2 != 0) {
                while (x1 < tiles.length-1 && y1 > 0) {
                    x1++;
                    y1--;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                return false;
            }
        }
        return false;// gfsd
    }

    public boolean chekRD(int s, int x, int y) {
        int x1 = x;
        int y1 = y;

        if (tiles[x][y].getState() == TileState.EMPTY && (x < tiles.length - 1 && y < tiles[0].length - 2) && tiles[x1 + 1][y1 + 1].getState() == TileState.WHITE && s % 2 == 0) {
            while (x1 < tiles.length-1 && y1 < tiles[0].length-1) {
                x1++;
                y1++;
                if (tiles[x1][y1].getState() == TileState.WHITE) continue;
                else if (tiles[x1][y1].getState() == TileState.BLACK) {
                    return true;
                }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
            }
        } else {
            x1 = x;
            y1 = y;
            if (tiles[x][y].getState() == TileState.EMPTY && (x < tiles.length - 1 && y < tiles[0].length - 2) && tiles[x1 + 1][y1 + 1].getState() == TileState.BLACK && s % 2 != 0) {
                while (x1 < tiles.length-1 && y1 < tiles[0].length-1) {
                    x1++;
                    y1++;
                    if (tiles[x1][y1].getState() == TileState.BLACK) continue;
                    else if (tiles[x1][y1].getState() == TileState.WHITE) {
                        return true;
                    }else if( tiles[x1][y1].getState() == TileState.EMPTY) return false;
                }

                return false;
            }
        }
        return false;// gfsd
    }

    public int whites() {
        int whites = 0;
        for (int y = 0; y < getY(); y++) {
            for (int x = 0; x < getX(); x++) {
                if(tiles[x][y].getState() == TileState.WHITE) whites++;
                else continue;
            }

        }
        return whites;
    }

    public int blacks() {
        int blacks = 0;
        for (int y = 0; y < getY(); y++) {
            for (int x = 0; x < getX(); x++) {
                if(tiles[x][y].getState() == TileState.BLACK) blacks++;
                else continue;
            }

        }
        return blacks;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }
    //   public Tile[][] getTiles() {
//       return tiles;
//   }
//
//    public void setTiles(Tile[][] tiles) {
//        this.tiles = tiles;
//    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }
}








