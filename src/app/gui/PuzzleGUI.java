package app.gui;
import app.IntState;
import app.algorithms.A_STAR;
import app.algorithms.BFS;
import app.algorithms.DFS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PuzzleGUI implements Initializable {

    @FXML
    private AnchorPane parentLayout;

    @FXML
    private Pane puzzleLayout;

    @FXML
    private VBox controlVBox;

    @FXML
    private HBox optionsHBox;

    @FXML
    private VBox radioButtonsVBox;

    @FXML
    private RadioButton BFSRadioBtn;

    @FXML
    private ToggleGroup algoSelectToggle;

    @FXML
    private RadioButton DFSRadioBtn;

    @FXML
    private RadioButton ASRadioBtn;

    @FXML
    private VBox animationCtrVBox;

    @FXML
    private Slider speedSlider;

    @FXML
    private Label animationSpeedLabel;

    @FXML
    private HBox btnDataHBox;

    @FXML
    private Button shuffleBtn;

    @FXML
    private Button solveBtn;

    @FXML
    private VBox infoLabelsVBox;

    @FXML
    private Label costLabel;

    @FXML
    private Label nodesExpandedLabel;

    @FXML
    private Label searchDepthLabel;

    @FXML
    private Label runningTimeLabel;

    private int currState;
    private List<Integer> path;
    private int stateCount;

    public void buildPuzzle(int state) {
        puzzleLayout.getChildren().clear();
        this.currState = state;
        byte currElement;
        IntState intState = new IntState();
        for (byte i = 0; i < 9; i++) {
            currElement = intState.getValAtSlot(state, i);
            if (currElement == 0)
                continue;
            puzzleLayout.getChildren().add(new Tile((i % 3) * 180, (i / 3) * 180, currElement, this));
        }
    }

    public void moveTile(int oldState, int newState) {
        IntState intState = new IntState();
        int tempOldState = oldState, tempNewState = newState;
        byte tileVal = 0;
        for (byte i = 0; i < 9; i++) {
            if (tempOldState % 10 != tempNewState % 10) {
                tileVal = (byte) (tempOldState % 10 != 0 ? tempOldState % 10 : tempNewState % 10);
                break;
            }

            tempOldState /= 10;
            tempNewState /= 10;

        }

        byte emptySlotNum = intState.getValSlotNum(oldState, (byte) 0);
        byte tileNum = intState.getValSlotNum(oldState, tileVal);



        Tile tile = null;
        for (Tile t : puzzleLayout.getChildren().toArray(new Tile[0])) {
            if (t.getTileVal() == tileVal) {
                tile = t;
                break;
            }
        }

        if (tileNum == emptySlotNum - 1) {
            tile.move(180, 0, (float) speedSlider.getValue());
        } else if (tileNum == emptySlotNum + 1) {
            tile.move(-180, 0, (float) speedSlider.getValue());
        } else if (tileNum == emptySlotNum + 3) {
            tile.move(0, -180, (float) speedSlider.getValue());
        } else {
            tile.move(0, 180, (float) speedSlider.getValue());
        }

        //while (tile.isTileIsMoving());
    }

    protected void moveNextTile() {
        stateCount++;
        if (stateCount == path.size() - 1)
            return;
        moveTile(path.get(stateCount), path.get(stateCount + 1));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        solveBtn.setOnAction(e -> {
            stateCount = 0;
            if (BFSRadioBtn.isSelected()) {
                BFS bfs = new BFS();
                path = bfs.BFS(currState);
            } else if (DFSRadioBtn.isSelected()) {
                DFS dfs = new DFS();
                path = dfs.DFS(currState);
            } else {
                A_STAR a = new A_STAR();
                path = a.AStar(currState, IntState.HeuristicsType.EUCLIDEAN);
            }

            buildPuzzle(path.get(stateCount));
            moveTile(path.get(stateCount), path.get(stateCount + 1));
        });
    }
}
