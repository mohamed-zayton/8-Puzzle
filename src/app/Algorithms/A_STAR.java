package app.Algorithms;

import app.IntState;

import java.util.*;

public class A_STAR {

	private class StateHeuristicHolder {
		private int state;
		private byte g;
		private byte h;

		private StateHeuristicHolder(int state, byte g, byte h) {
			this.state = state;
			this.g = g;
			this.h = h;
		}

		public int getState() {
			return state;
		}

		public byte getG() {
			return g;
		}

		public byte getH() {
			return h;
		}

		public byte getHeuristic() {
			return (byte) (h + g);
		}
	}

	private class HeuristicComparator implements Comparator<StateHeuristicHolder> {
		@Override
		public int compare(StateHeuristicHolder o1, StateHeuristicHolder o2) {
			return o1.getHeuristic() - o2.getHeuristic();
		}
	}


	public List<Integer> AStar(int initialState, IntState.HeuristicsType heuristicsType) {
		HeuristicComparator comparator = new HeuristicComparator();
		PriorityQueue<StateHeuristicHolder> frontier = new PriorityQueue<StateHeuristicHolder>(comparator);
		HashSet<Integer> explored = new HashSet<>();
		HashMap<Integer, Integer> parentMap = new HashMap<>();
		IntState intState = new IntState();
		frontier.add(new StateHeuristicHolder(initialState, (byte) 0, (byte) 0));
		parentMap.put(initialState, initialState);

		boolean goalFound = false;
		StateHeuristicHolder currStateHeuristicHolder;
		int currState;
		while (!frontier.isEmpty()) {
			currStateHeuristicHolder = frontier.poll();
			currState = currStateHeuristicHolder.getState();
			if (explored.contains(currState))
				continue;
			else if (intState.isGoalState(currState)) {
				goalFound = true;
				break;
			}

			explored.add(currState);
			List<Integer> neighbors = intState.getNeighborIntStates(currState);
			for (int n : neighbors) {
				if (explored.contains(n))
					continue;

				frontier.add(new StateHeuristicHolder(n, (byte) (currStateHeuristicHolder.getG() + 1), (byte) (intState.getHeuristics(n, heuristicsType))));
				parentMap.put(n, currState);
			}
		}

		return goalFound ? AlgorithmsBackTrack.backTrackPath(parentMap, intState.getGoalState()) : null;
	}

}
