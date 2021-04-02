package projectlp2a;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Board {
	
	ArrayList<Player> players = new ArrayList<Player>();
	
	public void startGame() {
		
	}
	
	public void isFinish() {
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f1 = new JFrame();
		JLabel l1 = new JLabel();
		f1.setSize(1090,780);
		f1.setResizable(false);
		Icon plateau = new ImageIcon("Image/plateauprojet.png");
		l1.setIcon(plateau);
	     f1.add(l1);
	     f1.setVisible(true);

	}
}
