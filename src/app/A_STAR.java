package app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class A_STAR {

	public A_STAR(State s, int heu) {
		// TODO Auto-generated constructor stub
		PriorityQueue<State> frontier = new PriorityQueue<>();
		HashSet<String> explored = new HashSet<String>();
		HashMap<String,String> childParent = new HashMap<>();
		frontier.add(s);


		while (!frontier.isEmpty()){
			var state = frontier.poll();
			explored.add(state.getKey());

			if (state.is_Goal()){
				break;
			}
			for (var ne: state.getNeighbours()) {
				var newState = new State(ne, new Heuristics(heu));
				if (!explored.contains(ne)) {
					frontier.add(newState);
				}else {
					var heuristicFun = newState.getHeuristic();
					var value = state.getDepth() + heuristicFun.get_Heuristics(newState.getState());
					newState.setParent(state);
					newState.setDepth(value);
					frontier.add(newState);

				}
			}


		}
	}

}
