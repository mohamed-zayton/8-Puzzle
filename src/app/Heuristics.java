package app;

public class Heuristics {

	public enum HeuristicsType {
		NONE, MANHATTAN, EUCLIDEAN;
	}

    public int getHeuristics(State state, HeuristicsType heuristicsType) {
        if (heuristicsType == HeuristicsType.NONE)
            return 0;
        else if (heuristicsType == HeuristicsType.MANHATTAN)
            return calManhattan(state);
        else if (heuristicsType == HeuristicsType.EUCLIDEAN)
            return calEuclidean(state);
        else
            return 0;
    }


    private int calManhattan(State state) {

        int h = 0;
        for (int i = 0, actualRow, actualCol, slotNum, currRow, currCol; i < 9; i++) {
            actualRow = i/3;
            actualCol = i%3;
            slotNum = state.getValSlot((byte) (i + 1));
            currRow = slotNum/3;
            currCol = slotNum%3;
            h += Math.abs(actualRow - currRow) + Math.abs(actualCol - currCol);
        }

        return h;
    }

    private int calEuclidean(State state) {
        int h = 0;
        for (int i = 0, actualRow, actualCol, slotNum, currRow, currCol; i < 9; i++) {
            actualRow = i/3;
            actualCol = i%3;
            slotNum = state.getValSlot((byte) (i + 1));
            currRow = slotNum/3;
            currCol = slotNum%3;

            h += Math.sqrt(Math.pow((actualRow - currRow), 2) + Math.pow((actualCol - currCol), 2));
        }

        return h;
    }

}
