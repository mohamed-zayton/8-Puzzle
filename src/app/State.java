package app;

import java.util.ArrayList;
import java.util.Arrays;

public class State {

	private class Pos{
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int x;
		int y;
	}

    private int  Hn,depth;
    private String path;
    private Heuristics heuristic;
    private byte[] state;

	public void setParent(State parent) {
		this.parent = parent;
	}

	private State parent;
    

    public String getKey(byte[] state) {
    	String str = "";
    	for(int i = 0; i < state.length; i++) {

    		str += String.valueOf(state[i]);
		}

    	return str;
	}

	public String getKey() {
		String str = "";
		for(int i = 0; i < state.length; i++) {

			str += String.valueOf(state[i]);
		}

		return str;
	}

	public State(byte[] start, Heuristics h) {
      this(start,0,"0->",h );
		
		
 	}

    public State(byte[] start, int d, String p, Heuristics h) {
		state=start.clone();
		depth=d;
		path=p;
		heuristic=h;
		if(heuristic.Mode!=0)
		{
			Hn=heuristic.get_Heuristics(state);
		}
		
	}
    
    public void print_state() {
    	System.out.printf("\n\t%d %d %d\n\t%d %d %d\n\t%d %d %d\n\n",state[0],state[1],state[2],state[3],state[4],state[5],state[6],state[7],state[8]);
    }

	
    boolean is_Goal() {
    	byte[] g=heuristic.getGoal();
    	return(state[0]==g[0] && state[1]==g[1] && state[2]==g[2] && state[3]==g[3] && state[4]==g[4] && state[5]==g[5] && state[6] == g[6] && state[7]==g[7] && state[8]==g[8]);
    }

    private int getZeroPos() {
    	for(int i = 0; i < state.length; i++) {
    		if(state[i] == 0)
    			return i;
		}

    	return -1;
	}

	private byte[] swap(byte[] state, int i, int j) {
    	byte[] newState = state.clone();
    	byte temp = newState[i];
    	newState[i] = newState[j];
    	newState[j] = temp;
		return newState;
	}

	private int convertXYtoX(int x, int y, int n) {
    	return n*y+x;
	}

	private byte[][] convertTo2D(byte[] oneD, int n) {
		byte[][] twoD = new byte[3][3];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				twoD[i][j] = oneD[i*3+j];
			}
		}
		return twoD;
	}

    public ArrayList<byte[]> getNeighbours(){
    	byte[][] tiles = new byte[3][3];
		ArrayList<byte[]> neighbours = new ArrayList<>();
		int zero = getZeroPos();
		tiles = convertTo2D(state, 3);
		int x = zero%3;//column
		int y = zero/3;//row

		ArrayList<Pos> positions = new ArrayList<>();

		if(x+1 < 3) {
			positions.add(new Pos(x+1, y));
		}
		if(x-1 >= 0) {
			positions.add(new Pos(x-1, y));
		}
		if(y+1 < 3) {
			positions.add(new Pos(x, y+1));
		}
		if(y-1 >= 0) {
			positions.add(new Pos(x, y-1));
		}

		for(Pos pos: positions) {
			int i = convertXYtoX(pos.x, pos.y, 3);
			byte[] neighbour = swap(state, i, zero);

			neighbours.add(neighbour);
		}

		return neighbours;
	}

    
	public int getDepth() {
		return depth;
	}

	public String getPath() {
		return path;
	}


	public byte[] getState() {
		return state;
	}
    
    
   
}

