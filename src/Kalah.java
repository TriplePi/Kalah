/**
 * Created by TriplePi on 16.04.2017.
 */
public class Kalah extends Cell {
    Kalah(boolean player){
        super(player);
    }

    void addStones(int a){
        this.stones+=a;
    }
}
