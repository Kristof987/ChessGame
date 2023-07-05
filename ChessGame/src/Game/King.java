package Game;

import java.util.List;

/**
 * 
 * A király bábut képviselő osztály.
 *
 */
public class King extends Piece {
	private static final long serialVersionUID = -581588126617843738L;
	

	/**
	 * A király osztály konstruktora.
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
	public King(int xp, int yp, boolean colour, String n, List<Piece> ps, boolean b, String p1, String p2, boolean p1c, boolean p2c) {
		super(xp, yp, colour, n, ps, b,p1,p2,p1c,p2c);
	}
	/**
	 * A király bábuk mozgatását végzi, az új helyet is módosítja, valamint a listát is megfelelően frissíti.
	 * Ha nem lehetséges az adott pozícióba való mozgás, akkor a bábu nem lép oda.
	 * 
	 * @param xp
	 * @param yp
	 */
	@Override
	public void move(int xp, int yp) { //kommentek a futó osztályban
		Piece p = ChessGame.getPiece(xp*64, yp*64);
		
		if(this.getColour()!=this.getActRound()) {
			x=this.getXP()*64;
            y=this.getYP()*64;
            return;
		}
		
		if(!((xp==this.getXP()+1&&yp==this.getYP())|| (xp== this.getXP()-1&&yp==this.getYP())|| (yp==this.getYP()+1&&xp==this.getXP()) || (yp==this.getYP()-1&&xp==this.getXP())|| ((xp==this.getXP()+1)&&((yp==this.getYP()+1)||(yp==this.getYP()-1)))||((xp==this.getXP()-1)&&((yp==this.getYP()+1)||(yp==this.getYP()-1))))) {
			x=this.getXP()*64;
            y=this.getYP()*64;
            return;
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
                         return;
        			}
        		}
        	}
        }
        
    }


	/**
	 * Visszaadja, hogy az adott király üti-e az ellentétes színű királyt.
	 * A "move" függvényhez hasonlóan vizsgálja a különböző lehetséges eseteket.
	 * 
	 * @param oppkingxp //ellentétes színű király xp értéke
	 * @param oppkingyp //ellentétes színű király yp értéke
	 */
	@Override
	public boolean canHitOppositeKing(int oppkingxp, int oppkingyp) {
		if(((oppkingxp==this.getXP()+1)&& (Math.abs(oppkingyp-this.getYP())<2))
				||((oppkingxp==this.getXP()-1)&& (Math.abs(oppkingyp-this.getYP())<2))
				||((oppkingyp==this.getYP()+1)&& (Math.abs(oppkingxp-this.getXP())<2))
				||((oppkingyp==this.getYP()-1)&& (Math.abs(oppkingxp-this.getXP())<2))) {
            return true;
		}
		if(!(oppkingxp==this.getXP()+1|| oppkingxp== this.getXP()-1|| oppkingyp==this.getYP()+1 || oppkingyp==this.getYP()-1|| ((oppkingxp==this.getXP()+1)&&((oppkingyp==this.getYP()+1)||(oppkingyp==this.getYP()-1)))||((oppkingxp==this.getXP()-1)&&((oppkingyp==this.getYP()+1)||(oppkingyp==this.getYP()-1))))) {
			return false;
		}
		
		return false;
	}

	/**
	 * Visszaadja, hogy az adott király az adott pozícióba léphet-e
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
		
		if(!((xp==this.getXP()+1&&yp==this.getYP())|| (xp== this.getXP()-1&&yp==this.getYP())|| (yp==this.getYP()+1&&xp==this.getXP()) || (yp==this.getYP()-1&&xp==this.getXP())|| ((xp==this.getXP()+1)&&((yp==this.getYP()+1)||(yp==this.getYP()-1)))||((xp==this.getXP()-1)&&((yp==this.getYP()+1)||(yp==this.getYP()-1))))) {
			return false;
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
        				if(((ps.get(j).getXP()==xd.getXP()+1&&ps.get(j).getYP()==xd.getYP())|| (ps.get(j).getXP()== xd.getXP()-1&&ps.get(j).getYP()==xd.getYP())|| (ps.get(j).getYP()==xd.getYP()+1&&ps.get(j).getXP()==xd.getXP()) || (ps.get(j).getYP()==xd.getYP()-1&&ps.get(j).getXP()==xd.getXP())|| ((ps.get(j).getXP()==xd.getXP()+1)&&((ps.get(j).getYP()==xd.getYP()+1)||(ps.get(j).getYP()==xd.getYP()-1)))||((ps.get(j).getXP()==xd.getXP()-1)&&((ps.get(j).getYP()==xd.getYP()+1)||(ps.get(j).getYP()==xd.getYP()-1))))) {
        					for(int z = 0; z<ps.size();z++) {
        						if(ps.get(z).getColour()!=this.getColour()&&ps.get(z).canHitOppositeKing(ps.get(j).getXP(), ps.get(j).getYP())==false)
        						return true;
        						else return false;
        					}
        				}
        				return false;
        			}
        		}
        	}
        }
		
		return true;
	}

}
