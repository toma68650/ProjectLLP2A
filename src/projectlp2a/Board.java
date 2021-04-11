package projectlp2a;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.awt.MouseInfo;

@SuppressWarnings("serial")
public class Board extends JPanel{
	
	private Graphics2D g2d;

	private Case focusedCase;
	private Image boardImage;
	private Timer timer;
	//private final int DELAY = 10;
	
	ArrayList<Player> players = new ArrayList<Player>();
	List<Case> cases = new LinkedList<Case>();
	
	public Board(JFrame f1, JLayeredPane jl) {
		super();
		loadImage();
		initBoard();
		focusedCase= null;
		
		/********************************************************************************************************
		 ***************** GENERATION OF THE ARRAY OF CASES *****************************************************
		 ********************************************************************************************************/
		
		
		/* Green part of the board */
		Case startGreen = new Case(6, 14, true, Colorp.green, null, jl, this, 1);
		cases.add(startGreen);
		for(int i=2; i <=7;i++) {
			cases.add(new Case(6, 15-i, true, null, null, jl, this, i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(6-i, 8, true, null, null, jl, this, 7+i));
		}
		Case endBlue = new Case(0, 7, true, null, Colorp.blue, jl, this, 13);
		cases.add(endBlue);
		
		
		
		/* Blue part of the board*/
		Case startBlue = new Case(0, 8, true, Colorp.blue, null, jl, this, 14);
		cases.add(startBlue);
		for(int i=1; i <=6;i++) {
			cases.add(new Case(i, 6, true, null, null, jl, this, 14+i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(6, 6-i, true, null, null, jl, this, 20+i));
		}
		Case endRed = new Case(7, 0, true, null, Colorp.red, jl, this, 27);
		cases.add(endRed);
		
		
		
		/* Red part of the board */
		Case startRed = new Case(8, 0, true, Colorp.red, null, jl, this, 28);
		cases.add(startRed);
		for(int i=1; i <=6;i++) {
			cases.add(new Case(8, i, true, null, null, jl, this, 28+i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(8+i, 6, true, null, null, jl, this, 34+i));
		}
		Case endYellow = new Case(14, 7, true, null, Colorp.yellow, jl, this, 41);
		cases.add(endYellow);
		
		
		
		/* Yellow part of the board */
		Case startYellow = new Case(14, 8, true, Colorp.yellow, null, jl, this, 42);
		cases.add(startYellow);
		for(int i=1; i <=6;i++) {
			cases.add(new Case(14-i, 8, true, null, null, jl, this, 42+i));
		}
		for(int i=1;i<=6;i++) {
			cases.add(new Case(8, 8+i, true, null, null, jl, this, 48+i));
		}
		Case endGreen = new Case(7, 14, true, null, Colorp.green, jl, this, 55);
		cases.add(endGreen);
		
		cases.add(startGreen);
		
		/********************************************************************************************************
		 ***************** GENERATION OF THE ARRAY OF PLAYERS ***************************************************
		 ********************************************************************************************************/
		
		Player greenP = new Player(endGreen, Colorp.green, jl, this);
		greenP.setStartingCase(startGreen);
		Player blueP = new Player(endBlue, Colorp.blue, jl, this);
		blueP.setStartingCase(startBlue);
		Player redP = new Player(endRed, Colorp.red, jl, this);
		redP.setStartingCase(startRed);
		Player yellowP = new Player(endYellow, Colorp.yellow, jl, this);
		yellowP.setStartingCase(startYellow);
		players.add(greenP);
		players.add(blueP);
		players.add(redP);
		players.add(yellowP);		
		
		
		/********************************************************************************************************
		 ***************** TIMER FOR DETECTING ACTIONS **********************************************************
		 ********************************************************************************************************/
		
		timer = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(focusedCase != null) {
                	System.out.println("Position of this case : x="+focusedCase.getX()+"; y="+focusedCase.getY());
                	int dieResult = 6;
                	Player focusedPlayer = redP;
                	
                	for(Pawn p : focusedPlayer.getPawns()) {
                		if((p.getRelativeX() == focusedCase.getX()) && (p.getRelativeY() == focusedCase.getY())) {
                			if(((focusedCase == focusedPlayer.getBarn().get(0)) && (dieResult == 6))||((focusedCase == focusedPlayer.getBarn().get(1)) && (dieResult == 6)) ||((focusedCase == focusedPlayer.getBarn().get(2)) && (dieResult == 6)) ||((focusedCase == focusedPlayer.getBarn().get(3)) && (dieResult == 6))) {
                				p.move(focusedPlayer.getStartingCase().getX(), focusedPlayer.getStartingCase().getY());
                			} else if(cases.contains(focusedCase)) {
                				Case target = cases.get(cases.indexOf(focusedCase)+dieResult);
                				p.move(target.getX(),target.getY());
                			}
                		} else {
                			System.out.println("You haven't any horse on this case !!!");
                		}
                	}
                	focusedCase= null;
                }
            }
        });
		timer.start();
		
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
	
	public void setFocusedCase(Case c) {
		focusedCase = c;
	}
	
	public void startGame() {
		
	}
	
	public void isFinish() {
		
	}
	
	
}
