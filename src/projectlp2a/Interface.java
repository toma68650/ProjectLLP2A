package projectlp2a;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import projectlp2a.Player;


public class Interface {

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Interface(JLayeredPane frame, Board b) {
		initialize(frame,b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JLayeredPane frame, Board b) {
		//frame = new JFrame();
		//frame.setBounds(100, 100, 861, 693);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		JButton GREEN = new JButton("Green");
		GREEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player greenP = b.greenP;
				b.getPlayers().add(greenP);
				System.out.println("You're Green");
				GREEN.setVisible(false);
			}
		});
		GREEN.setBounds(718, 156, 119, 115);
		frame.add(GREEN);
		
		JButton RED = new JButton("Red");
		RED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player redP = b.redP;
				b.getPlayers().add(redP);
				System.out.println("You're Red");
				RED.setVisible(false);
			}
		});
		RED.setBounds(718, 268, 119, 115);
		frame.add(RED);
		
		JButton BLUE = new JButton("Blue");
		BLUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player blueP = b.blueP;
				b.getPlayers().add(blueP);
				System.out.println("You're Blue");
				BLUE.setVisible(false);
			}
		});
		BLUE.setBounds(718, 380, 119, 115);
		frame.add(BLUE);
		
		JButton YELLOW = new JButton("Yellow");
		YELLOW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player yellowP = b.yellowP;
				b.getPlayers().add(yellowP);
				System.out.println("You're Yellow");
				YELLOW.setVisible(false);
			}
		});
		YELLOW.setBounds(718, 495, 119, 115);
		frame.add(YELLOW);
		
		Die mydie = new Die();
		frame.add(mydie.getButton());
	}
}
