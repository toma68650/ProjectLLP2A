package projectlp2a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AI {
	//Here we use composition over inheritance because we do not want to change too much details about the existing code.
	//So composition let us use all the things made w/o changing the initialization of the players
	Player playerAi;
	
	public AI(Player p) {
		playerAi = p;
	}
	
	private int rollDie(Die die) {
		return die.performAction();
	}
	
	public void makeTurn(Die die, Main main) {
		int dieValue = rollDie(die);
		if(main.rollDieAction()) {
			aiThoughts(main.board, dieValue);
		}
		
	}
	
	public Case aiThoughts(Board board, int dieValue) {
		
		boolean inBarn=false;
		int pawnsInBarn = 0;
		List<Case> barnPawn = new ArrayList<Case>();
		
		boolean inEnd=false;
		int pawnsInEnd = 0;
		List<Case> endPawn = new ArrayList<Case>();
		
		
		List<Case> boardPawn = new ArrayList<Case>();
		List<Boolean> canEat = new ArrayList<Boolean>();
		List<Boolean> canBecomeSafe = new ArrayList<Boolean>();
		List<Boolean> canEnterEnd = new ArrayList<Boolean>(); 
		
		
		for(Pawn p : playerAi.getPawns()) {
			for(Case c : playerAi.getBarn()) {
				if(c.isPawnStanding(p)) {
					pawnsInBarn++;
					barnPawn.add(c);
					inBarn=true;
				}
			}
			if(!inBarn) {
				for(Case c : playerAi.getEnd()) {
					if(c.isPawnStanding(p) && (playerAi.getEnd().indexOf(c) != 0)) {
						pawnsInEnd++;
						endPawn.add(c);
						inEnd=true;
					}
				}
				if(!inEnd) {
					for(Case c : board.cases) {
						if(c.isPawnStanding(p)) {
							boardPawn.add(c);
							Case target = determineTarget(c,board,dieValue);
							canEat.add(canEatPawn(p,board,target));
							canBecomeSafe.add(canBecomeSafePawn(target));
							canEnterEnd.add(canEnterEndPawn(target));
						}
					}
				}
			}
			
			
		}
		if((4-(pawnsInBarn+pawnsInBarn) < 2)&&(dieValue == 6)) {
			return barnPawn.get(0);
		}
		return barnPawn.get(0);
	}
	
	public Case determineTarget(Case focusedCase, Board board, int dieValue) {
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
	
	public boolean canEatPawn(Pawn pa, Board board, Case target) {
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
	
	public boolean canBecomeSafePawn(Case target) {
		return target.isSafe();
	}
	
	public boolean canEnterEndPawn(Case target) {
		return (playerAi.getEnd().indexOf(target) > 0);
	}
}
