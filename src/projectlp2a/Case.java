package projectlp2a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;


/**
 * @class Case.java
 * @brief Case class, represent a case of the Board.
 * @details It inherits from a JButton, so every case is clickable.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */
@SuppressWarnings("serial")
public class Case extends JButton {
	private boolean safe; //<! 
	private Colorp startColor; //<! 
	private Colorp finishColor; //<! 
	private int coordX; //<! 
	private int coordY; //<! 
	private int idCase; //<! 
	private Board b; //<! 
	
	
	
	public Case(int coordX, int coordY, boolean safe, Colorp startColor, Colorp finishColor, JLayeredPane jl, Board board, int i) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.safe = safe;
		this.startColor = startColor;
		this.finishColor = finishColor;
		b = board;
		
		idCase = i;
		this.setBounds(coordX*49, coordY*49, 49, 49);
		this.setVisible(true);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		jl.add(this, new Integer(idCase+20));
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Position of this case  : x="+coordX+"; y="+coordY);
				b.setFocusedCase(getCase());
			}
		});
	}
	
	private Case getCase() {
		return this;
	}
	
	public int getIdCase() {
		return idCase;
	}
	
	public int getX() {
		return coordX;
	}
	
	public int getY() {
		return coordY;
	}
	
	public Colorp getfinishColor() {
		return finishColor;
	}
	
	public Colorp getstartColor() {
		return startColor;
	}
	
	public boolean isSafe() {
		return safe;
	}
	
	public boolean isEqual(Case c) {
		return ((c.getX() == this.getX())&&(c.getY()==this.getY()));
	}
	
	public boolean isPawnStanding(Pawn pa) {
		return ((this.getX() == pa.getRelativeX())&&(this.getY() == pa.getRelativeY()));
	}
	
}
