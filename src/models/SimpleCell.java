package models;

/**
 * Created by TriplePi on 16.04.2017.
 */
public class SimpleCell extends Cell {
    SimpleCell opposite;
    int number;

    SimpleCell(boolean player, int number) {
        super(player);
        this.number = number;
    }

    public Cell getOpposite() {
        return opposite;
    }

    public boolean act(Collocation collocation) {
        int buffer = stones;
        this.stones = 0;
        Cell cell = getNext();
        while (buffer>0) {
            if(cell instanceof Kalah && cell.getPlayer()!=collocation.getPlayer()){
                cell=cell.getNext();
                continue;
            }
            cell.incrementStone();
            if(buffer!=1)
                cell = cell.getNext();
            buffer--;

        }
        if(cell instanceof Kalah && cell.getPlayer()==collocation.getPlayer()) {
            return true;
        }
        if(cell instanceof SimpleCell && cell.getPlayer()==collocation.getPlayer() && cell.getStones()-1==0){
            buffer = ((SimpleCell) cell).getOpposite().stones;
            ((SimpleCell) cell).getOpposite().stones = 0;
            cell.kalah.addStones(buffer);
        }
        collocation.invertPlayer();
//        if (collocation.getPlayer())
//            System.out.println("wtf");
        return false;
    }

    public void setOpposite(SimpleCell opposite) {
        this.opposite = opposite;
        opposite.opposite = this;
    }

    int getNumber(){
        return number;
    }
}
