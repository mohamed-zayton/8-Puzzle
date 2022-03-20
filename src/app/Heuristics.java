package app;

public class Heuristics {
     private byte[] Goal= {0,1,2,3,4,5,6,7,8};
     int Mode=-0;      // mode=0 -->no heuristics   mode=1 -->manhattan    mode=2  --> euclidean
     
     
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
	
	
	
	private int Manhattan() {
		
	}
    private int Eculidean() {
    	
    }
	public byte[] getGoal() {
		return Goal;
	}
    
   
	
}
