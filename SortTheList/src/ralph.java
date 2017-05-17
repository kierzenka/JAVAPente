import java.util.*;

public class ralph {
	pentegb theb;
	Square [][] gameboard;
	Square lastmove;
	int mysquarecolor;
	int bwsq;
	int chek;
	int bi;
	int moverow,movecol;
	ArrayList<Square> possiblemoves = new ArrayList<Square>();
	ArrayList<Square> black = new ArrayList<Square>();
	ArrayList<Square> white = new ArrayList<Square>();
	public ralph(pentegb b, int scolor){
		theb = b;
		mysquarecolor = scolor;
		bwsq=b.getbwsq();
		gameboard = b.getlegitboard();
		//System.out.println("made it");
	}
	public void clearlist(){
		possiblemoves.clear();
		black.clear();
		white.clear();
	}
	public Square defensecontrol (int chek){
		return defense(chek);
	}
	
	public Square defense(int chek){
		if(chek<1){
			return randommove();
		}
		if(white.size() >= 1){
			//white = sortList(white);
		}else{
			//System.out.println(white.size() + "\n");
			return randommove();
		}
		
		
		int best = getBestDefenseLine(white.get(0));
		//System.out.println(best);
		int bi = 0;
		for(int count = 1; count < white.size(); count++){
			if(getBestDefenseLine(white.get(count)) > best){
				bi = count;
				best = getBestDefenseLine(white.get(count));
				//System.out.println("best = "+best);
			}
		}
		if(best >= chek){
			if(theb.fivevert(white.get(bi),false) == getBestLine(white.get(bi))){
				return placedefenseY(white.get(bi), bi, chek);		
			}else if(theb.fivediagup(white.get(bi),false) == getBestDefenseLine(white.get(bi))){
				return placedefenseD1(white.get(bi), bi, chek);
			}else if(theb.fivediagdown(white.get(bi),false) == getBestDefenseLine(white.get(bi))){
				return placedefenseD2(white.get(bi), bi, chek);
			}else{
				return placedefenseX(white.get(bi), bi, chek);
			}
		}else{
			return offense(chek-1);
		}	
	}
	
	public int getBestDefenseLine(Square s){
		int bestNum = theb.fivevert(s,false);
		if(theb.fivehoro(s,false) > bestNum){
			bestNum = theb.fivehoro(s,false);
		}if(theb.fivediagup(s,false) > bestNum){
			bestNum = theb.fivediagup(s,false);
		}if(theb.fivediagdown(s,false) > bestNum){
			bestNum = theb.fivediagdown(s,false);
		}
		return bestNum;
	}
	
	public Square placedefenseY(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(gameboard[s.getrow()+r][s.getcol()].getState() == s.getState()){
			if(s.getrow()+r+1<=bwsq-1){
				r++;
			}
		}
		if(gameboard[s.getrow()+r][s.getcol()].getState() == pente.empty){
			toPlace = gameboard[s.getrow()+r][s.getcol()];
		}else{
			r = 1;
			while(gameboard[s.getrow()-r][s.getcol()].getState() == s.getState()){
				if(s.getrow()-r-1>=0){
					r++;
				}
			}
			if(gameboard[s.getrow()-r][s.getcol()].getState() == pente.empty){
				toPlace = gameboard[s.getrow()-r][s.getcol()];
			}else{
				white.remove(bi);
				toPlace = offense(c);
			}
		}
		return toPlace;
	}
	
	public Square placedefenseX(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(gameboard[s.getrow()][s.getcol()+r].getState() == s.getState()){
			if(s.getcol()+r+1<=bwsq-1){
				r++;
			}
		}
		if(gameboard[s.getrow()][s.getcol()+r].getState() == pente.empty){
			toPlace = gameboard[s.getrow()][s.getcol()+r];
		}else{
			r = 1;
			while(gameboard[s.getrow()][s.getcol()-r].getState() == s.getState()){
				if(s.getcol()-r-1>=0){
					r++;
				}
			}
			if(gameboard[s.getrow()][s.getcol()-r].getState() == pente.empty){
				toPlace = gameboard[s.getrow()][s.getcol()-r];
			}else{
				white.remove(bi);
				return offense(c--);
				
			}
		}
		return toPlace;
	}
	
