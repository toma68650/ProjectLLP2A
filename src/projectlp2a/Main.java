package projectlp2a;

import java.awt.Dimension;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f1 = new JFrame("Game of poney");
		
		f1.setResizable(false);
		Board b1 = new Board(f1);
		
		//b1.setPreferredSize(new Dimension(300, 200));
		
		f1.pack();
	    f1.setVisible(true);
	}

}
