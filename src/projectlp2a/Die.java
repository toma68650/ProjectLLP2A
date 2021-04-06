package projectlp2a;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class Die extends Random {
	private JFrame frame;
	
	public Die() {
		super();
	
	
	JButton Die = new JButton("Launch Die");
	Die.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			rollDice();
		}
	});
	Die.setBounds(687, 33, 150, 126);
	frame.getContentPane().add(Die);
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