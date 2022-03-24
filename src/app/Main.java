package app;
import app.Algorithms.A_STAR;
import app.Algorithms.BFS;
import app.Algorithms.DFS;

import java.util.*;

public class Main {

	public static void main(String[] args) {
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
