package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import models.AI;
import models.Collocation;
import models.SimpleCell;

import java.util.Arrays;

public class Controller {
    public Label whosTurnToGo;
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

    boolean firstMove = true;
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
        //System.out.println("ahtung " + Collocation.getCollocation().getPlayer());
        if (Collocation.getCollocation().getPlayer())
            playerAct((FlowPane) e.getSource());
        else
            do {
                botAct();
            }
            while (!Collocation.getCollocation().getPlayer() && Collocation.getCollocation().check() == 0);
        oursTheFury.setText(String.valueOf(Collocation.getCollocation().getAllStones()[6]) + "/36");
        enemysTheFury.setText(String.valueOf(Collocation.getCollocation().getAllStones()[13]) + "/36");
        if(Collocation.getCollocation().getPlayer())
        whosTurnToGo.setText("Ваш ход");
        else whosTurnToGo.setText("Чужой");
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

    void botAct() {
        System.out.println("botAct");
        AI ai = new AI();
        Collocation.change(new Collocation(ai.calculate(Collocation.getCollocation())));
        System.out.println(Collocation.getCollocation().getPlayer());
        if (!Collocation.getCollocation().getPlayer())
            System.out.println(Arrays.toString(Collocation.getCollocation().getAllStones()));
        synchronize();
//        if (Collocation.getCollocation().check() != 0 ||
//                (!Collocation.getCollocation().getPlayer() && Collocation.getCollocation().getPlayer()==Collocation.getCollocation().getPreviosPlayer())) {
//            System.out.println(Collocation.getCollocation().check() + "sovsem ahtung");
//            botAct();
//        }
    }

    void playerAct(FlowPane pane) {
        if (!(pane.getId().equals("oursKalah") || pane.getId().equals("enemysKalah"))) {
            if((firstMove && pane.getId().equals("oneOurs"))) {
                return;
            }
            firstMove = false;
            int i = 0;
            while (!cells[i].getId().equals(pane.getId()))
                i++;
            Collocation collocation = Collocation.getCollocation();
            SimpleCell cell = ((SimpleCell) collocation.getCell(i));
            if (cell.getStones() == 0) {
                return;
            }
            if (cell.getPlayer() == collocation.getPlayer()) {
                cell.act(Collocation.getCollocation());
                synchronize();
                if (!Collocation.getCollocation().getPlayer()) {
                    botAct();
                }
            }
        }
        synchronize();
    }
}