	public Square placedefenseD1(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(gameboard[s.getrow()-r][s.getcol()+r].getState() == s.getState()){
			r++;
		}
		if(gameboard[s.getrow()-r][s.getcol()+r].getState() == pente.empty){
			toPlace = gameboard[s.getrow()-r][s.getcol()+r];
		}else{
			r = 1;
			while(gameboard[s.getrow()+r][s.getcol()-r].getState() == s.getState()){
				r++;
			}
			if(gameboard[s.getrow()+r][s.getcol()-r].getState() == pente.empty){
				toPlace = gameboard[s.getrow()+r][s.getcol()-r];
			}else{
				white.remove(bi);
				return offense(c--);		
			}
		}	
		return toPlace;
	}
	
	public Square placedefenseD2(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(gameboard[s.getrow()+r][s.getcol()+r].getState() == s.getState()){
			r++;
		}
		if(gameboard[s.getrow()+r][s.getcol()+r].getState() == pente.empty){
			toPlace = gameboard[s.getrow()+r][s.getcol()+r];
		}else{
			r = 1;
			while(gameboard[s.getrow()-r][s.getcol()-r].getState() == s.getState()){
				if(s.getcol()-r-1>=0 && s.getrow()-r-1>=0){
					r++;
				}
			}
			if(gameboard[s.getrow()-r][s.getcol()-r].getState() == pente.empty){
				toPlace = gameboard[s.getrow()-r][s.getcol()-r];
			}else{
				white.remove(bi);
				return offense(c--);			
			}
		}
		return toPlace;
	}
	
	public Square randommove (){
		int nextmovecol;
		int nextmoverow;
		Random rnd = new Random();
		Square computerchoice = null;
		do{
			nextmoverow = rnd.nextInt(bwsq);
			nextmovecol = rnd.nextInt(bwsq);
		}while(gameboard[nextmoverow][nextmovecol].getState()!= pente.empty);
		
		computerchoice = gameboard[nextmoverow][nextmovecol];
		return computerchoice;
	}
	
	
	
	//offense
	public Square offensecontrol (int chek,int ralphstone){
		for(int row = 0;row<bwsq;row++){
			for(int col = 0;col<bwsq;col++){
				if(gameboard[row][col].getState()==ralphstone){
					black.add(gameboard[row][col]);
				}
			}
		}
		for(int row = 0;row<bwsq;row++){
			for(int col = 0;col<bwsq;col++){
				if(gameboard[row][col].getState()==(ralphstone*-1)){
					white.add(gameboard[row][col]);
				}
			}
		}
		return offense(chek);
	}
	
	public Square offense(int chek){
		if(chek==2){
			return(defense(chek));
		}
		if(black.size() >= 1){
			//black = sortList(black);
		}else{
			//System.out.println(black.size() + "\n");
			return defense(chek);
		}
		
		int best = getBestLine(black.get(0));
		//System.out.println(best);
		int bi = 0;
		for(int count = 0; count < black.size(); count++){
			if(getBestLine(black.get(count)) > best){
				bi = count;
				best = getBestLine(black.get(count));
			}
		}
		if(black.isEmpty()){
			return defense(chek);
		}
		
		if(best >= chek&&black.size()!=0){
			if(theb.fivevert(black.get(bi),false) == getBestLine(black.get(bi))){
				possiblemoves.add(placeY(black.get(bi), bi, chek));		
			}
			if(bi<black.size()){
				if(theb.fivediagdown(black.get(bi),false) == getBestLine(black.get(bi))){
					possiblemoves.add(placeD2(black.get(bi), bi, chek));
				}
			}
			if(bi<black.size()){
				if(theb.fivediagup(black.get(bi),false) == getBestLine(black.get(bi))){
			 		possiblemoves.add(placeD1(black.get(bi), bi, chek));
				}
			if(bi<black.size()){	
				if(theb.fivehoro(black.get(bi),false)==getBestLine(black.get(bi))){
					possiblemoves.add(placeX(black.get(bi), bi, chek));
				}
			}
		}
			Random nd = new Random();
			int rand = nd.nextInt(possiblemoves.size());
			return possiblemoves.get(rand);
		}else{
			return defense(chek);
		}
		
	}
	
	public int getBestLine(Square s){
		int bestNum = theb.fivevert(s,false);
		if(theb.fivehoro(s,false) > bestNum){
			bestNum = theb.fivehoro(s,false);
		}if(theb.fivediagup(s,false) > bestNum){
			bestNum = theb.fivediagup(s,false);
		}if(theb.fivediagdown(s,false) > bestNum){
			bestNum = theb.fivediagdown(s,false);
		}

		return bestNum;
	}
	
