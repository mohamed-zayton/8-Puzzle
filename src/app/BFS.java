package app;
import java.lang.reflect.Array;
import java.util.*;


public class BFS {
	private State root ;
    private Queue<State> queue ;
    private State goal;
    private HashMap<String, String> explored;
    private HashMap<String, String> parentMap;
    private int index ;

	public BFS(State s) {
		root=s;
		queue = new LinkedList<>();
		parentMap = new HashMap<>();
		explored = new HashMap<>();
		index=0;
	}

	public State BFS_search() {
		State current = null;
        queue.add(root);
        parentMap.put(root.getKey(), root.getKey());

        while (!queue.isEmpty())
        {
            current = queue.peek();
            explored.put(current.getKey(), current.getKey());
            queue.remove();
            if(current.is_Goal())
            {
                System.out.println("target found at index: "+index);
                creatPath(current);
                return  current;
            }

            index ++ ;
            ArrayList<State> children = new ArrayList<>();
            for(byte[] tiles: current.getNeighbours()) {
                children.add(new State(tiles, new Heuristics(0)));
            }
                addChildren(children, current);
        }
        goal = current;



        return goal;
	}

	public void creatPath(State goal) {
        String encoding;
        while (!(parentMap.get(goal.getKey()).equals(goal.getKey()))) {
            encoding = parentMap.get(goal.getKey());
            goal.print_state();
            State state = new State(mapStringToByte(encoding), new Heuristics(0));
            goal.setParent(state);
            goal = state;
        }
    }



	private byte[] mapStringToByte(String str) {
	    byte[] state = str.getBytes();

	    for(int i = 0; i < str.length(); i++) {
	        state[i] = (byte)(state[i] - 48);
        }

	    return state;
    }


	 private void addChildren (ArrayList<State> children, State parent)
	    {
	        for(State node : children)
	        {
                if(node == null) {
                    break;
                }
                if(!explored.containsKey(node.getKey()) && !parentMap.containsKey(node.getKey())) {
                    queue.add(node);
                    parentMap.put(node.getKey(), parent.getKey());
                }
            }
	    }

}
