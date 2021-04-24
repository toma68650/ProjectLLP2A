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
/**
 * @class Board.java
 * @brief The class where all things linked with the movement on the board and outside happend.
 * @details It extends a JPanel.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */ 
@SuppressWarnings("serial")
public class Board extends JPanel{
	
	private Graphics2D g2d; //!< The graphic 2d where the board is painted

	private Case focusedCase; //!< The case focused when someone push it
	private Image boardImage; //!< The image of the board
	private Timer timer; //!< The timer used to detect at each moment if a case has been focused
	protected boolean action = false; //!< true if an action was started, false else.
	
	private List<PawnMoveListener> listeners = new ArrayList<PawnMoveListener>(); //!< list of the listeners listening to this board. They receive a notification when a Pawn's move is performed.
	private Player focusedPlayer=null; //!< The player focused when it's his turn.
	private int dieResult=0; //!< The result of the die when someon launched it.
	
	protected Player greenP; //!< The green player.
	protected Player blueP; //!< The blue player.
	protected Player yellowP; //!< The yellow player.
	protected Player redP;//!< the red player.
	ArrayList<Player> players = new ArrayList<Player>(); //!< The list of all the players. It is useful to know who need to play.
	List<Case> cases = new LinkedList<Case>(); //!< The list of cases composing the board
	
