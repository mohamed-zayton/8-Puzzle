package app.main;
import app.IntState;
import app.algorithms.A_STAR;
import app.gui.PuzzleGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{

		PuzzleGUI puzzleGUI = null;
		Parent parent = null;
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/gui/gui.fxml"));
			loader.setController(puzzleGUI);
			parent = loader.load();
			puzzleGUI = loader.getController();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		int initialState = 867254301;

		puzzleGUI.buildPuzzle(initialState);
		Scene scene = new Scene(parent, 550, 730);
		primaryStage.setScene(scene);
		primaryStage.setTitle("8 Puzzle");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	    //TODO: Solve the puzzle here.*/

   }

   private static boolean puzzleIsSolvable(int initialState) {
		byte[] state = new IntState().intStateToByteArr(initialState);
	   int inv_count = 0;
	   for (int i = 0; i < 3 - 1; i++) {
		   for (int j = i + 1; j < 3; j++) {
			   // Value 0 is used for empty space
			   if (state[j * 3 + i] > 0 && state[j * 3 + i] > state[i * 3 + j])
				   inv_count++;
		   }
	   }

	   return inv_count%2 == 0;
   }

   private static byte[] scanStartState(int boardSize) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("**Enter the puzzle elements (use zero to express empty field)**");
	    byte[] inputState = new byte[boardSize];
	    for (int i = 0; i < inputState.length; i++) {
            System.out.print("Enter the field in row=" + (int)(i/Math.sqrt(boardSize)) + ", col=" + (int)(i%Math.sqrt(boardSize)) + " :-");
            inputState[i] = scanner.nextByte();
        }

	    return inputState;

   }

}
