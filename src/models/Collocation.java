package models;

import java.util.Arrays;

/**
 * Created by Дарья on 20.04.2017.
 */
public class Collocation {
    boolean player = true;
    Cell[] cells;
    private static Collocation collocationForAll;

    private Collocation() {
        int num = 6;
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
                if (cells[i].getPlayer())
                    cells[i].kalah = (Kalah) cells[6];
                else cells[i].kalah = (Kalah) cells[13];
            }
        }
        int k = 0;
        for (Cell cell : cells) {
            System.out.println(k);
            System.out.println(cell.getClass());
            System.out.println(cell.getPlayer());
            k++;
        }
    }

    public Collocation(int[] stones) {
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
                if (cells[i].getPlayer())
                    cells[i].kalah = (Kalah) cells[6];
                else cells[i].kalah = (Kalah) cells[13];
            }
        }
        int k = 0;
        for (Cell cell : cells) {
            cell.stones = stones[k];
            k++;
        }
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
}
