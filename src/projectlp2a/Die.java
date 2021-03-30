package projectlp2a;
import java.util.*;

public class Die extends Random {
	
	public Die() {
		super();
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