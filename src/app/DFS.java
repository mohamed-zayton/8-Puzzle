package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
	
	private State root ;
    private Stack<State> stack ;
    private ArrayList<byte[]> explored ;
    private int index ;
	
	public DFS(State s) {
		root=s;
		stack = new Stack<>();
		explored = new ArrayList<>();
		index=0;
	}
	
	
	public State DFS_search() {
		State current;
        stack.push(root);
        
        while (!stack.isEmpty())
        {
            current = stack.peek();
            explored.add(current.getState());
            stack.pop();
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
	                stack.push(node);
	        }
	    }

}
