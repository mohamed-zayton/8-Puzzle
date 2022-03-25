package app.gui;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tile extends StackPane {

    private Rectangle rectangle = new Rectangle();
    private Label label = new Label();
    private Queue<Timeline> timelinesQueue = new LinkedList<>();
    private float currX;
    private float currY;
    private boolean tileIsMoving = false;
    private PuzzleGUI puzzleGUI;

    public Tile(float x, float y, int num, PuzzleGUI puzzleGUI) {
        this.puzzleGUI = puzzleGUI;
        label.setText(String.format("%d", num));
        setPrefHeight(170);
        setPrefWidth(170);
        rectangle.setWidth(160);
        rectangle.setHeight(160);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.TRANSPARENT);

        getChildren().addAll(rectangle, label);
        setLayoutX(x);
        setLayoutY(y);
        //setTranslateX(x);
        //setTranslateY(y);
        currX = x;
        currY = y;
    }

    public void move(double addToX, double addToY, float speed) {
        KeyValue xKeyValue = new KeyValue(this.layoutXProperty(), getLayoutX() + addToX);
        KeyFrame xKeyFrame = new KeyFrame(Duration.millis(500 * (100 / speed)), xKeyValue);
        KeyValue yKeyValue = new KeyValue(this.layoutYProperty(), getLayoutY() + addToY);
        KeyFrame yKeyFrame = new KeyFrame(Duration.millis(500 * (100 / speed)), yKeyValue);
        Timeline movingTimeLine;

        if (addToY == 0)
            movingTimeLine = new Timeline(xKeyFrame);
        else
            movingTimeLine = new Timeline(yKeyFrame);

        movingTimeLine.setOnFinished(e -> {
            puzzleGUI.moveNextTile() ;
        });
        movingTimeLine.play();
    }


    public boolean isTileIsMoving() {
        return tileIsMoving;
    }

    public byte getTileVal() {
        return Byte.parseByte(label.getText());
    }



}