	public Square placeY(Square s, int bi, int c){
		Square toPlace=null;
		int r = 1;
		while(gameboard[s.getrow()+r][s.getcol()].getState() == s.getState()){
			if(s.getrow()+r<bwsq-1){
				r++;
			}
		}
		if(gameboard[s.getrow()+r][s.getcol()].getState() == pente.empty&&lastmove!=gameboard[s.getrow()+r][s.getcol()]){
			if(suicidemove(gameboard[s.getrow()+r][s.getcol()])==false){
				toPlace = gameboard[s.getrow()+r][s.getcol()];
				lastmove=gameboard[s.getrow()+r][s.getcol()];
				return toPlace;
			}
		}else{
			r = 1;
			while(gameboard[s.getrow()-r][s.getcol()].getState() == s.getState()){
				r++;
			}
			if(gameboard[s.getrow()-r][s.getcol()].getState() == pente.empty&&lastmove!=gameboard[s.getrow()-r][s.getcol()]){
				if(suicidemove(gameboard[s.getrow()-r][s.getcol()])==false){
					toPlace = gameboard[s.getrow()-r][s.getcol()];
					lastmove = gameboard[s.getrow()-r][s.getcol()];
					return toPlace;
				}
			}else{
				black.remove(bi);
				return defense(c);
			}
		}
		return defense(c);
	}
	
	public Square placeX(Square s, int bi, int c){
		Square toPlace=null;
		int r = 1;
		while(gameboard[s.getrow()][s.getcol()+r].getState() == s.getState()){
			if(s.getcol()+r+1<bwsq){
				r++;
			}
		}
		if(gameboard[s.getrow()][s.getcol()+r].getState() == pente.empty&&lastmove!=gameboard[s.getrow()][s.getcol()+r]){
			if(suicidemove(gameboard[s.getrow()][s.getcol()+r])==false){
				toPlace = gameboard[s.getrow()][s.getcol()+r];
				lastmove = gameboard[s.getrow()][s.getcol()+r];
				return toPlace;
			}
		}else{
			r = 1;
			while(gameboard[s.getrow()][s.getcol()-r].getState() == s.getState()){
				if(s.getcol()-r-1>=0){
					r++;
				}
			}
			if(gameboard[s.getrow()][s.getcol()-r].getState() == pente.empty&&lastmove!=gameboard[s.getrow()][s.getcol()-r]){
				if(suicidemove(gameboard[s.getrow()][s.getcol()-r])==false){
					toPlace = gameboard[s.getrow()][s.getcol()-r];
					lastmove =gameboard[s.getrow()][s.getcol()-r];
					return toPlace;
				}
			}else{
				black.remove(bi);
				return defense(c);
			}
		}
		return defense(c);
	}
	
	public Square placeD1(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(gameboard[s.getrow()+r][s.getcol()-r].getState() == s.getState()){
			r++;
		}
		if(gameboard[s.getrow()+r][s.getcol()-r].getState() == pente.empty&&lastmove!=gameboard[s.getrow()+r][s.getcol()-r]){
			if(suicidemove(gameboard[s.getrow()+r][s.getcol()-r])==false){
				toPlace = gameboard[s.getrow()+r][s.getcol()-r];
				lastmove = gameboard[s.getrow()+r][s.getcol()-r];
				return toPlace;
			}
		}else{
			r = 1;
			while(gameboard[s.getrow()-r][s.getcol()+r].getState() == s.getState()){
				r++;
			}
			if(gameboard[s.getrow()-r][s.getcol()+r].getState() == pente.empty&&lastmove!=gameboard[s.getrow()-r][s.getcol()+r]){
				if(suicidemove(gameboard[s.getrow()-r][s.getcol()+r])==false){
					toPlace = gameboard[s.getrow()-r][s.getcol()+r];
					lastmove=gameboard[s.getrow()-r][s.getcol()+r];
					return toPlace;
				}
			}else{
				black.remove(bi);
				return defense(c);
			}
		}
		return defense(c);
	}
	
