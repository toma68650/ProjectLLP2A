package projectlp2a;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

@SuppressWarnings("serial")
public class Die extends Random {
	private JButton die;
	private int resultDice;
	
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
	
	public JButton getButton() {
		return die;
	}
	/**
	 * @method rollDice()
	 * @brief method to roll a dice
	 * @return the result of the die (integer between 1 & 6)
	 */
	private int rollDice() {
		return this.nextInt(6)+1;
	}
	
	/**
	 * @method whoStart();
	 * @return a number between 1 and 4 to pick up a player randomly
	 */
	public int whoStart() {
        return this.nextInt(4)+1;
    }
	
	public int performAction() {
		int resultDice= rollDice();
		ImageIcon dice=null;
		System.out.println("Value of die : "+resultDice);
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
		/*if(resultDice!=6) {
			getButton().setEnabled(false);
		}*/
		System.out.println(dice);
		getButton().setIcon(dice);
		return resultDice;
	}
	
	
	
	private void updateResult(int die) {
		this.resultDice=die;
	}

	public int getResult() {
		return this.resultDice;
	}
	
	public void resetDie() {
		this.resultDice = 0;
		die.setEnabled(true);
	}
}