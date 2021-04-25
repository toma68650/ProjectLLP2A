package projectlp2a;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import projectlp2a.Player;

/**
 * @class Interface.java
 * @brief The interface owns all the buttons displayed on the board during the game.
 * @author alexandrev - thomasl 
 * @version 1.0
 * @date 2021
 */
public class Interface {

	private JLabel rememberer; //!< A JLabel remembering what player must play.
	private FadePane pane; //!< A message that fade out.
	public JButton startGame=null; //!< The button for starting a game.
	public JButton options=null; //!< The button used to choose an option.
	private ArrayList<Player> colorNotClicked; //!< List of players that were not clicked. It is mainly used when there is AI to know which player become an AI.
	
	private JButton GREEN; //!< The button for choosing the green player.
	private JButton RED; //!< The button for choosing the red player.
	private JButton BLUE; //!< The button for choosing the blue player.
	private JButton YELLOW; //!< The button for choosing the yellow player.
	
	private Die myDie; //!< The die used during the game.
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
		colorNotClicked = new ArrayList<Player>();
		colorNotClicked.add(b.greenP);
		colorNotClicked.add(b.redP);
		colorNotClicked.add(b.blueP);
		colorNotClicked.add(b.yellowP);
		
		/***************************************************
		 **************** COLOR BUTTONS ********************
		 ***************************************************/
		GREEN = new JButton("Green");
		GREEN.setBackground(Color.green);
		GREEN.setForeground(Color.black);
		GREEN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player greenP = b.greenP;
				b.getPlayers().add(greenP);
				System.out.println("You're Green");
				pane.changeAnnounce("You're Green",Color.green);
				GREEN.setVisible(false);
				colorNotClicked.remove(greenP);
			}
		});
		//GREEN.setBounds(800, 156, 119, 115);
		GREEN.setBounds(815, 169, 90, 90);
		frame.add(GREEN);
		
		RED = new JButton("Red");
		RED.setBackground(Color.red);
		RED.setForeground(Color.white);
		RED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player redP = b.redP;
				b.getPlayers().add(redP);
				System.out.println("You're Red");
				pane.changeAnnounce("You're Red",Color.red);
				RED.setVisible(false);
				colorNotClicked.remove(redP);
			}
		});
		//RED.setBounds(800, 268, 119, 115);
		RED.setBounds(815, 281, 90, 90);
		frame.add(RED);
		
		BLUE = new JButton("Blue");
		BLUE.setBackground(Color.blue);
		BLUE.setForeground(Color.white);
		BLUE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player blueP = b.blueP;
				b.getPlayers().add(blueP);
				System.out.println("You're Blue");
				pane.changeAnnounce("You're Blue",Color.blue);
				BLUE.setVisible(false);
				colorNotClicked.remove(blueP);
			}
		});
		//BLUE.setBounds(800, 380, 119, 115);
		BLUE.setBounds(815, 393, 90, 90);
		frame.add(BLUE);
		
		YELLOW = new JButton("Yellow");
		YELLOW.setBackground(Color.yellow);
		YELLOW.setForeground(Color.black);
		YELLOW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player yellowP = b.yellowP;
				b.getPlayers().add(yellowP);
				System.out.println("You're Yellow");
				pane.changeAnnounce("You're Yellow",Color.yellow);
				YELLOW.setVisible(false);
				colorNotClicked.remove(yellowP);
			}
		});
		//YELLOW.setBounds(800, 495, 119, 115);
		YELLOW.setBounds(815, 508, 90, 90);
		frame.add(YELLOW);
		
		/********************* START BUTTON *****************/
		startGame = new JButton("Start");
		
		startGame.setActionCommand(Actions.startGame.name());
		//startGame.setBounds(800, 610, 119, 115);
		startGame.setBounds(815, 623, 90, 35);
		frame.add(startGame);
		
		/************* DIE (WITH HIS BUTTON (OFC)) **********/
		myDie = new Die(frame);
		frame.add(myDie.getButton());
		
		/************* FADING LABEL CREATED *****************/
		initAnnounce("Let's start the game !");
		frame.add(pane, new Integer(200));
		
		/************* REMEMBER LABEL CREATED ***************/
		initRememberer();
		frame.add(rememberer, new Integer(201));
		
		/************* OPTION BUTTON CREATED ****************/
		options = new JButton(new ImageIcon("Image/cog.png"));
		
		options.setBorder(BorderFactory.createEmptyBorder());
		options.setBorderPainted(false);
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		
		options.setActionCommand(Actions.options.name());
		options.setBounds(950, 690, 40, 40);
		frame.add(options);
	}
	
	
	/**
	 * @brief Method to initialize the rememberer label.
	 */
	private void initRememberer() {
		rememberer = new JLabel();
		rememberer.setBounds(771,160,180,50 );
		rememberer.setFont(new Font("Arial",Font.BOLD, 20));
		rememberer.setOpaque(true);
		rememberer.setBackground(Color.black);
		
		rememberer.setVisible(false);
	}
	
	
	/**
	 * @brief Method to disable the rememberer.
	 */
	public void disableRememberer() {
		rememberer.setVisible(false);
	}
	
	
	/**
	 * @brief setter for the rememberer. Set the given text on the label with given color foreground.
	 * @param text - String, text to put on the rememberer.
	 * @param color - Color, color to give to the the rememberer foreground.
	 */
	public void setRemembererText(String text, Color color) {
		rememberer.setText(text);
		rememberer.setForeground(color);
		rememberer.setVisible(true);
	}
	
	
	/**
	 * @brief initialize the FadePane.
	 * @param text - String, text to put on the FadePane.
	 */
	public void initAnnounce(String text) {
		pane = new FadePane(text);
	}
	
	
	/**
	 * @brief getter for pane.
	 * @return FadePane, pane.
	 */
	public FadePane getPane() {
		return pane;
	}
	
	
	/**
	 * @brief getter for myDie.
	 * @return Die, myDie.
	 */
	public Die getDie() {
		return myDie;
	}
	
	
	/**
	 * @brief getter for startGame button.
	 * @return JButton, startGame.
	 */
	public JButton getStartGame() {
		return startGame;
	}
	
	
	/**
	 * @brief getter for colorNotClicked.
	 * @return ArrayList<Player>, colorNotClicked.
	 */
	public ArrayList<Player> getColorNotClicked(){
		return colorNotClicked;
	}
	
	
	/**
	 * @brief Set all the color button on the interface invisible. Useful when one plays with AI.
	 */
	public void setInterfaceInvisible() {
		GREEN.setVisible(false);
		RED.setVisible(false);
		BLUE.setVisible(false);
		YELLOW.setVisible(false);
	}
	
	
	/**
	 * @brief Restart all the things on the interface. Used when we want to restart a game.
	 * @param b - Board, the board where we want to pick the players.
	 */
	public void restartInterface(Board b) {
		colorNotClicked = new ArrayList<Player>();
		colorNotClicked.add(b.greenP);
		colorNotClicked.add(b.redP);
		colorNotClicked.add(b.blueP);
		colorNotClicked.add(b.yellowP);
		GREEN.setVisible(true);
		RED.setVisible(true);
		BLUE.setVisible(true);
		YELLOW.setVisible(true);
		startGame.setVisible(true);
		myDie.getButton().setEnabled(true);
		disableRememberer();
	}
	
}
