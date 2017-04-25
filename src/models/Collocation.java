package models;

import java.util.Arrays;

/**
 * Created by Дарья on 20.04.2017.
 */
public class Collocation {
    boolean player = true;
    Cell[] cells;
    private static Collocation collocationForAll;

    public static void change(Collocation collocation){
        collocationForAll = collocation;
    }

    private Collocation() {
        int num = 3;
        cells = new Cell[14];
        for (int i = 0; i < 6; i++) {
            cells[i] = new SimpleCell(true, i + 1);
            cells[i].stones = num;
            cells[i + 7] = new SimpleCell(false, 6 - i);
            cells[i + 7].stones = num;
        }
        cells[6] = new Kalah(true);
        cells[13] = new Kalah(false);
        for (int i = 0; i < 13; i++) {
            if (i == 12) {
                cells[i].setNext(cells[13]);
                cells[13].setNext(cells[0]);
            } else cells[i].setNext(cells[i + 1]);
        }
        for (int i = 0; i < 6; i++) {
            if (cells[i] instanceof SimpleCell) {
                ((SimpleCell) cells[i]).setOpposite(cells[12 - i]);
                cells[i].kalah = (Kalah) cells[6];
                cells[i+7].kalah = (Kalah) cells[13];
            }
        }
//        int k = 0;
//        for (Cell cell : cells) {
//            System.out.println(k);
//            System.out.println(cell.getClass());
//            System.out.println(cell.getPlayer());
//            k++;
//        }
    }

    public Collocation(Collocation collocation) {
        cells = new Cell[14];
        for (int i = 0; i < 6; i++) {
            cells[i] = new SimpleCell(true, i + 1);
            cells[i + 7] = new SimpleCell(false, 6 - i);
        }
        cells[6] = new Kalah(true);
        cells[13] = new Kalah(false);
        for (int i = 0; i < 13; i++) {
            if (i == 12) {
                cells[i].setNext(cells[13]);
                cells[13].setNext(cells[0]);
            } else cells[i].setNext(cells[i + 1]);
        }
        for (int i = 0; i < 6; i++) {
            if (cells[i] instanceof SimpleCell) {
                ((SimpleCell) cells[i]).setOpposite(cells[12 - i]);
                ((SimpleCell) cells[12 - i]).setOpposite(cells[i]);
                cells[i].kalah = (Kalah) cells[6];
                cells[i + 7].kalah = (Kalah) cells[13];
            }
        }
        int k = 0;
        for (Cell cell : cells) {
            cell.stones = collocation.getAllStones()[k];
            k++;
        }
        this.player = collocation.getPlayer();
    }

    public static Collocation getCollocation() {
        if (collocationForAll == null) {
            collocationForAll = new Collocation();
        }
        return collocationForAll;
    }

    public int[] getAllStones() {
        int i = 0;
        int[] stones = new int[14];
        for (Cell cell : cells) {
            stones[i] = cell.getStones();
            i++;
        }
        return stones;
    }

    public Cell getCell(int i) {
        return cells[i];
    }

    public boolean getPlayer() {
        return player;
    }

    public void invertPlayer() {
        player = !player;
    }

    public int check(){
        if(this.getAllStones()[13]>36)
            return -1;
        if(this.getAllStones()[6]>36)
            return 1;
        if(this.getAllStones()[13]==this.getAllStones()[6] && this.getAllStones()[6]==36)
            return 2;
        return 0;
    }
}
