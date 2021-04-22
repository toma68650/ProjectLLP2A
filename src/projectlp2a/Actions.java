package projectlp2a;
/**
 * @enum Actions.java
 * @brief Actions sended to the Main class mainly by pressing on a button.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */ 
public enum Actions {
	startMenu, //!< We want to display the game and not anymore the menu
	startAiMenu, //!< We also want to display the game but with AI
	quitMenu, //!< We want to quit the game, it close the window
	resumeMenu, //!< We want to continue the game after making a pause
	themeMenu, //!< We want to switch to dark or light theme
	
	options, //!< We want to access to the menu
	rollDice, //!< We rolled a die and one want to perform a action
	startGame; //!< One want to begin the game
}
