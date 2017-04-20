package models;

/**
 * Created by TriplePi on 16.04.2017.
 */
public class Cell {
    int stones = 0;
    Cell next;
    Kalah kalah;
    boolean player;

    Cell(boolean player){
        this.player = player;
    }

    Cell getNext(){
        return next;
    }

    public int getStones() {
        return stones;
    }

    void incrementStone(){
        stones++;
    }

    void setNext(Cell next){
        this.next = next;
    }
}
