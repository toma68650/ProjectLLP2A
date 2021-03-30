package projectlp2a;

public class Case {
	private Direction directionNext;
	private boolean safe;
	private Color startColor;
	private Color finishCase;
	private int coordX;
	private int coordY;
	
	
	public Case(Direction dn, boolean s, Color startColor) {
		this.directionNext = dn;
		this.safe = s;
		this.startColor = startColor;
		this.finishCase = null;
	}
	
	public Case(Direction dn, boolean s, Color finishCase) {
		this.directionNext = dn;
		this.safe = s;
		this.finishCase = finishCase;
		this.startColor = null;
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
