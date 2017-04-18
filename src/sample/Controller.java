package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class Controller {
    public void paintStones(MouseEvent e){
        System.out.println("ZZZZ");
        Image image = new Image("sample/images/our_stone.png");
        ImageView view = (ImageView) e.getSource();
        view.setImage(image);
        System.out.println("it works");
    }

}
