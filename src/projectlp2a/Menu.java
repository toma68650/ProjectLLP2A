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

@SuppressWarnings("serial")
public class Menu extends JPanel {
	
	private JPanel options;
	public JButton startButton;
	public JButton startAiButton;
	public JButton quitButton;
	public JButton resumeButton;
	
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
		/* SETING OF THE CONSTRAINTS FOR THE GRIDBAGLAYOUT */
		gb.setConstraints(quitButton, gbc);
		gb.setConstraints(resumeButton, gbc);
		gb.setConstraints(startButton, gbc);
		gb.setConstraints(startAiButton, gbc);

		/* ADDING OF THE BUTTON ON THE INTERN PANEL ********/
		options.add(startButton);
		options.add(startAiButton);
		options.add(resumeButton);
		options.add(quitButton);
	
	}
	
	public void changeToRestart() {
		startButton.setText("Restart game");
		startAiButton.setText("Restart a game with ai");
	}
	
}
