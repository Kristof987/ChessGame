package Game;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ChessGame {
    public static LinkedList<Piece> ps=new LinkedList<>(); // a bábuk tárolására szolgáló láncolt lista
    public static Piece selectedPiece=null; //a bábu, amit a képernyőn való mozgatáshoz használok
    
    /**
     * Az aktuális játékállás mentése fáljba, szerializálva
     * A keletkezett fájl neve: aktuális dátum + "_ChessGame"
     * A játék folyamán bármikor lehetőség van mentésre.
     * 
     * @throws IOException
     */
    public static void SaveGame() throws IOException {
    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
    	FileOutputStream fos = new FileOutputStream(timeStamp+"_ChessGame");
    	ObjectOutputStream oos = new ObjectOutputStream(fos);
    	oos.writeObject(ps);
    	oos.close();
    	fos.close();
    }
    
    /**
     * A fájl beolvasása és megjelenítése, szerializálva.
     * A fájl nevére történő rákeresés után a képernyő azonnal frissül.
     *  A játék folyamán bármikor lehetőség van egy korábbi játék betöltésére, ekkor (ha előtte nem mentjük) az aktuális játék elveszik.
     * 
     * @param input
     * @throws IOException
     * @throws ClassNotFoundException
     */
	@SuppressWarnings("unchecked")
	public static void LoadGame(String input) throws IOException, ClassNotFoundException {
		if(input==null) {throw new FileNotFoundException("File Error");}
    	FileInputStream fis = new FileInputStream(input);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ps=(LinkedList<Piece>) ois.readObject();
        ois.close();
        fis.close();
    }
	
	/**
	 * A játék vizuális beállításai, illetve a mozgatás a konstruktor által hívhatóak meg.
	 * 
	 */
	public ChessGame(){
		BufferedImage all;
		try {
			all = ImageIO.read(new File("pieces//chess.png")); //képeket tartalmazó fájl beolvasása
			Image imgs[]=new Image[12];
		       int ind=0;
		        for(int y=0;y<400;y+=200){
		        for(int x=0;x<1200;x+=200){
		            imgs[ind]=all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH); //adott bábuk képeinek kinyerése
		       ind++;
		        }    
		        }
		        Piece brook=new Rook(0, 0, false, "rook", ps, true,"p","p",false,true); //bábuk létrehozása
		        Piece bkinght=new Knight(1, 0, false, "knight", ps, true,"p","p",false,true);
		        Piece bbishop=new Bishop(2, 0, false, "bishop", ps, true,"p","p",false,true);
		        Piece bqueen=new Queen(3, 0, false, "queen", ps, true,"p","p",false,true);
		        Piece bking=new King(4, 0, false, "king", ps, true,"p","p",false,true);
		        Piece bbishop2=new Bishop(5, 0, false, "bishop", ps, true,"p","p",false,true);
		        Piece bkight2=new Knight(6, 0, false, "knight", ps, true,"p","p",false,true);
		        Piece brook2=new Rook(7, 0, false, "rook", ps, true,"p","p",false,true);
		        Piece bpawn1=new Pawn(1, 1, false, "pawn", ps, true,"p","p",false,true);
		        Piece bpawn2=new Pawn(2, 1, false, "pawn", ps, true,"p","p",false,true);
		        Piece bpawn3=new Pawn(3, 1, false, "pawn", ps, true,"p","p",false,true);
		        Piece bpawn4=new Pawn(4, 1, false, "pawn", ps, true,"p","p",false,true);
		        Piece bpawn5=new Pawn(5, 1, false, "pawn", ps, true,"p","p",false,true);
		        Piece bpawn6=new Pawn(6, 1, false, "pawn", ps, true,"p","p",false,true);
		        Piece bpawn7=new Pawn(7, 1, false, "pawn", ps, true,"p","p",false,true);
		        Piece bpawn8=new Pawn(0, 1, false, "pawn", ps, true,"p","p",false,true);
		        
		        Piece wrook=new Rook(0, 7, true, "rook", ps, true,"p","p",false,true);
		        Piece wkinght=new Knight(1, 7, true, "knight", ps, true,"p","p",false,true);
		        Piece wbishop=new Bishop(2, 7, true, "bishop", ps, true,"p","p",false,true);
		        Piece wqueen=new Queen(3, 7, true, "queen", ps, true,"p","p",false,true);
		        Piece wking=new King(4, 7, true, "king", ps, true,"p","p",false,true);
		        Piece wbishop2=new Bishop(5, 7, true, "bishop", ps, true,"p","p",false,true);
		        Piece wkight2=new Knight(6, 7, true, "knight", ps, true,"p","p",false,true);
		        Piece wrook2=new Rook(7, 7, true, "rook", ps, true,"p","p",false,true);
		        Piece wpawn1=new Pawn(1, 6, true, "pawn", ps, true,"p","p",false,true);
		        Piece wpawn2=new Pawn(2, 6, true, "pawn", ps, true,"p","p",false,true);
		        Piece wpawn3=new Pawn(3, 6, true, "pawn", ps, true,"p","p",false,true);
		        Piece wpawn4=new Pawn(4, 6, true, "pawn", ps, true,"p","p",false,true);
		        Piece wpawn5=new Pawn(5, 6, true, "pawn", ps, true,"p","p",false,true);
		        Piece wpawn6=new Pawn(6, 6, true, "pawn", ps, true,"p","p",false,true);
		        Piece wpawn7=new Pawn(7, 6, true, "pawn", ps, true,"p","p",false,true);
		        Piece wpawn8=new Pawn(0, 6, true, "pawn", ps, true,"p","p",false,true);
		      
		        JFrame frame = new JFrame();
		        frame.setBounds(10, 10, 512, 512);
		        
		        JPanel draww=new JPanel(){ //tábla rajzolása
					private static final long serialVersionUID = 1L;

					@Override
		            public void paint(Graphics g) {
		            boolean white=true;
		                for(int y= 0;y<8;y++){
		            for(int x= 0;x<8;x++){ //háttérszín beállítása
		                if(white){
		                    g.setColor(new Color(255,255, 255));
		                }else{
		                    g.setColor(new Color(90,90,90));
		                    
		                }
		                g.fillRect(x*64, y*64, 128, 128);
		           white=!white;
		            }
		            white=!white;
		            }
		                for(Piece p: ps){ //képek illesztése
		                    int ind=0;
		                    if(p.getName().equalsIgnoreCase("king")){
		                        ind=0;
		                    }
		                    if(p.getName().equalsIgnoreCase("queen")){
		                        ind=1;
		                    }
		                    if(p.getName().equalsIgnoreCase("bishop")){
		                        ind=2;
		                    }
		                    if(p.getName().equalsIgnoreCase("knight")){
		                        ind=3;
		                    }
		                    if(p.getName().equalsIgnoreCase("rook")){
		                        ind=4;
		                    }
		                    if(p.getName().equalsIgnoreCase("pawn")){
		                        ind=5;
		                    }
		                    if(!p.getColour()){
		                        ind+=6;
		                    }
		                    g.drawImage(imgs[ind], p.x, p.y, this);
		                    
		                }
		                
		            }
		            
		            
		        };
		        JPanel pn = new JPanel(new BorderLayout(3,3));
		        JToolBar tools = new JToolBar();
		        tools.setFloatable(false);
		        pn.add(tools, BorderLayout.PAGE_START);
		        pn.add(draww,BorderLayout.CENTER);
		        JButton pp = new JButton("Start Game"); //Gombok létrehozása (Player vs Player)
		        tools.add(pp);
		        //JButton pr = new JButton("P. vs R."); //Player (fehér) vs Robot (fekete)
		        //tools.add(pr);
		        //JButton rr = new JButton("R. vs R."); //Robot vs Robot
		        //tools.add(rr);
		        JButton save = new JButton("Save Game"); //Játék mentése
		        tools.add(save);
		        JButton load = new JButton("Load Game"); //Játék mentése
		        tools.add(load);
		        JButton resign = new JButton("Resign"); //Játék feladása. Ha a játékos nem tud/akar többet lépni, akkor a játék feladása mellett kell, hogy döntsön.
		        tools.add(resign);
		        frame.add(pn);
		        final JLabel message = new JLabel("White's turn");
		        tools.add(message);
		        frame.setSize(525,588);
		        
		        resign.addActionListener(e->{
		        	System.exit(0);
		        });
		        
		        pp.addActionListener(e->{
		        	for(int i=0; i<ps.size();i++) {
		        		ps.get(i).setPlayer1("P");
		        		ps.get(i).setPlayer2("P");
		        	}
		        	
		        });
		        
		        /*pr.addActionListener(e->{
		        	for(int i=0; i<ps.size();i++) {
		        		ps.get(i).setPlayer1("P");
		        		ps.get(i).setPlayer2("R");
		        	}
		        });
		        
		        rr.addActionListener(e->{
		        	for(int i=0; i<ps.size();i++) {
		        		ps.get(i).setPlayer1("R");
		        		ps.get(i).setPlayer2("R");
		        	}
		        });*/
		        
		        save.addActionListener(e->{
					try {
						SaveGame();
					}catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
		        
		        load.addActionListener(e->{
		        	String input = JOptionPane.showInputDialog(
		                    null, "Which file would you like to load? (Please enter a valid file name!)");

					try {
						if(input!=null)
						LoadGame(input);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.repaint();
					frame.revalidate();
				});
		        
		        frame.addMouseMotionListener(new MouseMotionListener() {
		            @Override
		            public void mouseDragged(MouseEvent e) {
		            	
		                if(selectedPiece!=null){ //a bábu követi az egeret
		                    selectedPiece.x=e.getX()-30;
		                    selectedPiece.y=e.getY()-83;
		                    frame.repaint();  
		                }
		            }

		            @Override
		            public void mouseMoved(MouseEvent e) {
		            }
		        });
		        frame.addMouseListener(new MouseListener() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		            	
		            }

		            @Override
		            public void mousePressed(MouseEvent e) { //a "selectedPiece" értéke az a bábu lesz, amire rá lett kattintva (ha nincs ott bábu, akkor null)
		            	selectedPiece=getPiece((e.getX()-7), (e.getY()-65)); 
		            	if(selectedPiece!=null && selectedPiece.getActRound()==false && selectedPiece.getPlayer2()=="R") { //Robotok kezelése
		            		selectedPiece = null;
		            	}
		            		
		            	else if(selectedPiece!=null && selectedPiece.getActRound()==true && selectedPiece.getPlayer1()=="R") //Robotok kezelése	
		            		selectedPiece = null;
		            	}

		            @Override
		            public void mouseReleased(MouseEvent e) {
		            	if(selectedPiece!=null) { //ha bábu mozgatása történt
		            		boolean tmp=selectedPiece.getActRound();
		            		boolean b=true;
		                	Piece tmppiece = selectedPiece;
		                	int tmpx = tmppiece.getXP();
		                	int tmpy = tmppiece.getYP();
		            		selectedPiece.move((e.getX()-6)/64, (e.getY()-65)/64); //bábu elmozdul
		            		if(tmpx!=selectedPiece.getXP()||tmpy!=selectedPiece.getYP())
		            			b=false;
		            		if(b==false) {
		                    	for(int i = 0; i<ps.size();i++) {
		                    		ps.get(i).setActRound(!tmp); 
		                    	}
		                    	if(selectedPiece.getColour()==true)
		                        	message.setText("Black's turn!"); //kört jelző üzenet állítása
		                    	else message.setText("White's turn!");
		                    }
		            	}
		            	
		            	else if(selectedPiece==null){ //Robotok esetében történő mozgatás véletlenszámgenerálással
			            	int s = ps.size();
			            	boolean bb;
			            	bb=ps.get(1).getActRound();
			            	int index = ThreadLocalRandom.current().nextInt(0,s);
			            	int random1 = ThreadLocalRandom.current().nextInt(0,8);
			            	int random2 = ThreadLocalRandom.current().nextInt(0,8);
			            	Piece p=ps.get(index);
			            	while(p.canMoveTo(random1, random2)!=true) {
			            		s = ps.size();
			            		index = ThreadLocalRandom.current().nextInt(0,s);
			            		p=ps.get(index);
			            		random1 = ThreadLocalRandom.current().nextInt(0,8);
				            	random2 = ThreadLocalRandom.current().nextInt(0,8);	
			            	}
			            	p.move(random1,random2);
			            	bb=p.getActRound();
			            	for(int i = 0; i<ps.size(); i++) {
			            		boolean bbb=bb;
			            		if(bbb==true && ps.get(1).getPlayer1()=="R"){
			            			message.setText("Black's turn");
			            			ps.get(i).setActRound(!bbb);
			            			bb=true;
			            		}	
				            	else if(bbb==false && ps.get(1).getPlayer2()=="R"){
				            		message.setText("White's turn");
				            		ps.get(i).setActRound(!bbb);
				            		bb=false;
			            		}
			            		
		            		}
			            	}
		            	frame.revalidate();	
		            	frame.repaint();
		            }

		            @Override
		            public void mouseEntered(MouseEvent e) {
		            }

		            @Override
		            public void mouseExited(MouseEvent e) {
		            }
		        });
		        frame.setDefaultCloseOperation(3);
		        frame.setVisible(true);
		} catch (IOException e2) {
			e2.printStackTrace();
		}     
	}

    /**
     * Frame pozíció alapján visszaadja az adott helyen lévő bábut.
     * Ha nincs abban a pozícióban bábu, akkor null értékkel tér vissza.
     * 
     * @param x
     * @param y
     * @return
     */
    public static Piece getPiece(int x,int y){
        int xp=x/64;
        int yp=y/64;
        for(Piece p: ps){
            if(p.getXP()==xp&&p.getYP()==yp){
                return p;
            }
        }
        return null;
    }
    
    
}

