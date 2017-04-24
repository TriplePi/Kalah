package models;

/**
 * Created by TriplePi on 16.04.2017.
 */
public class SimpleCell extends Cell {
    Cell opposite;
    int number;

    SimpleCell(boolean player, int number) {
        super(player);
        this.number = number;
    }

    public Cell getOpposite() {
        return opposite;
    }

    public boolean act() {
        int buffer = stones;
        this.stones = 0;
        Cell cell = getNext();
        while (buffer>0) {
            if(cell instanceof Kalah && cell.getPlayer()!=Collocation.getCollocation().getPlayer()){
                cell=cell.getNext();
                continue;
            }
            cell.incrementStone();
            if(buffer!=1)
                cell = cell.getNext();
            buffer--;

        }
        if(cell instanceof Kalah && cell.getPlayer()==Collocation.getCollocation().getPlayer()) {
            return true;
        }
        if(cell instanceof SimpleCell && cell.getPlayer()==Collocation.getCollocation().getPlayer() && cell.getStones()-1==0){
            buffer = ((SimpleCell) cell).getOpposite().stones;
            ((SimpleCell) cell).getOpposite().stones = 0;
            cell.kalah.addStones(buffer);
        }
        Collocation.getCollocation().invertPlayer();
        return false;
    }

    public void setOpposite(Cell opposite) {
        this.opposite = opposite;
    }
}
