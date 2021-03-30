package projectlp2a;
import java.util.*;

public class Pawn {
	public Color color;
	public boolean state;
	public int number;
	public int position;
	
	public Pawn(Color c, boolean s, int n, int p) {
		this.color=c;
		this.state=s;
		this.number=n;
		this.position=p;
	}
	
	public void move(int nbcase) {
		this.position=this.position + nbcase;
	}
	
	public boolean finish(Case fcase) {
		if(this.color.equals(fcase)){
			return true;
		}
	}
	
	public int eatPawn(Pawn p, Player b) {
		if(this.color.equals(p.color)) {
			this.position=p.position;
			p.position=b.barn;
		}else {
			return -1;
		}
	}
}