	/**
	 * @brief default constructor for a board
	 * @param f1 - JFrame, frame on which the game will be played on
	 * @param jl - JLayeredPane, pane that will appear when to inform the user about events
	 */
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
			cases.add(new Case(6, 15-i, false, null, null, jl, this, i));
		}
		for(int i=1;i<=6;i++) {
			if(i == 4) {
				cases.add(new Case(6-i, 8, true, null, null, jl, this, 7+i));
			} else {
				cases.add(new Case(6-i, 8, false, null, null, jl, this, 7+i));
			}
		}
		Case endBlue = new Case(0, 7, false, null, Colorp.blue, jl, this, 13);
		cases.add(endBlue);
		
		
		
		/* Blue part of the board*/
		Case startBlue = new Case(0, 6, true, Colorp.blue, null, jl, this, 14);
		cases.add(startBlue);
		for(int i=1; i <=6;i++) {
			cases.add(new Case(i, 6, false, null, null, jl, this, 14+i));
		}
		for(int i=1;i<=6;i++) {
			if(i == 4) {
				cases.add(new Case(6, 6-i, true, null, null, jl, this, 20+i));
			} else {
				cases.add(new Case(6, 6-i, false, null, null, jl, this, 20+i));
			}
		}
		Case endRed = new Case(7, 0, false, null, Colorp.red, jl, this, 27);
		cases.add(endRed);
		
		
		
		/* Red part of the board */
		Case startRed = new Case(8, 0, true, Colorp.red, null, jl, this, 28);
		cases.add(startRed);
		for(int i=1; i <=6;i++) {
			cases.add(new Case(8, i, false, null, null, jl, this, 28+i));
		}
		for(int i=1;i<=6;i++) {
			if(i == 4) {
				cases.add(new Case(8+i, 6, true, null, null, jl, this, 34+i));
			} else {
				cases.add(new Case(8+i, 6, false, null, null, jl, this, 34+i));
			}
		}
		Case endYellow = new Case(14, 7, false, null, Colorp.yellow, jl, this, 41);
		cases.add(endYellow);
		
		
		
		/* Yellow part of the board */
		Case startYellow = new Case(14, 8, true, Colorp.yellow, null, jl, this, 42);
		cases.add(startYellow);
		for(int i=1; i <=6;i++) {
			cases.add(new Case(14-i, 8, false, null, null, jl, this, 42+i));
		}
		for(int i=1;i<=6;i++) {
			if(i == 4) {
				cases.add(new Case(8, 8+i, true, null, null, jl, this, 48+i));
			} else {
				cases.add(new Case(8, 8+i, false, null, null, jl, this, 48+i));
			}
		}
		Case endGreen = new Case(7, 14, false, null, Colorp.green, jl, this, 55);
		cases.add(endGreen);
		
		
		/********************************************************************************************************
		 ***************** GENERATION OF THE ARRAY OF PLAYERS ***************************************************
		 ********************************************************************************************************/
		
		greenP = new Player(endGreen, Colorp.green, jl, this);
		greenP.setStartingCase(startGreen);
		blueP = new Player(endBlue, Colorp.blue, jl, this);
		blueP.setStartingCase(startBlue);
		redP = new Player(endRed, Colorp.red, jl, this);
		redP.setStartingCase(startRed);
		yellowP = new Player(endYellow, Colorp.yellow, jl, this);
		yellowP.setStartingCase(startYellow);		
		
		
		/********************************************************************************************************
		 ***************** TIMER FOR DETECTING ACTIONS **********************************************************
		 ********************************************************************************************************/
		
		timer = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(action) {
                	process();
                }      	
            }
        });
		timer.start();
		
	}
	
	/**
	 * @method public void process()
	 * @brief The method which will manage the movement of the pawns depending on the player who is playing
	 */
	public void process() {
		/* We first check if a case is focused (clicked on), if it is not the case, we do nothing, else we manage the operation */
		if((focusedCase != null) && (focusedPlayer != null) && (players.size() != 0)) {
			System.out.println("I entered this kindly function");
        	/* We check if one of player's pawn is on the case clicked, if not, we do nothing */
        	boolean pawnMoved=false;
			for(Pawn p : focusedPlayer.getPawns()) {
        		if(!pawnMoved) {
        			if((p.getRelativeX() == focusedCase.getX()) && (p.getRelativeY() == focusedCase.getY())) {
            			/* We detected a horse on the case, we now check the position of this case. It is a case of the barn ? */
        				Case target = null; // The case which will be targeted
        				if( ( (focusedCase == focusedPlayer.getBarn().get(0)) || (focusedCase == focusedPlayer.getBarn().get(1)) || (focusedCase == focusedPlayer.getBarn().get(2)) || (focusedCase == focusedPlayer.getBarn().get(3))) && (dieResult == 6) ) {
            				target = focusedPlayer.getStartingCase();
            			/* Or is it a case from the board ? */
            			} else if(cases.contains(focusedCase)) {
            				int  indexNextCase = (cases.indexOf(focusedCase)+dieResult); // The index of the next case if everything happen correctly
            				int nbCaseBeforeEnd=-10; // The case before the beginning of the "stair" or "ladder" 
            				
            				/* We check if a case is the end before the horse arrive. The horse must stand at the beginning of the ladder to climb to it, else he will go back */
            				for(int i = cases.indexOf(focusedCase);i<indexNextCase;i++) {
            					if(cases.get(i%56) == focusedPlayer.getEnd().get(0)) {
            						nbCaseBeforeEnd = i-cases.indexOf(focusedCase);
            						System.out.println("Value of nbCaseBeforeEnd : "+nbCaseBeforeEnd);
            					}
            				}
            				
            				/* The pawn arrive right on the bottom of the ladder, he can climb at the next turn */
            				if(dieResult - nbCaseBeforeEnd == 0) {
            					target = cases.get(indexNextCase%56);
            				/* The result of the die is too big, we must go back */
            				} else if ( nbCaseBeforeEnd > 0) {
            					//indexNextCase = cases.indexOf(focusedCase) +nbCaseBeforeEnd;
            					dieResult -= nbCaseBeforeEnd;
            					//indexNextCase -= dieResult;
            					//indexNextCase = indexNextCase%56; // Normally this action is useless, but if the die has more than 55 sides, it could be a problem :] */
            					target = focusedPlayer.getEnd().get(dieResult);
            				/* We're at the bottom of the ladder, we can now climb to it */
            				} else if(nbCaseBeforeEnd == 0) {
            					if (dieResult != 6) {
            						target = focusedPlayer.getEnd().get(dieResult);
            					}
            				/* We are not close from the end,  we just make a classic move */
            				} else {
            					target = cases.get(indexNextCase%56);							
							}
            				
            			} else if((focusedPlayer.getEnd().indexOf(focusedCase) > 0) && (6 - focusedPlayer.getEnd().indexOf(focusedCase) >= dieResult)) {
            				target = focusedPlayer.getEnd().get(focusedPlayer.getEnd().indexOf(focusedCase) + dieResult);
            			}
        				if (target != null) {
        					p.move(target.getX(),target.getY());
        					List<Pawn> pawnsInDanger = new ArrayList<Pawn>();
        					for(int i=0;i<4;i++) {
        						pawnsInDanger=null;
        						pawnsInDanger = new ArrayList<Pawn>();
        						if(!(players.get(i) == focusedPlayer)) {
	        						for(int j =0;j<4;j++) {
	    								
										//System.out.println("Position of pawn : "+players.get(i).getPawns().get(j).getX()+" "+players.get(i).getPawns().get(j).getY());
	    								//System.out.println("Postion of case : "+target.getX()+" "+target.getY());
	    								//System.out.println("Same color ? "+!(players.get(i).getPawns().get(j).getColor().equals(p.getColor())));
	    								if( (players.get(i).getPawns().get(j).getRelativeX()==target.getX()) && (players.get(i).getPawns().get(j).getRelativeY()==target.getY()) && !(players.get(i).getPawns().get(j).getColor().equals(p.getColor()))) {
	    									if(!target.isSafe()) {
	    										pawnsInDanger.add(players.get(i).getPawns().get(j));
	    									}
	    								}
	    							}
	        						if(pawnsInDanger.size()==1) {
	        							pawnsInDanger.get(0).comeBackHome();
	        						}
        						}
    						}
        					pawnMoved=true;
        					action=false;
        				}
        				
            			
            		}
        		}
        	}
        	for(PawnMoveListener l : listeners) {
        		l.pawnActionPerformed();
        	}
        	focusedCase= null; // We set the focusedCase to null so we stop our interest on it
        }
	}
	
	/**
	 * @brief add a PawnMoveListener in a list of listeners
	 * @param l - 
	 */
	public void addPawnMoveListener(PawnMoveListener l) {
		listeners.add(l);
	}
	
	/**
	 * @brief verify is the movement proposed by the player is legal or not
	 * @param p - Player, the player actually playing
	 * @return boolean, true if the movement is legal
	 */
	public boolean isLegalMove(Player p) {
		int nbLegalMoves = 4;
		boolean inBarn = false;
		for(Pawn pa : p.getPawns()) {
			if(dieResult !=6) {
				for(Case c : p.getBarn()) {
					if((pa.getRelativeX()==c.getX()) && (pa.getRelativeY()==c.getY()) ) {
						nbLegalMoves--;
						inBarn=true;
					}
				}
			} 
			if(!inBarn) {
				for(Case c : p.getEnd()) {
					if((pa.getRelativeX()==c.getX()) && (pa.getRelativeY()==c.getY())){
						if(6 - p.getEnd().indexOf(c) < dieResult) {
							nbLegalMoves--;
						}
					}
				}
			}
			inBarn=false;
		}
		System.out.println("Number of legal moves : "+nbLegalMoves);
		if(nbLegalMoves > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @brief initialze the board
	 */
	private void initBoard() {
		
        setBackground(Color.black);
        setFocusable(true);

        setBounds(0,0,735, 735);
        //timer = new Timer(DELAY, this);
        //timer.start();
    }
	
	/**
	 * @brief getter for Image
	 * @return Image, boardImage
	 */
	public Image getImage() {
        
        return boardImage;
    }
	
	/**
	 * @brief load the image of the board on the JFrame
	 */
	private void loadImage() {
		ImageIcon boardIcon = new ImageIcon("Image/plateauprojet_skin.png");
		boardImage = boardIcon.getImage();
		
		boardImage.getWidth(null);
		boardImage.getHeight(null);
	}
	
	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
	
	/**
	 * 
	 * @param g
	 */
	protected void doDrawing(Graphics g) {
        
        g2d = (Graphics2D) g;

        g2d.drawImage(this.getImage(),0,0, this);
    }
	
	/**
	 * @brief set the case the program will focus on
	 * @param c - Case, the case we want the program to focus on
	 */
	public void setFocusedCase(Case c) {
		focusedCase = c;
	}
	
	/**
	 * @brief getter for players
	 * @return Player, players
	 */
	public List<Player> getPlayers(){
		return players;
	}
	
	/**
	 * @brief find a player according to his color
	 * @param color - Colorp, color of the player we want to find
	 * @return Player, playerFound
	 */
	public Player findPlayer(Colorp color) {
		Player playerFound =null;
		for(Player p : players) {
			if(p.getColor().equals(color)) {
				playerFound = p;
			}
		}
		return playerFound;
	}
	
	/**
	 * @brief verify if the player has finished
	 * @param p - Player, the player we want to check if he finished the game
	 * @return boolean , 
	 */
    public boolean isFinish(Player p) {
        int PawnsEnded = 0;
        for(Pawn pa : p.getPawns()) {
            if((pa.getRelativeX()==p.getEnd().get(6).getX()) && (pa.getRelativeY()==p.getEnd().get(6).getY())) {
                PawnsEnded++;
            }
        }
        return (PawnsEnded == 4);
    }
	
    /**
     * @brief 
     */
    private void enableAction() {
    	action=true;
    }
	
    
    /**
     * 
     * @param p
     * @param dieResult
     */
    public void setAction(Player p, int dieResult) {
    	focusedPlayer = p;
    	this.dieResult = dieResult;
    	enableAction();
    	process();
    }
    
    /**
     * 
     * @param p
     * @param dieResult
     */
    public void setActionAi(Player p, int dieResult) {
    	focusedPlayer = p;
    	this.dieResult = dieResult;
    	process();
    }
    
    /**
     * @brief make all the pawns go to the barn
     */
    public void restartBoard() {
    	if(players.size() !=0) {
    		for(Player p : players) {
        		for(Pawn pa : p.getPawns()) {
        			pa.comeBackHome();
        		}
        	}
    	}
    	players = null;
    	players = new ArrayList<Player>();
    	action=false;
    }
}
