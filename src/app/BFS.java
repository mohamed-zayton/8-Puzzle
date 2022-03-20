package app;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;



public class BFS {
	private State root ;
    private Queue<State> queue ;
    private ArrayList<byte[]> explored ;
    private int index ;
	
	public BFS(State s) {
		root=s;
		queue = new LinkedList<>();
		explored = new ArrayList<>();
		index=0;
	}
	
	public State BFS_search() {
		State current;
        queue.add(root);
        
        while (!queue.isEmpty())
        {
            current = queue.peek();
            explored.add(current.getState());
            queue.remove();
            if(current.is_Goal())
            {
                System.out.println("target found at index: "+index);
                return  current;
            }
            index ++ ;
            //add_children(current.generate_children);
        }
        
        return null;
	}
      
	
	 private void addChildren (State[] children)
	    {
	        boolean unique = true;
	        for(State node : children)
	        {
	            if(node == null)
	                break;
	            for(byte[] a : explored)
	            {
	                if(Arrays.equals(a , node.getState()))
	                {
	                    unique = false;
	                    break;
	                }
	                else
	                    unique = true;
	            }
	            if(unique)
	                queue.add(node);
	        }
	    }
	
}
