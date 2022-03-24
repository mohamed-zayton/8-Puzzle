package app.Algorithms;

import app.IntState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DFS {

    private Stack<Integer> frontier = new Stack<>();
    private HashSet<Integer> explored = new HashSet<>();
    private HashMap<Integer, Integer> parentMap = new HashMap<>();

    public List<Integer> DFS(int initialState) {
        frontier.clear();
        explored.clear();
        parentMap.clear();

        IntState intState = new IntState();
        frontier.add(initialState);
        parentMap.put(initialState, initialState);

        boolean goalFound = false;
        int currState;
        while (!frontier.isEmpty()) {
            currState = frontier.pop();
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

                frontier.add(n);
                parentMap.put(n, currState);
            }
        }

        return goalFound ? AlgorithmsBackTrack.backTrackPath(parentMap, intState.getGoalState()) : null;
    }

}