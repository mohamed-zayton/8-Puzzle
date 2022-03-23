package app;

public class Heuristics {

	private enum HeuristicsType {
		NONE, MANHATTAN, EUCLIDEAN;
	}

    private byte[] Goal = {0, 1, 2, 3, 4, 5, 6, 7, 8};


    public Heuristics() {
    }

    public Heuristics(byte[] goal) {
        Goal = goal;
    }

    int get_Heuristics(byte[] a, HeuristicsType heuristicsType) {
        if (heuristicsType == HeuristicsType.NONE)
            return 0;
        else if (heuristicsType == HeuristicsType.MANHATTAN)
            return Manhattan(a);
        else if (heuristicsType == HeuristicsType.EUCLIDEAN)
            return Euclidean(a);
        else
            return 0;
    }


    private int Manhattan(byte[] tiles) {

        int h = 0;
        double currentRow, currentColumn, row, col;
        for (int i = 0; i < tiles.length; i++) {
            currentRow = Math.ceil(i / 3) - 1;
            currentColumn = i % 3;

            row = Math.ceil(tiles[i] / 3) - 1;
            col = tiles[i] % 3;

            h += Math.abs(row - currentRow) + Math.abs(col - currentColumn);
        }

        return h;
    }

    private int Euclidean(byte[] tiles) {
        int h = 0;
        double currentRow, currentColumn, row, col;
        for (int i = 0; i < tiles.length; i++) {
            currentRow = Math.ceil(i / 3) - 1;
            currentColumn = i % 3;

            row = Math.ceil(tiles[i] / 3) - 1;
            col = tiles[i] % 3;

            h += Math.sqrt(Math.pow((row - currentRow), 2) + Math.pow((col - currentColumn), 2));
        }

        return h;
    }

    public byte[] getGoal() {
        return Goal;
    }


}
