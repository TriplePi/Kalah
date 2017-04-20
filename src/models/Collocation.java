package models;

import java.util.Arrays;

/**
 * Created by Дарья on 20.04.2017.
 */
public class Collocation {
    Cell[] cells;
    private static Collocation collocationForAll;

    Collocation(){
        cells = new Cell[14];
        for (int i = 0; i < 6; i++) {
            cells[i] = new SimpleCell(true,i+1);
            cells[i].stones=6;
            cells[i+7] = new SimpleCell(false,6-i);
            cells[i+7].stones=6;
        }
        cells[6] = new Kalah(true);
        cells[13] = new Kalah(false);
        for (Cell cell:cells) {
            System.out.println(cell.player+" "+Integer.toString(cell.getStones()));

        }
        for (int i = 0; i < 13; i++) {
            if(i==12) {
                cells[i].setNext(cells[0]);
            }
            else cells[i].setNext(cells[i + 1]);
        }
        for (int i = 0; i < 6; i++) {
            if(cells[i] instanceof SimpleCell){
                ((SimpleCell)cells[i]).setOpposite(cells[12-i]);
            }
        }
    }

    public static Collocation getCollocation(){
        if(collocationForAll == null){
            collocationForAll = new Collocation();
        }
        return collocationForAll;
    }

    public int[] getAllStones(){
        System.out.println("getStones");
        int i = 0;
        int[] stones = new int[14];
        for (Cell cell:cells) {
            stones[i] = cell.getStones();
            i++;
        }
        return stones;
    }

}
