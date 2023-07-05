package Game;

import java.util.List;

/**
 * 
 * A királynő bábut képviselő osztály.
 *
 */
public class Queen extends Piece {
	private static final long serialVersionUID = 1234201087093353446L;
	
	/**
	 * A királynő osztály konstruktora.
	 * 
	 * @param xp
	 * @param yp
	 * @param colour
	 * @param n
	 * @param ps
	 * @param b
	 * @param p1
	 * @param p2
	 * @param p1c
	 * @param p2c
	 */
	public Queen(int xp, int yp, boolean colour, String n, List<Piece> ps, boolean b, String p1, String p2, boolean p1c, boolean p2c) {
		super(xp, yp, colour, n, ps, b,p1,p2,p1c,p2c);
	}
	
	/**
	 * A királynő bábuk mozgatását végzi, az új helyet is módosítja, valamint a listát is megfelelően frissíti.
	 * Ha nem lehetséges az adott pozícióba való mozgás, akkor a bábu nem lép oda.
	 * 
	 * @param xp
	 * @param yp
	 */
	public void move(int xp, int yp){//kommentek a futó osztályban
		Piece p = ChessGame.getPiece(xp*64, yp*64);
		if(this.getColour()!=this.getActRound()) {
			x=this.getXP()*64;
            y=this.getYP()*64;
            return;
		}
		if((yp==this.getYP())) {
    		if(xp>this.getXP()) {
    			for(int j = this.getXP()+1; j<xp;j++) {
    				if(ChessGame.getPiece(j*64,yp*64) !=null) {
    					x=this.getXP()*64;
                        y=this.getYP()*64;
                        return;
    				}	
    			}
    		
    		}
    		else if(xp<this.getXP()) {
    			for(int j = this.getXP()-1; j>xp;j--) {
    				if(ChessGame.getPiece(j*64,yp*64) !=null) {
    					x=this.getXP()*64;
                        y=this.getYP()*64;
                        return;
    				}	
    			}
    		}
    	}
    	else if (xp==this.getXP()) {
    		if(yp>this.getYP()) {
    			for(int j = this.getYP()+1; j<yp;j++) {
    				if(ChessGame.getPiece(xp*64,j*64) !=null) {
    					x=this.getXP()*64;
                        y=this.getYP()*64;
                        return;
    				}	
    			}
    		}
    		else if(yp<this.getYP()) {
    			for(int j = this.getYP()-1; j>yp;j--) {
    				if(ChessGame.getPiece(xp*64,j*64) !=null) {
    					x=this.getXP()*64;
                        y=this.getYP()*64;
                        return;
    				}	
    			}
    		}
    	}
		
    	else if(!(Math.abs(xp-this.getXP())==Math.abs(yp-this.getYP()))) {
			x=this.getXP()*64;
            y=this.getYP()*64;
            return;
		}
    	else if(xp>this.getXP()&&yp>this.getYP()) {
			for(int i=this.getXP()+1;i<xp;i++) {
				for(int j=this.getYP()+1;j<yp;j++) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						x=this.getXP()*64;
		                y=this.getYP()*64;
		                return;
						}
					}
				}
			}
		}
		else if(xp>this.getXP()&&yp<this.getYP()) {
			for(int i=this.getXP()+1;i<xp;i++) {
				for(int j=this.getYP()-1;j>yp;j--) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						x=this.getXP()*64;
		                y=this.getYP()*64;
		                return;
						}
					}
				}
			}
		}
		else if(xp<this.getXP()&&yp<this.getYP()) {
			for(int i=this.getXP()-1;i>xp;i--) {
				for(int j=this.getYP()-1;j>yp;j--) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						x=this.getXP()*64;
		                y=this.getYP()*64;
		                return;
						}
					}
				}
			}
		}
		else if(xp<this.getXP()&&yp>this.getYP()) {
			for(int i=this.getXP()-1;i>xp;i--) {
				for(int j=this.getYP()+1;j<yp;j++) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						x=this.getXP()*64;
		                y=this.getYP()*64;
		                return;
						}
					}
				}
			}
		}
		if(p!=null){
            if(p.getColour()!=this.getColour()){
            	p.kill();
                
            }else{
                  x=this.getXP()*64;
                  y=this.getYP()*64;
            return;
        }
        }
		int xx = this.getXP();
		int yy = this.getYP();
		int xxx=xp;
		int yyy=yp;
        this.setXP(xp);
        this.setYP(yp);
           x=xp*64;
        y=yp*64;
        for(int i = 0; i<ps.size();i++) {
        	if(ps.get(i).getColour()==this.getColour() && ps.get(i).getClass().getName()=="Game.King") {
        		Piece xd = ps.get(i);
        		for(int j = 0; j<ps.size();j++) {
        			if(ps.get(j).getColour()!=this.getColour()&&ps.get(j).canHitOppositeKing(xd.getXP(), xd.getYP())==true) {
        				this.setXP(xx);
        		        this.setYP(yy);
        		           x=xx*64;
        		           y=yy*64;
        		           if(p!=null) {
        		        	ps.add(p);
               				p.setXP(xxx);
               				p.setYP(yyy);
               				p.x=xxx*64;
               				p.y=yyy*64;
               				}
                         return;        			}
        		}
        	}
        }
    }

	/**
	 * Visszaadja, hogy az adott királynő üti-e az ellentétes színű királyt.
	 * A "move" függvényhez hasonlóan vizsgálja a különböző lehetséges eseteket.
	 * 
	 * @param oppkingxp //ellentétes színű király xp értéke
	 * @param oppkingyp //ellentétes színű király yp értéke
	 */
	@Override
	public boolean canHitOppositeKing(int oppkingxp, int oppkingyp) {
		if((oppkingyp==this.getYP())) {
    		if(oppkingxp>this.getXP()) {
    			for(int j = this.getXP()+1; j<oppkingxp;j++) {
    				if(ChessGame.getPiece(j*64,oppkingyp*64) !=null) {
    					return false;
    				}	
    			}
    		
    		}
    		else if(oppkingxp<this.getXP()) {
    			for(int j = this.getXP()-1; j>oppkingxp;j--) {
    				if(ChessGame.getPiece(j*64,oppkingyp*64) !=null) {
    					return false;
    				}	
    			}
    		}
    		
    	}
    	else if (oppkingxp==this.getXP()) {
    		if(oppkingyp>this.getYP()) {
    			for(int j = this.getYP()+1; j<oppkingyp;j++) {
    				if(ChessGame.getPiece(oppkingxp*64,j*64) !=null) {
    					return false;
    				}	
    			}
    		
    		}
    		else if(oppkingyp<this.getYP()) {
    			for(int j = this.getYP()-1; j>oppkingyp;j--) {
    				if(ChessGame.getPiece(oppkingxp*64,j*64) !=null) {
    					return false;
    				}	
    			}
    		}
    		
    	}
		
    	else if(!(Math.abs(oppkingxp-this.getXP())==Math.abs(oppkingyp-this.getYP()))) {
			return false;
		}
    	else if(oppkingxp>this.getXP()&&oppkingyp>this.getYP()) {
			for(int i=this.getXP()+1;i<oppkingxp;i++) {
				for(int j=this.getYP()+1;j<oppkingyp;j++) {
					if (Math.abs(i-oppkingxp)==Math.abs(j-oppkingyp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						return false;
						}
					}
				}
			}
		}
		else if(oppkingxp>this.getXP()&&oppkingyp<this.getYP()) {
			for(int i=this.getXP()+1;i<oppkingxp;i++) {
				for(int j=this.getYP()-1;j>oppkingyp;j--) {
					if (Math.abs(i-oppkingxp)==Math.abs(j-oppkingyp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						return false;
						}
					}
				}
			}
		}
		else if(oppkingxp<this.getXP()&&oppkingyp<this.getYP()) {
			for(int i=this.getXP()-1;i>oppkingxp;i--) {
				for(int j=this.getYP()-1;j>oppkingyp;j--) {
					if (Math.abs(i-oppkingxp)==Math.abs(j-oppkingyp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						return false;
						}
					}
				}
			}
		}
		else if(oppkingxp<this.getXP()&&oppkingyp>this.getYP()) {
			for(int i=this.getXP()-1;i>oppkingxp;i--) {
				for(int j=this.getYP()+1;j<oppkingyp;j++) {
					if (Math.abs(i-oppkingxp)==Math.abs(j-oppkingyp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
						return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Visszaadja, hogy az adott királynő az adott pozícióba léphet-e
	 * A "move" függvényhez hasonlóan vizsgálja a különböző lehetséges eseteket.
	 * Ez a függvény a bot lépéseihez van felhasználva.
	 * 
	 * @param xp
	 * @param yp
	 */
	@Override
	public boolean canMoveTo(int xp, int yp) {
		Piece p = ChessGame.getPiece(xp*64, yp*64);
		if(this.getColour()!=this.getActRound()) {
			return false;
		}
		if((yp==this.getYP())) {
    		if(xp>this.getXP()) {
    			for(int j = this.getXP()+1; j<xp;j++) {
    				if(ChessGame.getPiece(j*64,yp*64) !=null) {
    					return false;
    				}	
    			}
    		}
    		else if(xp<this.getXP()) {
    			for(int j = this.getXP()-1; j>xp;j--) {
    				if(ChessGame.getPiece(j*64,yp*64) !=null) {
    					return false;
    				}	
    			}
    		}
    	}
    	else if (xp==this.getXP()) {
    		if(yp>this.getYP()) {
    			for(int j = this.getYP()+1; j<yp;j++) {
    				if(ChessGame.getPiece(xp*64,j*64) !=null) {
    					return false;
    				}	
    			}
    		}
    		else if(yp<this.getYP()) {
    			for(int j = this.getYP()-1; j>yp;j--) {
    				if(ChessGame.getPiece(xp*64,j*64) !=null) {
    					return false;
    				}	
    			}
    		}
    	}
		
    	else if(!(Math.abs(xp-this.getXP())==Math.abs(yp-this.getYP()))) {
    		return false;
		}
    	else if(xp>this.getXP()&&yp>this.getYP()) {
			for(int i=this.getXP()+1;i<xp;i++) {
				for(int j=this.getYP()+1;j<yp;j++) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
							return false;
						}
					}
				}
			}
		}
		else if(xp>this.getXP()&&yp<this.getYP()) {
			for(int i=this.getXP()+1;i<xp;i++) {
				for(int j=this.getYP()-1;j>yp;j--) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
							return false;
						}
					}
				}
			}
		}
		else if(xp<this.getXP()&&yp<this.getYP()) {
			for(int i=this.getXP()-1;i>xp;i--) {
				for(int j=this.getYP()-1;j>yp;j--) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
							return false;
						}
					}
				}
			}
		}
		else if(xp<this.getXP()&&yp>this.getYP()) {
			for(int i=this.getXP()-1;i>xp;i--) {
				for(int j=this.getYP()+1;j<yp;j++) {
					if (Math.abs(i-xp)==Math.abs(j-yp)) {
						if(ChessGame.getPiece(i*64, j*64)!=null) {
							return false;
						}
					}
				}
			}
		}
		if(p!=null){
            if(p.getColour()==this.getColour()){
            	return false;
        }
        }
		
        for(int i = 0; i<ps.size();i++) {
        	if(ps.get(i).getColour()==this.getColour() && ps.get(i).getClass().getName()=="Game.King") {
        		Piece xd = ps.get(i);
        		for(int j = 0; j<ps.size();j++) {
        			if(ps.get(j).getColour()!=this.getColour()&&ps.get(j).canHitOppositeKing(xd.getXP(), xd.getYP())==true) {
        				return false;    			}
        		}
        	}
        }
		return true;
	}
}