	public Square placeD2(Square s, int bi, int c){
		Square toPlace;
		int r = 1;
		while(gameboard[s.getrow()+r][s.getcol()+r].getState() == s.getState()){
			r++;
		}
		if(gameboard[s.getrow()+r][s.getcol()+r].getState() == pente.empty&&lastmove!=gameboard[s.getrow()+r][s.getcol()+r]){
			if(suicidemove(gameboard[s.getrow()+r][s.getcol()+r])==false){
				toPlace = gameboard[s.getrow()+r][s.getcol()+r];
				lastmove = gameboard[s.getrow()+r][s.getcol()+r];
				return toPlace;
			}
		}else{
			r = 1;
			while(gameboard[s.getrow()-r][s.getcol()-r].getState() == s.getState()){
				r++;
			}
			if(gameboard[s.getrow()-r][s.getcol()-r].getState() == pente.empty&&lastmove!=gameboard[s.getrow()-r][s.getcol()-r]){
				if(suicidemove(gameboard[s.getrow()-r][s.getcol()-r])==false){
					toPlace = gameboard[s.getrow()-r][s.getcol()-r];
					lastmove=gameboard[s.getrow()-r][s.getcol()-r];
					return toPlace;
				}
			}else{
				black.remove(bi);
				return defense(c);
			}
		}
		return defense(c);
	}

	public boolean suicidemove(Square placechoice){
		int pcol, prow;
		boolean goodmove=true;
		int state = placechoice.getState();
		pcol = placechoice.getcol();
		prow = placechoice.getrow();
		if(prow<bwsq-2 && prow>=2){
			if(gameboard[prow++][pcol].getState()==state){
				if(gameboard[prow+2][pcol].getState()==pente.empty && gameboard[prow-1][pcol].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow-1][pcol].getState()==pente.empty && gameboard[prow+2][pcol].getState()==state*-1){
					goodmove=false;
				}
			}
			if(gameboard[prow--][pcol].getState()==state){
				if(gameboard[prow-2][pcol].getState()==pente.empty && gameboard[prow+1][pcol].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow+1][pcol].getState()==pente.empty && gameboard[prow-2][pcol].getState()==state*-1){
					goodmove=false;
				}
			}
		}
		if(pcol>=2 && pcol<bwsq-2){
			if(gameboard[prow][pcol++].getState()==state){
				if(gameboard[prow][pcol+2].getState()==pente.empty && gameboard[prow][pcol-1].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow][pcol-1].getState()==pente.empty && gameboard[prow][pcol+2].getState()==state*-1){
					goodmove=false;
				}
			}
			if(gameboard[prow][pcol--].getState()==state){
				if(gameboard[prow][pcol-2].getState()==pente.empty && gameboard[prow][pcol+1].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow][pcol+1].getState()==pente.empty && gameboard[prow][pcol-2].getState()==state*-1){
					goodmove=false;
				}
			}	
		}
		if(prow<bwsq-2 && pcol<bwsq-2 && prow>=2 && pcol>=2){
			if(gameboard[prow++][pcol++].getState()==state){
				if(gameboard[prow+2][pcol+2].getState()==pente.empty && gameboard[prow--][pcol-1].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow--][pcol-1].getState()==pente.empty && gameboard[prow+2][pcol+2].getState()==state*-1){
					goodmove=false;
				}
			}
			if(gameboard[prow--][pcol--].getState()==state){
				if(gameboard[prow-2][pcol-2].getState()==pente.empty && gameboard[prow++][pcol+1].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow++][pcol+1].getState()==pente.empty && gameboard[prow-2][pcol-2].getState()==state*-1){
					goodmove=false;
				}
			}	
			
			
			if(gameboard[prow--][pcol++].getState()==state){
				if(gameboard[prow-2][pcol+2].getState()==pente.empty && gameboard[prow++][pcol-1].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow++][pcol-1].getState()==pente.empty && gameboard[prow-2][pcol+2].getState()==state*-1){
					goodmove=false;
				}
			}
			if(gameboard[prow++][pcol--].getState()==state){
				if(gameboard[prow+2][pcol-2].getState()==pente.empty && gameboard[prow--][pcol+1].getState()==state*-1){
					goodmove=false;
				}
				if(gameboard[prow--][pcol+1].getState()==pente.empty && gameboard[prow+2][pcol-2].getState()==state*-1){
					goodmove=false;
				}
			}	
		}
		
		if(goodmove==false){
			System.out.println("Suicide prevention~~~");
		}
		if(goodmove==true){
			System.out.println("checked and was fine");
		}
		return goodmove;
		
	}

}
