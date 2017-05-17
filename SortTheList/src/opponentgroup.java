import java.util.ArrayList;

public class opponentgroup {
	ArrayList<Square> groupl;
	int grouplength = 0;
	int grouprank = 0;
	Square end1sq = null;
	Square end2sq = null;

	public opponentgroup(){
	groupl = new ArrayList<Square>();
	System.out.println("yay");
	}
	public void addsqtogroup(Square add){
		groupl.add(add);
		grouplength++;
		grouprank++;
	}
	public void setend1(Square which){
		end1sq = which;
	}
	public void setend2 (Square which){
		end2sq = which;
	}
	public Square getend1(){
		return end1sq;
	}
	public Square getend2(){
		return end2sq;
	}
	public int getrank(){
		return grouprank;
	}
	public void setGrouprank (int newrank){
		grouprank = newrank;
	} 
}
