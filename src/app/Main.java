package app;
import java.util.*;

public class Main {

	public static void main(String[] args) {
	    byte[] initialStateBytes = scanStartState();
	    byte[] optimizedInitialState = State.convertNormalStateBytesToOptimizedBytes(initialStateBytes);
	    State initialState = new State(optimizedInitialState);
        System.out.println(initialState);
	    //TODO: Solve the puzzle here.
   }

   private static byte[] scanStartState() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("**Enter the puzzle elements (use zero to express empty field)**");
	    byte[] inputState = new byte[9];
	    for (int i = 0; i < inputState.length; i++) {
            System.out.print("Enter the field in row=" + (int)(i/3) + ", col=" + i%3 + " :-");
            inputState[i] = scanner.nextByte();
        }

	    return inputState;

   }

}
