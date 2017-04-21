package sample;

import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import models.Collocation;
import models.SimpleCell;

import java.util.ArrayList;

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
    private FlowPane[] cells;

    public void start(MouseEvent e) {
        fillCells();
        synhronize();
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
        FlowPane pane = (FlowPane) e.getSource();
        if (!(pane.getId().equals("oursKalah") || pane.getId().equals("enemysKalah"))) {
            int i = 0;
            while (!cells[i].getId().equals(pane.getId()))
                i++;
            Collocation collocation = Collocation.getCollocation();
            SimpleCell cell = ((SimpleCell) collocation.getCell(i));
            if (cell.getPlayer()==collocation.getPlayer()) {
                cell.act();
            }
        }
        synhronize();
        System.out.println(Collocation.getCollocation().getPlayer());

    }

    public void synhronize() {
        Image stone = new Image("sample/images/our_stone.png");
        for (FlowPane pane : cells) {
            pane.getChildren().clear();
        }
        int num = 0;
        int[] stones = Collocation.getCollocation().getAllStones();
        for (int i : stones) {
            for (int j = 0; j < i; j++) {
                ImageView view =new ImageView(stone);
                cells[num].getChildren().add(view);
            }
            num++;
        }
    }


}
