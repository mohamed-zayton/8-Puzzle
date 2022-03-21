package app;

public class Heuristics {
     private byte[] Goal= {0,1,2,3,4,5,6,7,8};
     int Mode=-1;      // mode=0 -->no heuristics   mode=1 -->manhattan    mode=2  --> euclidean
     
     
     public Heuristics(int m )
     {
         this(new byte[]{1,2,3,4,5,6,7,8,0} , m);
     }
	 
     public Heuristics(byte[] goal, int mode) {
		Goal=goal;
		if(mode==0||mode==1||mode==2)
			Mode=mode;
	}
	
     int get_Heuristics(byte[] a)
	{
		     if(Mode==0)
		         return 0;
		     else if(Mode==1)
		    	 return Manhattan(a);
		     else if(Mode==2)
		    	 return Eculidean(a);	
		     else 
		    	 return 0;
	}
	
	
	
	private int Manhattan(byte[] tiles) {

     	int h = 0;
     	double currentRow, currentColumn, row, col;
     	for(int i = 0; i < tiles.length; i++) {
     		currentRow = Math.ceil(i/3)-1;
     		currentColumn = i%3;

     		row = Math.ceil(tiles[i]/3)-1;
     		col = tiles[i]%3;

     		h += Math.abs(row - currentRow) + Math.abs(col - currentColumn);
		}

     	return h;
	}
    private int Eculidean(byte[] tiles) {
		int h = 0;
		double currentRow, currentColumn, row, col;
		for(int i = 0; i < tiles.length; i++) {
			currentRow = Math.ceil(i/3)-1;
			currentColumn = i%3;

			row = Math.ceil(tiles[i]/3)-1;
			col = tiles[i]%3;

			h += Math.sqrt(Math.pow((row - currentRow), 2) + Math.pow((col - currentColumn), 2));
		}

		return h;
    }
	public byte[] getGoal() {
		return Goal;
	}
    
   
	
}
