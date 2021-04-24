package projectlp2a;
import java.util.*;

import javax.swing.JLayeredPane;

/**
 * @class Player.java
 * @brief Player class, represents a player with his pawns.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */
public class Player {
	private Board b; //!< b is the board which the player belong to
	private Case startingCase; //!< startingCase is the Case where the pawns can start to move
	private List<Pawn> pawns; //!< pawns represents the list of pawns belonging to the player
	private List<Case> barn; //!< barn represents the cases of the player's barn
	private List<Case> end; //!< end represents the cases of the player's ladder/stair/end + the case on the bottom of the ladder
	private Colorp color; //!< color is just the color of the player
	
	/**
	 * @brief default constructor for a player
	 * @param end - Case, the end case of the player
	 * @param color - Color, color of the player
	 * @param jl - JLayeredPane used in the Main class.
	 * @param board - Board, the main board.
	 */
	public Player(Case end, Colorp color, JLayeredPane jl, Board board) {
		this.b = board;
		pawns = new ArrayList<Pawn>();
		barn = new ArrayList<Case>();
		this.color = color;
		int i = initBarn(jl);
		
		Iterator<Case> it = barn.iterator();
		for(int j=1; j<= 4; j++) {
			pawns.add(new Pawn(color, jl, i+j, it.next()));
		}
		
		this.end = new ArrayList<Case>();
		this.end.add(end);
		initEnd(jl);
	}
	
	
	/**
	 * @brief initialize a barn
	 * @param jl -JLayeredPane used in the Main class.
	 * @return Integer, i
	 */
	private int initBarn(JLayeredPane jl) {
		int i=16;
		switch(color) {
			case yellow :
				i=0;
				barn.add(new Case(10, 10, true, Colorp.yellow, null, jl, b, 60));
				barn.add(new Case(13, 10, true, Colorp.yellow, null, jl, b, 61));
				barn.add(new Case(10, 13, true, Colorp.yellow, null, jl, b, 62));
				barn.add(new Case(13, 13, true, Colorp.yellow, null, jl, b, 63));
				break;
			case red :
				i=4;
				barn.add(new Case(10, 1, true, Colorp.red, null, jl, b, 64));
				barn.add(new Case(13, 1, true, Colorp.red, null, jl, b, 65));
				barn.add(new Case(10, 4, true, Colorp.red, null, jl, b, 66));
				barn.add(new Case(13, 4, true, Colorp.red, null, jl, b, 67));
				break;
			case blue :
				i=8;
				barn.add(new Case(1, 1, true, Colorp.blue, null, jl, b, 68));
				barn.add(new Case(4, 1, true, Colorp.blue, null, jl, b, 69));
				barn.add(new Case(1, 4, true, Colorp.blue, null, jl, b, 70));
				barn.add(new Case(4, 4, true, Colorp.blue, null, jl, b, 71));
				break;
			case green :
				i=12;
				barn.add(new Case(1, 10, true, Colorp.green, null, jl, b, 72));
				barn.add(new Case(4, 10, true, Colorp.green, null, jl, b, 73));
				barn.add(new Case(1, 13, true, Colorp.green, null, jl, b, 74));
				barn.add(new Case(4, 13, true, Colorp.green, null, jl, b, 75));
				break;
		}
		return i;
	}
	
	/**
	 * @brief initialize the end line of the player
	 * @param jl - JLayeredPane used in the Main class.
	 */
	private void initEnd(JLayeredPane jl) {
		switch(color) {
			case yellow :
				end.add(new Case(13, 7, true, null, Colorp.yellow, jl, b, 76));
				end.add(new Case(12, 7, true, null, Colorp.yellow, jl, b, 77));
				end.add(new Case(11, 7, true, null, Colorp.yellow, jl, b, 78));
				end.add(new Case(10, 7, true, null, Colorp.yellow, jl, b, 79));
				end.add(new Case(9, 7, true, null, Colorp.yellow, jl, b, 80));
				end.add(new Case(8, 7, true, null, Colorp.yellow, jl, b, 81));
				break;
			case red :
				end.add(new Case(7, 1, true, null, Colorp.red, jl, b, 82));
				end.add(new Case(7, 2, true, null, Colorp.red, jl, b, 83));
				end.add(new Case(7, 3, true, null, Colorp.red, jl, b, 84));
				end.add(new Case(7, 4, true, null, Colorp.red, jl, b, 85));
				end.add(new Case(7, 5, true, null, Colorp.red, jl, b, 86));
				end.add(new Case(7, 6, true, null, Colorp.red, jl, b, 87));
				break;
			case blue :
				end.add(new Case(1, 7, true, null, Colorp.blue, jl, b, 88));
				end.add(new Case(2, 7, true, null, Colorp.blue, jl, b, 89));
				end.add(new Case(3, 7, true, null, Colorp.blue, jl, b, 90));
				end.add(new Case(4, 7, true, null, Colorp.blue, jl, b, 91));
				end.add(new Case(5, 7, true, null, Colorp.blue, jl, b, 92));
				end.add(new Case(6, 7, true, null, Colorp.blue, jl, b, 93));
				break;
			case green :
				end.add(new Case(7, 13, true, null, Colorp.green, jl, b, 94));
				end.add(new Case(7, 12, true, null, Colorp.green, jl, b, 95));
				end.add(new Case(7, 11, true, null, Colorp.green, jl, b, 96));
				end.add(new Case(7, 10, true, null, Colorp.green, jl, b, 97));
				end.add(new Case(7, 9, true, null, Colorp.green, jl, b, 98));
				end.add(new Case(7, 8, true, null, Colorp.green, jl, b, 98));
				break;
		}
	}
	
	
	/**
	 * @brief verify if a pawn moved
	 * @return Boolean, moved
	 */
	public boolean movePerformed() {
		boolean moved = false;
		  for(Pawn pa : pawns){
			  //System.out.println(pawns.indexOf(pa)+" : "+pa.getMoved());
			  if(pa.getMoved()) {
				  moved = true;
				  pa.disableMoved();
			  }
			  
		  }
		  //System.out.println("Value of moved : "+moved);
		  return moved;
	}
	
	/**
	 * @brief setter for the starting case of the player
	 * @param c - Case, the case we want to be the starting case
	 */
	protected void setStartingCase(Case c) {
		this.startingCase = c;
	}
	
	
	/**
	 * @brief getter for starting case
	 * @return Case, startingCase
	 */
	public Case getStartingCase() {
		return startingCase;
	}
	
	
	/**
	 * @brief add a pawn in a list of pawns
	 * @param p - Pawn, pawn we want to add in the list
	 */
	public void addPawn(Pawn p) {
		pawns.add(p);
	}
	
	
	/**
	 * @brief getter for finish line
	 * @return List<Case>, end
	 */
	public List<Case> getEnd() {
		return end;
	}
	
	
	/**
	 * @brief getter for barn
	 * @return List<Case>, barn
	 */
	public List<Case> getBarn() {
		return barn;
	}
	
	
	/**
	 * @brief getter for a list of pawns
	 * @return List<Pawn>, pawns
	 */
	public List<Pawn> getPawns() {
		return pawns;
	}
	
	
	/**
	 * @brief getter for Colorp
	 * @return Colorp, color
	 */
	public Colorp getColor() {
		return color;
	}
}
