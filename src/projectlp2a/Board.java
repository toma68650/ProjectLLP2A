package projectlp2a;
import java.util.*;
import javax.swing.*;
import java.awt.BorderLayout;

public class Board extends JLabel {
	
	Icon plateau;
	
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Case> cases = new ArrayList<Case>();
	
	public Board(JFrame f1) {
		super("Board");
		plateau = new ImageIcon("Image/plateauprojet_skin.png");
		this.setIcon(plateau);
		f1.add(this,BorderLayout.CENTER);
		//Pawn p = new Pawn(f1);
		}
	
	public void startGame() {
		
	}
	
	public void isFinish() {
		
	}

}
