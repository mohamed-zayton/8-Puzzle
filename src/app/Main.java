package app;
import app.algorithms.A_STAR;
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

		/*PuzzleGUI puzzleGUI = null;// = new PuzzleGUI();
		Parent parent = null;
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
			//puzzleGUI = new PuzzleGUI();
			loader.setController(puzzleGUI);
			//puzzleGUI = loader.load();
			//loader.setRoot(parent);
			parent = loader.load();
			puzzleGUI = loader.getController();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		int state = 123456789;
		System.out.println(puzzleGUI);
		puzzleGUI.buildPuzzle();*/
		Scene scene = new Scene(new Pane(), 550, 730);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
		/*int boardSize = 3*3;
	    byte[] initialStateBytes = scanStartState(boardSize);
		byte[] optimizedInitialState = State.convertNormalStateBytesToOptimizedBytes(initialStateBytes, boardSize);
	    State initialState = new State(optimizedInitialState, boardSize);
		System.out.println(initialState);
	    List<State> neighbors = initialState.getNeighborStates();
	    for (State n : neighbors)
			System.out.println(n);
	    //TODO: Solve the puzzle here.*/
		int initialState = 867254301;
		IntState intState = new IntState();
		/*if(puzzleIsSolvable(initialState))
			System.out.println("Solvable");
		else
			System.out.println("Not Solvable");
*/
		//8 6 7
		// 2 5 4
		// 3 . 1
		A_STAR a = new A_STAR();
		List<Integer> path = a.AStar(initialState, IntState.HeuristicsType.EUCLIDEAN);
		System.out.println(path.size());
		for (int n : path)
			System.out.println(intState.intStateToString(n));


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
