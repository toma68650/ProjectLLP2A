package projectlp2a;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

@SuppressWarnings("serial")
public class Die extends Random {
	private JButton die;
	
	public Die() {
		super();
	
		die = new JButton("Launch Die");
		die.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Value of die : "+rollDice());
			}
		});
		die.setBounds(687, 33, 150, 126);
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