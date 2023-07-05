package Game;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * A bábukat képviselő absztrakt ősosztály. Leszármazottai az egyes bábuk.
 *
 */
public abstract class Piece implements Serializable{
	private static final long serialVersionUID = 7373171783972184093L;
    private int xp; //0-8 koordináta
    private int yp; //0-8 koordináta
    
    int x; //x koordináta a framen
    int y; //real coordinate on the frame
    private boolean colour; //szín, true=fekete, false=fehér
    public List<Piece> ps; //bábuk tárolására használt lista
    private String name; //bábu neve
    private boolean actRound; //melyik színű játékos köre van éppen
    private String player1; //"R"->Robot, "P"->Player
    private String player2; //"R"->Robot, "P"->Player
    private boolean player1colour; //1.játékos színe
    private boolean player2colour; //2.játékos színe
    
    /**
	 * A Piece osztály konstruktora.
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
    public Piece(int xp, int yp, boolean colour,String n, List<Piece> ps, boolean b, String p1, String p2, boolean p1c, boolean p2c) {
        this.xp = xp;
        this.yp = yp;
        x=xp*64;
        y=yp*64;
        this.colour = colour;
        this.ps=ps;
        name=n;
        actRound=b;
        player1=p1;
        player2=p2;
        p1c=player1colour;
        p2c=player2colour;
        ps.add(this);
    }
    
    /**
	 * A bábuk mozgatását végző absztrakt függvény, az új helyet is módosítja, valamint a listát is megfelelően frissíti.
	 * Ha nem lehetséges az adott pozícióba való mozgás, akkor a bábu nem lép oda.
	 * 
	 * @param xp
	 * @param yp
	 */
    public abstract void move(int xp,int yp);
 
    /**
	 * Aszbtrakt függvény, mely visszaadja, hogy az adott bábu az adott pozícióba léphet-e
	 * A "move" függvényhez hasonlóan vizsgálja a különböző lehetséges eseteket.
	 * Ez a függvény a bot lépéseihez van felhasználva.
	 * 
	 * @param xp
	 * @param yp
	 */
    public abstract boolean canMoveTo(int xp, int yp);
    
    /**
     * A bábut eltávolítja a listából.
     */
    public void kill(){
        ps.remove(this);
    }
    
    /**
	 * Absztrakt függvény, mely visszaadja, hogy az adott bábu üti-e az ellentétes színű királyt.
	 * A "move" függvényhez hasonlóan vizsgálja a különböző lehetséges eseteket.
	 * 
	 * @param oppkingxp //ellentétes színű király xp értéke
	 * @param oppkingyp //ellentétes színű király yp értéke
	 */
    public abstract boolean canHitOppositeKing(int oppkingxp, int oppkingyp);
    
    /**
     * //1. játékos getter-e
     * @return
     */
    public String getPlayer1() {
    	return this.player1;
    }
    
    /**
     * //2. játékos getter-e
     * @return
     */
    public String getPlayer2() {
    	return this.player2;
    }
    
    /**
     * //1. játékos színének getter-e
     * @return
     */
    public boolean getPlayer1Colour() {
    	return this.player1colour;
    }
    
    /**
     * //2. játékos színének getter-e
     * @return
     */
    public boolean getPlayer2Colour() {
    	return this.player2colour;
    }
    
    /**
     * 1. játékos setter-e
     * @param s
     */
    public void setPlayer1(String s) {
        this.player1 = s;
    }
    
    /**
     * 2. játékos setter-e
     * @param s
     */
    public void setPlayer2(String s) {
        this.player2 = s;
    }
    
    /**
     * 1. játékos színének setter-e
     * @param s
     */
    public void setPlayer1Colour(boolean b) {
        this.player1colour = b;
    }
    
    /**
     * 2. játékos színének setter-e
     * @param s
     */
    public void setPlayer2Colour(boolean b) {
        this.player2colour = b;
    }
    
    /**
     * //aktuális kör getter-e
     * @return
     */
    public boolean getActRound() {
    	return this.actRound;
    }
    
    /**
     * aktuális kör setter-e
     * @param s
     */
    public void setActRound(boolean b) {
    	this.actRound=b;
    }
    
    /**
     * //XP koordináta getter-e
     * @return
     */
    public int getXP() {
		return this.xp;
	}
    
    /**
     * //YP koordináta getter-e
     * @return
     */
    public int getYP() {
		return this.yp;
	}
    
    /**
     * //szín getter-e
     * @return
     */
    public boolean getColour() {
		return this.colour;
	}
    
    /**
     * //név getter-e
     * @return
     */
    public String getName() {
    	return this.name;
    }
    
    /**
     * XP koordináta setter-e
     * @param s
     */
    public void setXP(int xp) {
        this.xp = xp;
    }
    
    /**
     * YP koordináta setter-e
     * @param s
     */
    public void setYP(int yp) {
        this.yp = yp;
    }
    
    /**
     * szín setter-e
     * @param s
     */
    public void setColour(boolean colour) {
        this.colour = colour;
    }
    
    /**
     * név setter-e
     * @param s
     */
    public void setName(String name) {
    	this.name=name;
    }
	
}
