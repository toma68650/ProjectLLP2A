package projectlp2a;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import projectlp2a.Player;


public class Interface {

	private FadePane pane;
	
	
	private Die myDie;
	/**
	 * Create the application.
	 */
	public Interface(JLayeredPane frame, Board b) {
		initialize(frame,b);
	}

	/**
	 * @method private void initialize(JLayeredPane frame, Board b)
	 * @brief Initialize the contents of the frame.
	 * @param frame - JLayeredPane, the JLayeredPnae where the buttons will be built
	 * @param b - Board, that players belong to
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
				pane.changeAnnounce("You're Green",Color.green);
				GREEN.setVisible(false);
			}
		});
		GREEN.setBounds(800, 156, 119, 115);
		frame.add(GREEN);
		
		JButton RED = new JButton("Red");
		RED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player redP = b.redP;
				b.getPlayers().add(redP);
				System.out.println("You're Red");
				pane.changeAnnounce("You're Red",Color.red);
				RED.setVisible(false);
			}
		});
		RED.setBounds(800, 268, 119, 115);
		frame.add(RED);
		
		JButton BLUE = new JButton("Blue");
		BLUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player blueP = b.blueP;
				b.getPlayers().add(blueP);
				System.out.println("You're Blue");
				pane.changeAnnounce("You're Blue",Color.blue);
				BLUE.setVisible(false);
			}
		});
		BLUE.setBounds(800, 380, 119, 115);
		frame.add(BLUE);
		
		JButton YELLOW = new JButton("Yellow");
		YELLOW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player yellowP = b.yellowP;
				b.getPlayers().add(yellowP);
				System.out.println("You're Yellow");
				pane.changeAnnounce("You're Yellow",Color.yellow);
				YELLOW.setVisible(false);
			}
		});
		YELLOW.setBounds(800, 495, 119, 115);
		frame.add(YELLOW);
		
		JButton startParty = new JButton("Start");
		startParty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Game's starting");
				pane.changeAnnounce("Game's starting",Color.black);
				startParty.setVisible(false);
				
			}
		});
		startParty.setBounds(800, 610, 119, 115);
		frame.add(startParty);
		
		myDie = new Die();
		frame.add(myDie.getButton());
		initAnnounce("Let's start the game !");
		
		frame.add(pane, new Integer(200));
	}
	
	public void initAnnounce(String text) {
		pane = new FadePane(text);
	}
	
	public FadePane getPane() {
		return pane;
	}
	
	public Die getDie() {
		return myDie;
	}
	
}
