package app;

public class State {
    private int  Hn,depth;
    private String path;
    private Heuristics heuristic;
    private byte[] state;
    

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

