package projectlp2a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
/**
 * @class AI.java
 * @brief A class representing an AI, she owns a player and she makes move it by thinking.
 * @details Here we use composition over inheritance because we do not want to change too much details about the existing code
 * So composition let us use all the things made w/o changing the initialization of the players.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */ 
public class AI {

	private Player playerAi; //!< We use composition : Instead of AI inherting from Player, we compose the AI with a Player 
	private Die die; //!< the die used in the Interface
	private Main main; //!< the main class to perform actions in the main to make it easier
	private Timer timer; //!< the timer of the AI, it is useful for put a speed gap on the ai
	private boolean action = false; //!< boolean set true if we ask the ai to make a move
	private List<AiStruggleListener> listeners = new ArrayList<AiStruggleListener>(); //!< Custom array of listeners for sending infromations to the main when an ai is done
	
	
	/**
	 * @brief default constructor for the class AI.
	 * @param p - a Player, the player we want to attribute to this ai.
	 * @param die - a Die, the die that the AI will used during the game.
	 * @param main - a Main object, it will be used to perform actions.
	 */
	public AI(Player p, Die die, Main main) {
		playerAi = p;
		this.die  = die;
		this.main = main;
		
		timer = new Timer(600, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(action) {
                	makeTurn();
                }      	
            }
        });
		timer.start();
	}
	
	
	/**
	 * @brief The ai roll a die.
	 * @return the value read on the die.
	 */
	private int rollDie() {
		return die.performAction();
	}
	
	/**
	 * @brief let the ai perform an action.
	 */
	public void setTurn() {
		action = true;
	}
	
	
	/**
	 * @brief The turn of an ai.
	 * @details The ai will roll the die, then choose a case.
	 * If she cannot do anything, she will struggle and then skip her turn.
	 * Else she will choose a Case by "thinking" with AiThoughts (no she does not dream of electric sheep).
	 * She will not stop until she is done.
	 */
	public void makeTurn() {
		int dieValue = 0;
		boolean struggle = false;
		dieValue = rollDie();
		System.out.println("Player "+playerAi.getColor()+" played a "+dieValue);
		Case focusedCase = aiThoughts(main.board, dieValue);
		if(focusedCase != null) {
			System.out.println("Player "+playerAi.getColor()+" decided to make something of his life");
			main.board.setFocusedCase(focusedCase);
			main.board.setActionAi(playerAi, dieValue);
		} else {
			struggle = true;
		}
		
		if(dieValue != 6 || struggle) {
			action = false;
			for(AiStruggleListener listener : listeners) {
				listener.aiFinished();
			}
		} 

	}
	
	
	/**
	 * @brief The thoughts of an AI.
	 * @details For choosing a case, she will follow a particular algorithm : 1. How much pawns are outside of the barn ? 2. Less than two -> I will ty to get one out. 3. Can I put a pawn in the end ? 4. Can I put a pawn in a safe place ? 5. Can I eat a pawn ? 6. I can none of this action, I move the pawn which is closer from the end of the board.
	 * @param board - the board that the ai will analyze. 
	 * @param dieValue - An integer, the value obtained with the die.
	 * @return A Case, the case chosen.
	 */
	private Case aiThoughts(Board board, int dieValue) {
		
		int pawnsInBarn = 0;
		List<Case> barnPawn = new ArrayList<Case>();
		
		int pawnsInEnd = 0;
		List<Case> endPawn = new ArrayList<Case>();
		List<Boolean> canGoForwardInEnd = new ArrayList<Boolean>();
		
		
		List<Case> boardPawn = new ArrayList<Case>();
		List<Boolean> canEat = new ArrayList<Boolean>();
		List<Boolean> canBecomeSafe = new ArrayList<Boolean>();
		List<Boolean> canEnterEnd = new ArrayList<Boolean>();
		
		
		for(Pawn p : playerAi.getPawns()) {
			for(Case c : playerAi.getBarn()) {
				if(c.isPawnStanding(p)) {
					pawnsInBarn++;
					barnPawn.add(c);
				}
			}
			for(Case c : playerAi.getEnd()) {
				if(c.isPawnStanding(p) && (playerAi.getEnd().indexOf(c) != 0)) {
					pawnsInEnd++;
					endPawn.add(c);
					canGoForwardInEnd.add(goForwardInEnd(dieValue,c));
				}
			}

			for(Case c : board.cases) {
				if(c.isPawnStanding(p)) {
					boardPawn.add(c);
					Case target = determineTarget(c,board,dieValue);
					if(board.cases.contains(target)) canEat.add(canEatPawn(p,board,target));
					if(board.cases.contains(target)) canBecomeSafe.add(canBecomeSafePawn(target));
					canEnterEnd.add(canEnterEndPawn(target));
				}
			}

			
			
		}
		if((barnPawn.size() == 4) &&(dieValue == 6)) {
			return barnPawn.get(0);
		} else if(boardPawn.size() != 0 && canEnterEnd.contains(true)) {
			return boardPawn.get(canEnterEnd.indexOf(true));
		} else if ((4-(pawnsInBarn+pawnsInEnd) < 2)&&(dieValue == 6)&&(pawnsInBarn > 0)) {
			return barnPawn.get(0);
		} else if(boardPawn.size() != 0 && canBecomeSafe.contains(true)) {
			return boardPawn.get(canBecomeSafe.indexOf(true));
		} else if(boardPawn.size() != 0 && canEat.contains(true)) {
			return boardPawn.get(canEat.indexOf(true));
		} else if(endPawn.size() != 0 && canGoForwardInEnd.contains(true)) {
			return endPawn.get(canGoForwardInEnd.indexOf(true));
		} else if( boardPawn.size() != 0){
			return boardPawn.get(boardPawn.size()-1);
		} else {
			return null;
		}
	}
	
	
	/**
	 * @brief Determine which case will be the next case occupied by the pawn if the ai touch the focusedCase.
	 * @param focusedCase - a Case where a pawn of the AI is standing.
	 * @param board - A Board that the AI will analyze.
	 * @param dieValue - The value of the die after the roll.
	 * @return a Case, the target.
	 */
	private Case determineTarget(Case focusedCase, Board board, int dieValue) {
		Case target = null;
		int  indexNextCase = (board.cases.indexOf(focusedCase)+dieValue); // The index of the next case if everything happen correctly
		int nbCaseBeforeEnd=-10; // The case before the beginning of the "stair" or "ladder" 
		
		/* We check if a case is the end before the horse arrive. The horse must stand at the beginning of the ladder to climb to it, else he will go back */
		for(int i = board.cases.indexOf(focusedCase);i<indexNextCase;i++) {
			if(board.cases.get(i%56) == playerAi.getEnd().get(0)) {
				nbCaseBeforeEnd = i-board.cases.indexOf(focusedCase);
				System.out.println("Value of nbCaseBeforeEnd : "+nbCaseBeforeEnd);
			}
		}
		
		if(dieValue - nbCaseBeforeEnd == 0) {
			target = board.cases.get(indexNextCase%56);
		} else if ( nbCaseBeforeEnd > 0) {
			dieValue -= nbCaseBeforeEnd;
			target = playerAi.getEnd().get(dieValue);
		} else if(nbCaseBeforeEnd == 0) {
			if (dieValue != 6) {
				target = playerAi.getEnd().get(dieValue);
			}
		} else {
			target = board.cases.get(indexNextCase%56);							
		}
		
		return target;
		
	}
	
	
	/**
	 * @brief Check if the Pawn pa can eat another pawn. 
	 * @param pa - Pawn, we will verify if this pawn can eat another pawn.
	 * @param board - Board, the board which will be analyzed.
	 * @param target - the case targeted.s
	 * @return a boolean. True if pa can eat another pawn, false else.
	 */
	private boolean canEatPawn(Pawn pa, Board board, Case target) {
		boolean canEat=false;
		List<Pawn> pawnsInDanger;
		for(int i=0;i<4;i++) {
			pawnsInDanger=null;
			pawnsInDanger = new ArrayList<Pawn>();
			if(!(board.getPlayers().get(i) == playerAi)) {
				for(int j =0;j<4;j++) {
					if( (board.getPlayers().get(i).getPawns().get(j).getRelativeX()==target.getX()) && (board.getPlayers().get(i).getPawns().get(j).getRelativeY()==target.getY()) && !(board.getPlayers().get(i).getPawns().get(j).getColor().equals(pa.getColor()))) {
						if(!target.isSafe()) {
							pawnsInDanger.add(board.getPlayers().get(i).getPawns().get(j));
						}
					}
				}
				if(pawnsInDanger.size()==1) {
					canEat=  true;
				}
			}
		}
		return canEat;
	}
	
	
	/**
	 * @brief Check if a pawn can become safe.
	 * @param target - the case where a pawn can go.
	 * @return a boolean.True if a pawn can become safe, false else.
	 */
	private boolean canBecomeSafePawn(Case target) {
		boolean isPawn = false;
		for(Pawn pa : playerAi.getPawns()) {
			if(target.isPawnStanding(pa)) isPawn=true;
		}
		return (target.isSafe() || isPawn);
	}
	
	
	/**
	 * @brief Check if a pawn can enter in the end.
	 * @param target - The case targeted.
	 * @return a boolean. True if a pawn can enter in the end, false else.
	 */
	private boolean canEnterEndPawn(Case target) {
		return (playerAi.getEnd().indexOf(target) > 0);
	}
	
	
	/**
	 * @brief Check if a pawn can go forward in his end.
	 * @param dieValue - Integer, The value of the die.
	 * @param focusedCase - Case, The case where a pawn is standing. 
	 * @return a boolean. True if the pawn can go forward in the end, false else.
	 */
	private boolean goForwardInEnd(int dieValue, Case focusedCase) {
		if(playerAi.getEnd().contains(focusedCase)) {
			return (playerAi.getEnd().indexOf(focusedCase)+dieValue <= 6);
		} else {
			return false;
		}
	}
	
	
	/**
	 * @brief Getter for playerAi.
	 * @return Player, playerAi.
	 */
	public Player getPlayer() {
		return playerAi;
	}

	/**
	 * @brief Add a listener to the list of listeners that the AI owns.
	 * @param listener - the listener to add.
	 */
	public void addListeners(AiStruggleListener listener) {
		this.listeners.add(listener);
	}


}
