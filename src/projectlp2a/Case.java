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
	private boolean safe; //!< true if the case is a safe case, false else
	private Colorp startColor; //!< Is this case the first case on the board for a player ?
	private Colorp finishColor; //!< Is this case the last case for a player ?
	private int coordX; //!< Relative position of this case on the X axis  
	private int coordY; //!< Relative position of this case on the Y axis
	private int idCase; //!< identificator for the case, each case has a different id
	private Board b; //!< Board the case belong to
	
	
	/**
	 * @brief default constructor for a case.
	 * @param coordX - Integer, the relative X coordinate of the case. 
	 * @param coordY - Integer, the relative Y coordinate of the case.
	 * @param safe - boolean, is the case a safe case ?
	 * @param startColor - Colorp, is this case the start Case for a player? And for which player?
	 * @param finishColor - Colorp, is this case the finish Case for a player? And for which player?
	 * @param jl - JLayeredPane used in the Main class.
	 * @param board - Board, the main board.
	 * @param i - Integer, the index of this case.
	 */
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
				b.setFocusedCase(getCase());
			}
		});
	}
	
	/**
	 * @brief This method is used to let the actionListener of the button get this Case.
	 * @return Case, this Case.
	 */
	private Case getCase() {
		return this;
	}
	
	
	/**
	 * @brief getter for idCase.
	 * @return Integer, idCase.
	 */
	public int getIdCase() {
		return idCase;
	}
	
	/**
	 * @brief getter for coordX
	 * @return Integer, coordX.
	 */
	public int getX() {
		return coordX;
	}
	
	
	/**
	 * @brief getter for coordY
	 * @return Integer, coordY.
	 */
	public int getY() {
		return coordY;
	}
	
	
	/**
	 * @brief getter for finishColor.
	 * @return Colorp, finishColor.
	 */
	public Colorp getfinishColor() {
		return finishColor;
	}
	
	
	/**
	 * @brief getter for startColor.
	 * @return Colorp, startColor.
	 */
	public Colorp getstartColor() {
		return startColor;
	}
	
	
	/**
	 * @brief getter for safe.
	 * @return boolean, safe.
	 */
	public boolean isSafe() {
		return safe;
	}
	
	
	/**
	 * @brief isEqual() "override".
	 * @param c - Case, the case to be compared with.
	 * @return boolean. True if c has the same as this, false else.
	 */
	public boolean isEqual(Case c) {
		return ((c.getX() == this.getX())&&(c.getY()==this.getY()));
	}
	
	
	/**
	 * @brief Chcek if pa is standing on this Case.
	 * @param pa - Pawn, the pawn that we are looking for.
	 * @return a boolean. True if pa is on this, false else.
	 */
	public boolean isPawnStanding(Pawn pa) {
		return ((this.getX() == pa.getRelativeX())&&(this.getY() == pa.getRelativeY()));
	}
	
}
