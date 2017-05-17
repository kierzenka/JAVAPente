import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Square {
	
	int xl,yl,srow,scol;
	static int sw;
	Color bscolor = new Color(255, 221, 94);
	Color chcolor = new Color(26, 13, 0);
	int squarest = pente.empty;
	public Square(int x,int y, int w, int row, int col){
		xl=x;
		yl=y;
		sw=w;
		srow=row;
		scol=col;
	}
	public void draw(Graphics g){
		g.setColor(bscolor);
		g.fillRect(xl, yl, sw, sw);
		g.setColor(chcolor);
		//g.drawLine(xl+((int) sw/2),yl,xl+((int) sw/2),yl+sw);
		//g.drawLine(xl,yl+((int)sw/2),xl+sw,yl+((int)sw/2));
		if(squarest == pente.blackst){
			g.setColor(Color.BLACK);
			g.fillOval(xl+3,yl+3,sw-6,sw-6);
		}
		if(squarest == pente.whites){
			g.setColor(Color.WHITE);
			g.fillOval(xl+3,yl+3,sw-6,sw-6);
		}
	}
	public void setState(int newState){
		squarest = newState;
	}
	public int getState(){
		return squarest;
	}
	public int getrow(){
		return srow;
	}
	public int getcol(){
		return scol;
	}
	
	public boolean youclicked(int x,int y){
		boolean didyouclick = false;
		if(x>=xl && x<=xl+sw && y>=yl && y<=yl+sw){
			didyouclick = true;
		}
		return didyouclick;
	}
}
