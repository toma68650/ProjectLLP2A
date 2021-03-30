package projectlp2a;
import java.awt.Color;

public class Case {
	private Direction directionNext;
	private boolean safe;
	private Color startColor;
	private Color finishCase;
	private int coordX;
	private int coordY;
	
	
	public Case(Direction dn, boolean s, Color startColor, Color finishCase, int x, int y) {
		this.directionNext = dn;
		this.safe = s;
		this.startColor = startColor;
		this.finishCase = finishCase;
		coordX = x;
		coordY = y;
	}
	
	public Case(Direction dn, boolean s) {
		this.directionNext = dn;
		this.safe = s;
		this.startColor = null;
		this.finishCase = null;
	}
	
	public Case(Case c) {
		this.directionNext = c.directionNext;
		this.finishCase = c.finishCase;
		this.safe = c.safe;
		this.startColor = c.startColor;
	}
	
	
}
