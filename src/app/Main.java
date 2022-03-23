package app;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		int boardSize = 3*3;
	    byte[] initialStateBytes = scanStartState(boardSize);
		System.out.println(Arrays.toString(initialStateBytes));
		byte[] optimizedInitialState = State.convertNormalStateBytesToOptimizedBytes(initialStateBytes, boardSize);
		System.out.println(Arrays.toString(optimizedInitialState));
	    State initialState = new State(optimizedInitialState, boardSize);
        System.out.println(initialState);
	    //TODO: Solve the puzzle here.
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
