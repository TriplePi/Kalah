/**
 * Created by TriplePi on 16.04.2017.
 */
public class SimpleCell extends Cell {
    Cell opposite;
    int number;

    SimpleCell(boolean player,int number){
        super(player);
        this.number = number;
    }

    public Cell getOpposite() {
        return opposite;
    }

    boolean act(){
        int buffer = stones;
        this.stones = 0;
        Cell cell = getNext();
        while (buffer>0){
            if(!(cell instanceof Kalah)){
                cell.incrementStone();
                buffer--;
                cell = cell.getNext();
                continue;
            }
            else {
                if(cell.player == this.player){
                    if(buffer == 1){
                        cell.incrementStone();
                        return true;
                    }
                }
                else{
                    cell = cell.getNext();
                    continue;
                }
            }
            if(buffer==1 && cell.getNext().player == this.player && cell.getNext() instanceof SimpleCell){
                this.kalah.addStones(getStones())
            }
        }
        return false;
    }
}
