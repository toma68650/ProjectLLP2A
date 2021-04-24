package projectlp2a;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
/**
 * @class Menu.java
 * @brief menu where the players can set up some settings.
 * @details The class inherits from JPanel.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */ 
@SuppressWarnings("serial")
public class Menu extends JPanel {
	
	private JPanel options; //!< A JPanel used to contain the options. It is used to have a good rendering on the options disposition. It is linked with a GridBagLayout manager.
	public JButton startButton; //!< The button to display the game board.
	public JButton startAiButton; //!< The button to display the game board and play with ai.
	public JButton quitButton; //!< The button to quit the game.
	public JButton resumeButton; //!< The button to resume the game when it was paused.
	public JButton themeButton; //!< The button to switch theme.
	
	public Menu() {
		super();
		setBounds(0,0,500,500);
		setLayout(new GridBagLayout());
		
		options = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		options.setLayout(gb);
		
		add(options, new GridBagConstraints());
		
		/* INITIALIZATION OF THE INTERN PANEL LAYOUT ********/
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.ipady = 20;
		gbc.gridheight = 20;
		gbc.weightx = 1;
	    gbc.weighty = 1;
	    gbc.gridx = 1;
		
	    /* INITIALIZATION OF THE STARTBUTTON ***************/
		startButton = new JButton();
		startButton.setFont(new Font("Arial",Font.ITALIC,20));
		startButton.setText("Start a game");
		startButton.setActionCommand(Actions.startMenu.name());
		/* INITIALIZATION OF THE STARTAIBUTTON *************/
		startAiButton = new JButton();
		startAiButton.setFont(new Font("Arial",Font.ITALIC,20));
		startAiButton.setText("Start a game with ai");
		startAiButton.setActionCommand(Actions.startAiMenu.name());
		
		/* INITIALIZATION OF THE QUIT BUTTON ***************/
		quitButton = new JButton();
		quitButton.setFont(new Font("Arial",Font.ITALIC,20));
		quitButton.setText("Quit");
		quitButton.setActionCommand(Actions.quitMenu.name());
		/* INITIALIZATION OF THE RESUME BUTTON *************/
		resumeButton = new JButton();
		resumeButton.setFont(new Font("Arial",Font.ITALIC,20));
		resumeButton.setText("Resume");
		resumeButton.setActionCommand(Actions.resumeMenu.name());
		resumeButton.setVisible(false);
		/* INITIALIZATION OF THE DARK/LIGHT THEME BUTTON ***/
		themeButton = new JButton();
		themeButton.setFont(new Font("Arial",Font.ITALIC,20));
		themeButton.setText("Dark theme");
		themeButton.setActionCommand(Actions.themeMenu.name());
		/* SETING OF THE CONSTRAINTS FOR THE GRIDBAGLAYOUT */
		gb.setConstraints(quitButton, gbc);
		gb.setConstraints(resumeButton, gbc);
		gb.setConstraints(startButton, gbc);
		gb.setConstraints(startAiButton, gbc);
		gb.setConstraints(themeButton, gbc);

		/* ADDING OF THE BUTTON ON THE INTERN PANEL ********/
		options.add(startButton);
		options.add(startAiButton);
		options.add(resumeButton);
		options.add(themeButton);
		options.add(quitButton);
	
	}
	
	public void changeToRestart() {
		startButton.setText("Restart game");
		startAiButton.setText("Restart a game with ai");
	}
	
	public void changeToDarkTheme(boolean isDarkTheme) {
		if(isDarkTheme) {
			themeButton.setText("Dark theme");
		} else {
			themeButton.setText("Light theme");
		}
	}
	
}
