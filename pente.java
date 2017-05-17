import javax.swing.JFrame;

public class pente {
	public static final int empty=0;
	public static final int blackst = 1;
	public static final int whites=-1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int boardw = 720;
		int boardwsq = 19;
		
		JFrame f = new JFrame("play pente all the time");
		pentegb p = new pentegb (boardw,boardwsq);
		f.setSize(boardw,boardw);
		f.setVisible(true);
		
		f.add(p);
		
}

}
