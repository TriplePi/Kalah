package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Collocation;

import java.util.Arrays;

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

    FlowPane[] cells = {oneOurs,twoOurs,threeOurs,fourOurs,fiveOurs,sixOurs,oursKalah,oneEnemys,twoEnemys,threeEnemys,fourEnemys,fiveEnemys,sixEnemys,enemysKalah};

    public void paintStones(MouseEvent e){
        System.out.println("ZZZZ");
        Image image = new Image("sample/images/our_stone.png");
        ImageView view = (ImageView) e.getSource();
        view.setImage(image);
        System.out.println("it works");
    }

    public void start(MouseEvent e){
        Image stone = new Image("sample/images/our_stone.png");
        int num = 0;
        int[] stones = Collocation.getCollocation().getStones();
        System.out.println(Arrays.toString(stones));
        for (int i:stones) {
            System.out.println(i);
            for (int j = 0; j < i; j++) {
                //написать способ расположения камней

                cells[num].getChildren().add(new ImageView(stone));
                System.out.println(cells[num].getId()+" id");
            }
            num++;
        }
    }

}
