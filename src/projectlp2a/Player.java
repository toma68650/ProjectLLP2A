package projectlp2a;
import java.util.*;

import javax.swing.JLayeredPane;


public class Player {
	private Board b;
	private Case startingCase;
	private List<Pawn> pawns;
	private List<Case> barn;
	private List<Case> end;
	private Colorp color;
	
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
	
	protected void setStartingCase(Case c) {
		this.startingCase = c;
	}
	
	public Case getStartingCase() {
		return startingCase;
	}
	
	public void addPawn(Pawn p) {
		pawns.add(p);
	}
	
	public List<Case> getEnd() {
		return end;
	}
	
	public List<Case> getBarn() {
		return barn;
	}
	
	public List<Pawn> getPawns() {
		return pawns;
	}
	
	public Colorp getColor() {
		return color;
	}
}
