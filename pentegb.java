import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.synth.SynthSpinnerUI;

public class pentegb extends JPanel implements MouseListener {
	int bwpx;
	int bwsq;
	int sqwidth;
	int blackp1 = 0;
	int whitep1 = 0;
	int currentTurn = pente.blackst;
	boolean playcomp = false;
	int ralphstone;
	ralph compmovegen = null;
	Square[][] theb;
	Square s;

	// Color bsc = new Color (150,111,51);
	Square testsq;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		//System.out.println(x+" "+y);
		playGame(e);
		repaint();
	}


	pentegb(int bwpix, int bwsqu) {

		bwpx = bwpix;
		bwsq = bwsqu;
		sqwidth = (int) (bwpx - bwsq) / bwsq;
		bwpx = sqwidth*(bwsq+1);
		this.setSize(bwpx, bwpx);
		this.setBackground(Color.GRAY);

		theb = new Square[bwsq][bwsq];

		String compans = JOptionPane.showInputDialog("wanna play the computer?");
		for (int row = 0; row < bwsq; row++) {
			for (int collumn = 0; collumn < bwsq; ++collumn) {
				theb[row][collumn] = new Square((int) (collumn * sqwidth) + 2 * collumn,
						(int) (row * sqwidth) + 2 * row, sqwidth, row, collumn);
			}
		}
		theb[(int) (bwsq / 2)][(int) (bwsq / 2)].setState(pente.blackst);

		this.addMouseListener(this);
		if (compans.toLowerCase().equals("yes") || compans.toLowerCase().equals("y")) {
			repaint();
			playcomp = true;
			String whichturn = JOptionPane.showInputDialog("wanna be player 1 or 2?");
			System.out.println("Which turn "+whichturn);
			if(whichturn.equals("2")){
				ralphstone = 1;
				compmovegen = new ralph(this, ralphstone);
			}
			else if(whichturn.equals("1")){
				ralphstone = -1;
				compmovegen = new ralph(this, ralphstone);
				Square cs = compmovegen.randommove();
				cs.setState(ralphstone);
				this.changeTurn();
				this.requestFocus();
			}
			
			
		}
			System.out.println("ralphstone = "+ralphstone);
		changeTurn();
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(130, 63, 5));
		g.fillRect(0, 0, bwpx, bwpx);
		// testsq.draw(g);
		for (int row = 0; row < bwsq; row++) {
			for (int collumn = 0; collumn < bwsq; ++collumn) {
				theb[row][collumn].draw(g);
			}
		}

	}

	public void changeTurn() {
		if (currentTurn == pente.blackst) {
			currentTurn = pente.whites;
		} else {
			currentTurn = pente.blackst;
		}
	}

	public void playGame(MouseEvent e) {
		repaint();
		Square s = findSq(e.getX(), e.getY());
		if (s != null) {
			if (s.getState() == pente.empty && currentTurn != ralphstone) {
				System.out.println("PLAYERTURN");
				s.setState(currentTurn);
				this.repaint();
				this.checkcap(s);
				this.fiveinrow(s);
				this.changeTurn();
				requestFocus();
			}
			this.repaint();
			if (playcomp == true && currentTurn == ralphstone) {
				System.out.println("COMPUTERTURN");
				Square cs;
				compmovegen.clearlist();
				cs = compmovegen.offensecontrol(3,ralphstone);
				cs.setState(currentTurn);
				this.repaint();
				this.fiveinrow(cs);
				this.checkcap(cs);
				this.changeTurn();
				requestFocus();
			}
		} else {
			JOptionPane.showMessageDialog(null, "You cant do that");
		}
	}

	public Square findSq(int mousex, int mousey) {
		Square clickedone = null;
		for (int row = 0; row < bwsq; row++) {
			for (int collumn = 0; collumn < bwsq; ++collumn) {
				if (theb[row][collumn].youclicked(mousex, mousey) == true) {
					clickedone = theb[row][collumn];
				}
			}
		}
		return clickedone;
	}

	public int fivevert(Square s, boolean winning) {
		int srow = s.getrow();
		int scol = s.getcol();
		int st = s.getState();
		int counter = 1;
		int counter2 = 1;
		int inarow = 0;
		boolean continuous = true;
		if (srow <= bwsq - 4) {
			while (counter <= 4 && continuous == true) {
				if (theb[srow + counter][scol].getState() == st) {
					counter++;
					inarow++;
				}else if(theb[srow + counter][scol].getState() == pente.empty && theb[srow+counter+1][scol].getState()==st && winning==false){
					counter+=2;
					inarow++;
				} 
				else {
					continuous = false;
				}
			}
		}
		continuous = true;

		while (counter2 <= 4 && continuous == true && srow - counter2 >= 0) {
			if(srow-counter2>=0){
				if (theb[srow - counter2][scol].getState() == st) {
					if(srow-counter2-1>=0){
						counter2++;
					}
					inarow++;
				}else if(theb[srow - counter2][scol].getState() == pente.empty && theb[srow-counter2-1][scol].getState()==st && winning==false){
					if(srow-counter2-3>=0){
						counter2+=2;
					}
					inarow++;
				}else {
					continuous = false;
				}
			}
		}
		if (inarow >= 4 && winning==true) {
			win(st);
		}
		return inarow;

		
	}

	public int fivehoro(Square s, boolean winning) {
		int srow = s.getrow();
		int scol = s.getcol();
		int st = s.getState();
		int counter = 1;
		int counter2 = 1;
		int inarow = 0;
		boolean continuous = true;
		if (scol > 3) {
			while (counter <= 4 && continuous == true) {
				if (theb[srow][scol - counter].getState() == st) {
					counter++;
					inarow++;
				}else if(theb[srow][scol-counter].getState() == pente.empty && theb[srow][scol-counter-1].getState()==st && winning==false){
					counter+=2;
					inarow++;
				}  
				else {
					continuous = false;
				}
			}
		}
		continuous = true;
		if (scol < bwsq) {
			while (counter2 <= 4 && continuous == true) {
				//System.out.println("that last error " + (scol + counter2));
				if(scol+counter2<bwsq){
					if (theb[srow][scol + counter2].getState() == st && scol+counter+1<bwsq) {
						counter2++;
						inarow++;
					}else if(theb[srow][scol+counter2].getState() == pente.empty && theb[srow][scol+counter2+1].getState()==st && winning==false){
						if(scol+counter<bwsq){
							counter2+=2;
						}
						inarow++;
					} 
					else {
						continuous = false;
					}
				}
			}
		}
		if (inarow >= 4 && winning==true) {
			win(st);
		}
		return inarow;
	}

	public int fivediagup(Square s, boolean winning) {
		int srow = s.getrow();
		int scol = s.getcol();
		int st = s.getState();
		int counter = 1;
		int counter2 = 1;
		int inarow = 0;
		boolean continuous = true;
		if (srow >= 3 && scol <= bwsq - 3) {
			while (counter <= 4 && continuous == true) {
			//	if (srow - counter >= 0 && scol + counter <= bwsq - 1) {
					if (theb[srow - counter][scol + counter].getState() == st) {
						counter++;
						inarow++;
					}else if(theb[srow - counter][scol+counter].getState() == pente.empty && theb[srow-counter-1][scol+counter+1].getState()==st && winning==false){
						counter+=2;
						inarow++;
					}  
					else {
						continuous = false;
					}
				//}
			}
		}
		continuous = true;
		if (srow <= bwsq - 3 && scol >= 3) {
			while (counter2 <= 4 && continuous == true) {
			//	if(srow+counter2<bwsq&& scol-counter2>=0){
					if (theb[srow + counter2][scol - counter2].getState() == st) {
						counter2++;
						inarow++;
					}else if(theb[srow + counter2][scol-counter2].getState() == pente.empty && theb[srow+counter2+1][scol-counter2-1].getState()==st && winning==false){
						counter2+=2;
						inarow++;
					} 
				
				else {
					continuous = false;
				}
			//}
		}
		}
		if (inarow >= 4 && winning==true) {
			win(st);
		}
		return inarow;
	
	}

	public int fivediagdown(Square s, boolean winning) {
		int srow = s.getrow();
		int scol = s.getcol();
		int st = s.getState();
		int counter = 1;
		int counter2 = 1;
		int inarow = 0;
		boolean continuous = true;
		if (scol > 3 && srow > 3) {
			while (counter <= 4 && continuous == true) {
				if(srow-counter>=0 && scol-counter>=0){
					if (theb[srow - counter][scol - counter].getState() == st) {
						counter++;
						inarow++;
					}else if(theb[srow - counter][scol-counter].getState() == pente.empty && theb[srow-counter-1][scol-counter-1].getState()==st && winning==false){
						counter+=2;
						inarow++;
					}
					else {
						continuous = false;
					}
				}
			}
		}
		continuous = true;
		if (scol < bwsq - 3 && srow < bwsq - 3) {
			while (counter2 <= 4 && continuous == true) {
				if (theb[srow + counter2][scol + counter2].getState() == st) {
					counter2++;
					inarow++;
				} else if(theb[srow + counter2][scol+counter2].getState() == pente.empty && theb[srow+counter2+1][scol+counter2+1].getState()==st && winning==false){
					counter2+=2;
					inarow++;
				}
				else {
					continuous = false;
				}
			}
		}
		if (inarow >= 4 && winning==true) {
			win(st);
		}
		return inarow;
	}

	public void checkcap(Square s) {
		int srow = s.getrow();
		int scol = s.getcol();
		int opposite = s.getState() * (-1);

		if (srow <= bwsq - 3) {
			// horizontal
			if (srow < bwsq - 3) {
				if (theb[srow + 1][scol].getState() == opposite && theb[srow + 2][scol].getState() == opposite
						&& theb[srow][scol].getState() == theb[srow + 3][scol].getState()) {
					theb[srow + 1][scol].setState(pente.empty);
					theb[srow + 2][scol].setState(pente.empty);
					scorekeeper(s);

				}
			}
			// diagonals
			if (srow > 2 && scol > 2) {
				if (theb[srow - 1][scol - 1].getState() == opposite && theb[srow - 2][scol - 2].getState() == opposite
						&& theb[srow][scol].getState() == theb[srow - 3][scol - 3].getState()) {
					theb[srow - 1][scol - 1].setState(pente.empty);
					theb[srow - 2][scol - 2].setState(pente.empty);
					scorekeeper(s);

				}
			}
			if (srow > 2 && scol <= bwsq - 3) {
				if (theb[srow - 1][scol + 1].getState() == opposite && theb[srow - 2][scol + 2].getState() == opposite
						&& theb[srow][scol].getState() == theb[srow - 3][scol + 3].getState()) {
					theb[srow - 1][scol + 1].setState(pente.empty);
					theb[srow - 2][scol + 2].setState(pente.empty);
					scorekeeper(s);

				}
				if (theb[srow + 1][scol + 1].getState() == opposite && theb[srow + 2][scol + 2].getState() == opposite
						&& theb[srow][scol].getState() == theb[srow + 3][scol + 3].getState()) {
					theb[srow + 1][scol + 1].setState(pente.empty);
					theb[srow + 2][scol + 2].setState(pente.empty);
					scorekeeper(s);

				}
			}

			if (scol > 2) {
				if (theb[srow + 1][scol - 1].getState() == opposite && theb[srow + 2][scol - 2].getState() == opposite
						&& theb[srow][scol].getState() == theb[srow + 3][scol - 3].getState()) {
					theb[srow + 1][scol - 1].setState(pente.empty);
					theb[srow + 2][scol - 2].setState(pente.empty);
					scorekeeper(s);

				}
			}
		}

		if (srow > 2) {
			if (theb[srow - 1][scol].getState() == opposite && theb[srow - 2][scol].getState() == opposite
					&& theb[srow][scol].getState() == theb[srow - 3][scol].getState()) {
				theb[srow - 1][scol].setState(pente.empty);
				theb[srow - 2][scol].setState(pente.empty);
				scorekeeper(s);

			}
		}

		if (scol < bwsq - 3) {
			if (theb[srow][scol + 1].getState() == opposite && theb[srow][scol + 2].getState() == opposite
					&& theb[srow][scol].getState() == theb[srow][scol + 3].getState()) {
				theb[srow][scol + 1].setState(pente.empty);
				theb[srow][scol + 2].setState(pente.empty);
				scorekeeper(s);

			}
		}
		if (scol > 2) {
			if (theb[srow][scol - 1].getState() == opposite && theb[srow][scol - 2].getState() == opposite
					&& theb[srow][scol].getState() == theb[srow][scol - 3].getState()) {
				theb[srow][scol - 1].setState(pente.empty);
				theb[srow][scol - 2].setState(pente.empty);
				scorekeeper(s);

			}
		}
	}

	public void fiveinrow(Square s) {
		fivehoro(s,true);
		fivevert(s,true);
		fivediagup(s,true);
		fivediagdown(s,true);
	}

	public void scorekeeper(Square s) {
		repaint();
		int capper = s.getState();
		if (capper == pente.blackst) {
			blackp1++;
		} else {
			whitep1++;
		}

		if (whitep1 == 5) {
			JOptionPane.showMessageDialog(null, "5 Captures for White");
			win(pente.whites);
			
		}

		if (blackp1 == 5) {
			JOptionPane.showMessageDialog(null, "5 Captures for Black");
			win(pente.blackst);
		}
	}

	public void win(int player) {
		repaint();
		if (player == pente.whites) {
			JOptionPane.showMessageDialog(null, "White wins");
			System.exit(0);
		}

		if (player == pente.blackst) {
			JOptionPane.showMessageDialog(null, "Black wins");
			System.exit(0);
		}
		
	}

	public int getbwsq() {
		return bwsq;
	}

	public Square[][] getlegitboard() {
		return theb;
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
