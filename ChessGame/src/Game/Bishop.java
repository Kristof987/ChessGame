package Game;

import java.util.List;

/**
 * 
 * A futó bábut képviselő osztály.
 *
 */
public class Bishop extends Piece {
	private static final long serialVersionUID=8699621094247683557L;
	/**
	 * A futó osztály konstruktora.
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
	public Bishop(int xp, int yp, boolean colour, String n, List<Piece> ps, boolean b, String p1, String p2, boolean p1c, boolean p2c) {
		super(xp, yp, colour, n, ps, b,p1,p2,p1c,p2c);
	}
	
	/**
	 * A futó bábuk mozgatását végzi, az új helyet is módosítja, valamint a listát is megfelelően frissíti.
	 * Ha nem lehetséges az adott pozícióba való mozgás, akkor a bábu nem lép oda.
	 * 
	 * @param xp
	 * @param yp
	 */
	public void move(int xp, int yp){
		Piece p = ChessGame.getPiece(xp*64, yp*64); //az adott pozícióban álló bábu (ha nem áll ott bábu, akkor null értékű)
		
		if(this.getColour()!=this.getActRound()) { //ha nem az adott színű játékos köre van
			x=this.getXP()*64; //akkor nincs módosítás
            y=this.getYP()*64;
            return;
		}
		if(!(Math.abs(xp-this.getXP())==(Math.abs(yp-this.getYP())))) { //ha a futó lépéstartományának nem megfelelő lépés
			x=this.getXP()*64; //akkor nincs módosítás
            y=this.getYP()*64;
            return;
		}
		if(xp>this.getXP()&&yp>this.getYP()) { //különböző lehetséges mozgásirányok (átlósan balra-jobbra, illetve fel és le)
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
		if(p!=null){ //ha a mezőn, ahova lépnénk, van bábu,
            if(p.getColour()!=this.getColour()){ //ami nem azonos színű (és beleesik a mozgástartományba)
            	p.kill(); //akkor leüthető
                
            }else{
                  x=this.getXP()*64; //egyébként nem
                  y=this.getYP()*64;
            return;
        }
        }
		
		int xx = this.getXP();
		int yy = this.getYP();
		int xxx=xp;
		int yyy=yp;
        this.setXP(xp); //ha ezidáig minden feltételnek eleget tettünk, akkor a lépés megvlósítható, a beállítások mentésre kerülnek
        this.setYP(yp);
           x=xp*64;
           y=yp*64;
        
        for(int i = 0; i<ps.size();i++) { //annak az ellenőrzése, hogy a lépés következtében a király sakkba került-e bármilyen bábu által
        	
        	if(ps.get(i).getColour()==this.getColour() && ps.get(i).getClass().getName()=="Game.King") {
        		Piece xd = ps.get(i);
        		for(int j = 0; j<ps.size();j++) {
        			if(ps.get(j).getColour()!=this.getColour()&&ps.get(j).canHitOppositeKing(xd.getXP(), xd.getYP())==true) { //ha igen
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
                         return;
        			}
        		}
        	}
        }     
    }

	/**
	 * Visszaadja, hogy az adott futó üti-e az ellentétes színű királyt.
	 * A "move" függvényhez hasonlóan vizsgálja a különböző lehetséges eseteket.
	 * 
	 * @param oppkingxp //ellentétes színű király xp értéke
	 * @param oppkingyp //ellentétes színű király yp értéke
	 */
	@Override
	public boolean canHitOppositeKing(int oppkingxp, int oppkingyp) {
		if(!(Math.abs(oppkingxp-this.getXP())==Math.abs(oppkingyp-this.getYP())))
			return false;
		
		if(oppkingxp>this.getXP()&&oppkingyp>this.getYP()) {
			for(int i=this.getXP()+1;i<oppkingxp;i++) {
				for(int j=this.getYP()+1;j<oppkingyp;j++) {
					if (Math.abs(i-oppkingxp)==Math.abs(j-oppkingyp)) {
						if(ChessGame.getPiece(i*64,j*64)!=null) {
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
						if(ChessGame.getPiece(i*64,j*64)!=null) {
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
						if(ChessGame.getPiece(i*64,j*64)!=null) {
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
						if(ChessGame.getPiece(i*64,j*64)!=null) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Visszaadja, hogy az adott futó az adott pozícióba léphet-e
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
		if(!(Math.abs(xp-this.getXP())==(Math.abs(yp-this.getYP())))) {
			return false;
		}
		if(xp>this.getXP()&&yp>this.getYP()) {
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
        				return false;
        			}
        		}
        	}
        }
		return true;     
    }
	
	
}
