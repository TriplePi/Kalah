package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import models.AI;
import models.Collocation;
import models.SimpleCell;

public class Controller {
    @FXML
    FlowPane oneOurs;
    @FXML
    FlowPane twoOurs;
    @FXML
    FlowPane threeOurs;
    @FXML
    FlowPane fourOurs;
    @FXML
    FlowPane fiveOurs;
    @FXML
    FlowPane sixOurs;
    @FXML
    FlowPane oneEnemys;
    @FXML
    FlowPane twoEnemys;
    @FXML
    FlowPane threeEnemys;
    @FXML
    FlowPane fourEnemys;
    @FXML
    FlowPane fiveEnemys;
    @FXML
    FlowPane sixEnemys;
    @FXML
    FlowPane oursKalah;
    @FXML
    FlowPane enemysKalah;

    @FXML
    Label oursTheFury;
    @FXML
    Label enemysTheFury;

    @FXML
    private FlowPane[] cells;

    public void start(MouseEvent e) {
        fillCells();
        synchronize();
    }

    public void fillCells() {
        cells = new FlowPane[14];
        cells[0] = oneOurs;
        cells[1] = twoOurs;
        cells[2] = threeOurs;
        cells[3] = fourOurs;
        cells[4] = fiveOurs;
        cells[5] = sixOurs;
        cells[6] = oursKalah;
        cells[7] = oneEnemys;
        cells[8] = twoEnemys;
        cells[9] = threeEnemys;
        cells[10] = fourEnemys;
        cells[11] = fiveEnemys;
        cells[12] = sixEnemys;
        cells[13] = enemysKalah;
    }

    public void act(MouseEvent e) {
        System.out.println("ahtung "+Collocation.getCollocation().getPlayer());
        FlowPane pane = (FlowPane) e.getSource();
        if (!(pane.getId().equals("oursKalah") || pane.getId().equals("enemysKalah"))) {
            int i = 0;
            while (!cells[i].getId().equals(pane.getId()))
                i++;
            Collocation collocation = Collocation.getCollocation();
            SimpleCell cell = ((SimpleCell) collocation.getCell(i));
            if (cell.getPlayer() == collocation.getPlayer()) {
                cell.act(Collocation.getCollocation());
                if (!Collocation.getCollocation().getPlayer()) {

                    while (!Collocation.getCollocation().getPlayer()) {
                        AI ai = new AI();
                        System.out.println(Collocation.getCollocation().getPlayer());
                        Collocation.change(ai.calculate(Collocation.getCollocation()));

                        synchronize();
                        return;
//                        if(Collocation.getCollocation().check()!=0) {
//                            System.out.println(Collocation.getCollocation().check()+"sovsem ahtung");
//                            return;
//                        }
                    }
                }
            }
        }
        oursTheFury.setText(String.valueOf(Collocation.getCollocation().getAllStones()[6]));
        enemysTheFury.setText(String.valueOf(Collocation.getCollocation().getAllStones()[13]));
        synchronize();
    }

    public void synchronize() {
        Image stone = new Image("sample/images/our_stone.png");
        for (FlowPane pane : cells) {
            pane.getChildren().clear();
        }
        int num = 0;
        int[] stones = Collocation.getCollocation().getAllStones();
        for (int i : stones) {
            for (int j = 0; j < i; j++) {
                ImageView view = new ImageView(stone);
                cells[num].getChildren().add(view);
            }
            num++;
        }
    }


}
