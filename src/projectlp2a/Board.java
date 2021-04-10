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
	ArrayList<Case> cases = new ArrayList<Case>();
	
	public Board(JFrame f1) {
		super();
		loadImage();
		initBoard();
		
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
