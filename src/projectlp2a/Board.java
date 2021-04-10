package projectlp2a;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.awt.MouseInfo;

@SuppressWarnings("serial")
public class Board extends JPanel{
	
	private Graphics2D g2d;

	private Image boardImage;
	//private Timer timer;
	//private final int DELAY = 10;
	
	ArrayList<Player> players = new ArrayList<Player>();
	List<Case> cases = new ArrayList<Case>();
	
	public Board(JFrame f1, JLayeredPane jl) {
		super();
		loadImage();
		initBoard();
		
		/* Green part of the board */
		cases.add(new Case(6, 14, true, Colorp.green, null, jl, 1));
		for(int i=2; i <=7;i++) {
			cases.add(new Case(6, 15-i, true, null, null, jl, i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(6-i, 8, true, null, null, jl, 7+i));
		}
		cases.add(new Case(0, 7, true, null, Colorp.blue, jl, 13));
		
		
		
		/* Blue part of the board*/
		cases.add(new Case(0, 8, true, Colorp.blue, null, jl, 14));
		for(int i=1; i <=6;i++) {
			cases.add(new Case(i, 6, true, null, null, jl, 14+i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(6, 6-i, true, null, null, jl, 20+i));
		}
		cases.add(new Case(7, 0, true, null, Colorp.red, jl, 27));
		
		
		
		/* Red part of the board */
		cases.add(new Case(8, 0, true, Colorp.red, null, jl, 28));
		for(int i=1; i <=6;i++) {
			cases.add(new Case(8, i, true, null, null, jl, 28+i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(8+i, 6, true, null, null, jl, 34+i));
		}
		cases.add(new Case(14, 7, true, null, Colorp.yellow, jl, 41));
		
		
		
		/* Yellow part of the board */
		cases.add(new Case(14, 8, true, Colorp.yellow, null, jl, 42));
		for(int i=1; i <=6;i++) {
			cases.add(new Case(14-i, 8, true, null, null, jl, 42+i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(8, 8+i, true, null, null, jl, 48+i));
		}
		cases.add(new Case(7, 14, true, null, Colorp.green, jl, 55));
		
		//f1.add(this,BorderLayout.CENTER);
		//Pawn p = new Pawn(f1);
	}
	
	private void initBoard() {
		
        setBackground(Color.black);
        setFocusable(true);

        setBounds(0,0,735, 735);
        //timer = new Timer(DELAY, this);
        //timer.start();
    }

	public Image getImage() {
        
        return boardImage;
    }
	
	private void loadImage() {
		ImageIcon boardIcon = new ImageIcon("Image/plateauprojet.png");
		boardImage = boardIcon.getImage();
		
		boardImage.getWidth(null);
		boardImage.getHeight(null);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
	
	protected Graphics2D getG2D() {
		return g2d;
	}
	
	protected void doDrawing(Graphics g) {
        
        g2d = (Graphics2D) g;

        g2d.drawImage(this.getImage(),0,0, this);
    }
	
	public void startGame() {
		
	}
	
	public void isFinish() {
		
	}
	
	
}
