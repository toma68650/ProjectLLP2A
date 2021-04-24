package projectlp2a;

import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.*;

/**
 * @class Die.java
 * @brief Die class, represents the die with his image.
 * @details It inherits from Random.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */
@SuppressWarnings("serial")
public class Die extends Random {
	private JButton die; //!< the button of the die
	private int resultDice; //!< the result of the die when rolled
	
	
	/**
	 * @brief the default constructor for a Die.
	 * @param frame - JLayeredPane, the JLayeredPane of the main function.
	 */
	public Die(JLayeredPane frame) {
		super();
		resultDice=0;
	
	
		die = new JButton(new ImageIcon("Image/die/dice_1.png"));
		getButton().setBorder(BorderFactory.createEmptyBorder());
		getButton().setBorderPainted(false);
		getButton().setOpaque(false);
		
		
		getButton().setContentAreaFilled(false);

		
		die.setActionCommand(Actions.rollDice.name());
		die.setBounds(785, 25, 150, 128);
		die.setVisible(true);
	}
	
	
	/**
	 * @brief getter for die.
	 * @return JButton, the die attribute.
	 */
	public JButton getButton() {
		return die;
	}
	
	
	/**
	 * @brief method to roll a dice
	 * @return the result of the die (integer between 1 & 6)
	 */
	private int rollDice() {
		return this.nextInt(6)+1;
	}
	
	
	/**
	 * @brief method to determine who must start.
	 * @return a number between 1 and 4 to pick up a player randomly
	 */
	public int whoStart() {
        return this.nextInt(4)+1;
    }
	
	
	/**
	 * @brief roll a die with an "animation".
	 * @return Integer, The result of the die.
	 */
	public int performAction() {
		int resultDice= rollDice();
		ImageIcon dice=null;
		switch (resultDice){
		case 1 : 
			dice = new ImageIcon("Image/die/dice_1.png");
			break;
		case 2 : 
			 dice = new ImageIcon("Image/die/dice_2.png");
			 break;
		case 3 : 
			 dice = new ImageIcon("Image/die/dice_3.png");
			 break;
		case 4 : 
			 dice = new ImageIcon("Image/die/dice_4.png");
			 break;
		case 5 : 
			 dice = new ImageIcon("Image/die/dice_5.png");
			 break;
		case 6 : 
			 dice = new ImageIcon("Image/die/dice_6.png");
			 break;
		}
		getButton().setIcon(dice);
		return resultDice;
	}
	
	
	/**
	 * @brief a getter for resultDice.
	 * @return Integer, resultDice.
	 */
	public int getResult() {
		return this.resultDice;
	}
	
	
	/**
	 * @brief reset the value of the die.
	 */
	public void resetDie() {
		this.resultDice = 0;
		die.setEnabled(true);
	}
}