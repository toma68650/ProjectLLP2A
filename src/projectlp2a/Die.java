package projectlp2a;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

@SuppressWarnings("serial")
public class Die extends Random {
	private JButton die;
	
	public Die(JFrame frame) {
		super();

	
	
		die = new JButton(new ImageIcon("Image/die/dice_1.png"));
		die.setVisible(false);
		getButton().setBorder(BorderFactory.createEmptyBorder());
		getButton().setBorderPainted(false);
		getButton().setOpaque(false);
		
		
		getButton().setContentAreaFilled(false);

		
		die.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				
				System.out.println(dice);
				getButton().setIcon(dice);

			}
		});
		die.setBounds(708, 25, 150, 128);
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
	public int rollDice() {
		return this.nextInt(6)+1;
	}
	
	/**
	 * @method whoStart();
	 * @return a number between 1 and 4 to pick up a player randomly
	 */
	public int whoStart() {
        return this.nextInt(4)+1;
    }

}