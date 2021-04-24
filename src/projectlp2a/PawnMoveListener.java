package projectlp2a;
/**
 * @interface PawnMoveListener.java
 * @brief A custom listener used to send informations to the main when a pawn has performed an action.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */ 
public interface PawnMoveListener {
	/**
	 * @method public void pawnActionPerformed();
	 * @brief Make the actions required when a pawn has perform a move.
	 */
	public void pawnActionPerformed();
}
