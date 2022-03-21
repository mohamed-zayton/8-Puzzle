//package app;
//import java.util.*;
//
//
//public class BFS {
//	private State root ;
//    private Queue<State> queue ;
//    private HashMap<String, String> explored ;
//    private int index ;
//
//	public BFS(State s) {
//		root=s;
//		queue = new LinkedList<>();
//		explored = new HashMap<>();
//		index=0;
//	}
//
//	public State BFS_search() {
//		State current;
//        queue.add(root);
//
//        while (!queue.isEmpty())
//        {
//            current = queue.peek();
//            explored.put(current.getKey(), current.getKey());
//            queue.remove();
//            if(current.is_Goal())
//            {
//                System.out.println("target found at index: "+index);
//                return  current;
//            }
//
////            if()
//            index ++ ;
//            //add_children(current.generate_children);
//        }
//
//        return null;
//	}
//
//
////	 private void addChildren (State[] children)
////	    {
////	        boolean unique = true;
////	        for(State node : children)
////	        {
////	            if(node == null)
////	                break;
////	            for(byte[] a : explored)
////	            {
////	                if(Arrays.equals(a , node.getState()))
////	                {
////	                    unique = false;
////	                    break;
////	                }
////	                else
////	                    unique = true;
////	            }
////	            if(unique)
////	                queue.add(node);
////	        }
////	    }
//
//}
