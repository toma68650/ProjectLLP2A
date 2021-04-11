package projectlp2a;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import projectlp2a.Player;


public class Interface {

	private JFrame frame;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface(frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface(JFrame frame) {
		initialize(frame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JFrame frame) {
		//frame = new JFrame();
		//frame.setBounds(100, 100, 861, 693);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		
		JButton GREEN = new JButton("Green");
		GREEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.color =Color.GREEN;
				System.out.println("You're Green");
				GREEN.setVisible(false);
			}
		});
		GREEN.setBounds(718, 156, 119, 115);
		frame.getContentPane().add(GREEN);
		
		JButton RED = new JButton("Red");
		RED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.color =Color.RED;
				System.out.println("You're Red");
				RED.setVisible(false);
			}
		});
		RED.setBounds(718, 268, 119, 115);
		frame.getContentPane().add(RED);
		
		JButton BLUE = new JButton("Blue");
		BLUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.color =Color.BLUE;
				System.out.println("You're Blue");
				BLUE.setVisible(false);
			}
		});
		BLUE.setBounds(718, 380, 119, 115);
		frame.getContentPane().add(BLUE);
		
		JButton YELLOW = new JButton("Yellow");
		YELLOW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player.color =Color.YELLOW;
				System.out.println("You're Yellow");
				YELLOW.setVisible(false);
			}
		});
		YELLOW.setBounds(718, 495, 119, 115);
		frame.getContentPane().add(YELLOW);
		
		Die mydie = new Die();
		frame.getContentPane().add(mydie.getButton());
		
		int resultDice;
		switch (resultDice){
		case 1 : 
			Icon dice = new ImageIcon("Image/dice_2.jpg");
		case 2 : 
			 dice = new ImageIcon("Image/dice_2.jpg");
		case 3 : 
			 dice = new ImageIcon("Image/dice_3.jpg");
		case 4 : 
			 dice = new ImageIcon("Image/dice_4.jpg");
		case 5 : 
			 dice = new ImageIcon("Image/dice_5.jpg");
		case 6 : 
			 dice = new ImageIcon("Image/dice_6.jpg");
		}
		JLabel result = new JLabel();
		result.setIcon(dice);
	}
}
