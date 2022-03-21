package app;
import java.util.*;

public class Main {
	private  static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		 byte[] start = new byte[9];
         byte[] goal = {0,1,2,3,4,5,6,7,8};
          
         System.out.println("Enter Puzzle:");
         for(int i= 0 ; i < 9 ; i++)
             start[i] = input.nextByte();
        
         algorithm_choice(start, goal);
         
	}
   public static void algorithm_choice (byte[] start , byte[] goal)
   {
	   Heuristics heuristic;
       State s ;
	   
	   
	   System.out.printf("Choose algorithm\n1)BFS\n2)DFS\n3)A*\n>>");
	   byte choice=input.nextByte() ;
	  
	   switch (choice)
       {
           case 1:
               System.out.println("\n---------- BFS ----------");
               heuristic= new Heuristics(goal , 0);
               s= new State(start,heuristic);
               BFS bfs = new BFS(s);
               bfs.BFS_search();
               break;
           case 2:
        	   System.out.println("\n---------- DFS ----------");
        	  heuristic= new Heuristics(goal , 0);
        	   s= new State(start,heuristic);
        	   DFS dfs = new DFS(s);
        	   dfs.DFS_search();
        	   break;
           case 3:
        	   System.out.println("\n---------- A* ----------");
        	   System.out.printf("choose heuristic\n1)Manhattan\n2)Euclidean\n>>");
        	   choice=input.nextByte();
        	   heuristic= new Heuristics(goal ,choice);
           	   s= new State(start,heuristic);
           	A_STAR a_star= new A_STAR(s);
           	// method to solve a_star
           	   break;
       }
   }
}
