package models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Дарья on 20.04.2017.
 */
public class Collocation {
    boolean previousPlayer = true;
    boolean player = true;
    Cell[] cells;
    private static Collocation collocationForAll;
    private static ArrayList<String> log;

    public static void change(Collocation collocation){
        collocationForAll = collocation;
    }

    private Collocation() {
        log = new ArrayList<>();
        int num = 6;
        cells = new Cell[14];
        for (int i = 0; i < 6; i++) {
            cells[i] = new SimpleCell(true, i );
            cells[i].stones = num;
            cells[i + 7] = new SimpleCell(false, i+7);
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
        System.out.println(Arrays.toString(getAllStones()));
        for (int i = 0; i < 6; i++) {
            if (cells[i] instanceof SimpleCell) {
                ((SimpleCell) cells[i]).setOpposite((SimpleCell) cells[12 - i]);
                cells[i].kalah = (Kalah) cells[6];
                cells[i+7].kalah = (Kalah) cells[13];
            }
        }

//        for (Cell cell:cells) {
//            if(cell instanceof SimpleCell) {
//                System.out.print(((SimpleCell) cell).getNumber());
//                System.out.println(((SimpleCell) ((SimpleCell) cell).getOpposite()).getNumber());
//            }
//        }
//        int k = 0;
//        for (Cell cell : cells) {
//            System.out.println(k);
//            System.out.println(cell.getClass());
//            System.out.println(cell.getPlayer());
//            k++;
//        }
    }

    public void setPlayer(boolean player){
        this.player = player;
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
                ((SimpleCell) cells[i]).setOpposite((SimpleCell) cells[12 - i]);
                ((SimpleCell) cells[12 - i]).setOpposite((SimpleCell) cells[i]);
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

    public boolean getPreviosPlayer(){
        return previousPlayer;
    }

    public void invertPlayer() {
        previousPlayer = player;
        this.player = !player;
    }
    // проверка на выигрыш/проигрыш/ничью
    public int check(){
        int[] stones = this.getAllStones();
        int tCount =0;
        int fCount = 0;
        for (int i = 0; i < 14; i++) {
            if(i!= 6 && i!=13){
                if(i<6)
                    tCount+=stones[i];
                else fCount+=stones[i];
            }
        }
        if(stones[13]>36 || (tCount==0 && stones[6]<36))
            return -1;
        if(stones[6]>36 || (fCount==0 && stones[13]<36))
            return 1;
        if(stones[13]==stones[6] && stones[6]==36)
            return 2;
        return 0;
    }
}