package projectlp2a;
import java.util.*;


public class Player {
	private List<Pawn> pawns;
	private Case barn;
	private List<Case> end;
	public static Color color;
	
	public Player(Case barn, Case end) {
		pawns = new ArrayList<Pawn>();
		this.barn = barn;
		this.end = new ArrayList<Case>();
		this.end.add(end);
	}
	
	public void addPawn(Pawn p) {
		pawns.add(p);
	}
	
	public List<Case> getEnd() {
		return end;
	}
	
	public Case getBarn() {
		return barn;
	}
	
	public List<Pawn> getPawns() {
		return pawns;
	}
}