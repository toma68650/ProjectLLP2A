package projectlp2a;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * @class Main.java
 * @brief Main class of our project. It is where the program is running
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 * @warning Only use one main in a project
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener, PawnMoveListener, AiStruggleListener{

	private JLayeredPane jl; //!< A JLayeredPane used to put all the components useful during the game. It acts like a pile of paper we put on each other.
	public Board board; //!< The board used during the game.
	private Menu menu; //!< The menu used during the game.
	private Interface window; //!< The interface during the game.
	private boolean finished=false; //!< Equals false while any player has finished.
	private boolean isAi = false; //!< check if we play with ai or not.
	private List<AI> ais; //!< AI present in the game we are playing.
	
	int resultDice; //!< The result of the last die roll.
	int turn=0; //!< the counter of turn. It is only incremented during the game and set to 0 at the beginning of a new game.
	
	private boolean dieRolled = false; //!< check if the die has been rolled or not.
	private boolean gameStarted = false; //!< check if the game is running or not
	
	/***** THEME ******************************/
	private boolean isDarkTheme = true; //!< Used for the theme : equals true if the dark theme is set.
	private Color colorText = Color.white; //!< Used for the theme : the font color of the FadePane (white if we are in dark theme and black else). 
	
	public Main() {
		initUI();
	}
	/**
	 * @brief initialize the user's interface and the menu
	 */
	private void initUI() {
		jl = new JLayeredPane();
		board = new Board(this, jl);
        jl.setLayout(null);
        jl.setBackground(Color.black);
		jl.add(board, new Integer(1));
        getContentPane().add(jl);

        try {
			window = new Interface(jl, board);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        resultDice = 0;
        
        menu = new Menu();
		menu.setVisible(true);
		jl.add(menu,new Integer(300));
        
        /* Setup of the action listeners for the menu */
        menu.quitButton.addActionListener(this);
        menu.startButton.addActionListener(this);
        menu.startAiButton.addActionListener(this);
        menu.resumeButton.addActionListener(this);
        menu.themeButton.addActionListener(this);
        
        /* Setup of the action listeners for the game */
        this.window.getStartGame().addActionListener(this);
        this.window.getDie().getButton().addActionListener(this);
        this.window.options.addActionListener(this);
        this.board.addPawnMoveListener(this);
  
        
        /*JLabel label = new JLabel("Yo la street !");
        label.setBounds(800, 500, 100, 100);
        label.setText("Yo la street");
        jl.add(label, new Integer(100));
        label.setVisible(true); */
        setTitle("Game of poney");
        //setSize(735,765);
        setSize(500,500);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
	
	/**
	 * @brief verify if all actions are made during the turn of a player
	 * @param p - Player, the player that is playing
	 */
	private void TurnPlayer(Player p) {
		window.getPane().changeAnnounce(p.getColor()+"'s turn", colorText);
		boolean hasPlay=false;
		while(window.getDie().getResult()==0) {
			System.out.println(window.getDie().getResult());
		}
		System.out.println("Gone through !");
		while(!hasPlay) {
			
			if(p.movePerformed() || !board.isLegalMove(p)) {
				hasPlay=true;
				System.out.println("Next player!");
			}	
		}
		window.getDie().resetDie();
	}
	
	/**
	 * @brief make all the players play
	 */
	private  void Turn() {
		for(Player p : board.getPlayers()) {
			TurnPlayer(p);
			finished=board.isFinish(p);
		}
	}
	
	/**
	 * @brief manage all the processing of the game
	 */
	protected void Game() {
		finished=false;
		if(board.getPlayers().size() == 4) {
			while(!finished) {
				Turn();
			}
		} else {
			System.out.println("There is not enough players !");
		}

		
	}
	
	
	public static void main(String[] args) {
		Main main = new Main();
	
		main.jl.setVisible(true);
		main.jl.setOpaque(true);
		main.setVisible(true);
				

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == Actions.startGame.name()) {
			if(isAi) {
				if(this.board.getPlayers().size() >= 1){
					ais = new ArrayList<AI>();
					window.setInterfaceInvisible();
					for(Player p : window.getColorNotClicked()) {
						board.getPlayers().add(p);
						ais.add(new AI(p, window.getDie(), this));
					}
					for(AI ai : ais) {
						ai.addListeners(this);
					}
					window.getPane().changeAnnounce("Game's starting", colorText);
					window.startGame.setVisible(false);
					gameStarted =true;
					turn = 1;
					String message = board.getPlayers().get((turn-1)%4).getColor()+"'s turn";
					window.getPane().changeAnnounce(message, colorText);
					window.setRemembererText("  "+message, convertColorpToAwtColor(board.getPlayers().get((turn-1)%4).getColor()));
				} else {
					window.getPane().changeAnnounce("Not enough players...", colorText);
				}
			} else {
				if(this.board.getPlayers().size() == 4) {
					window.getPane().changeAnnounce("Game's starting",colorText);
					window.startGame.setVisible(false);
					gameStarted =true;
					turn = 1;
					String message = board.getPlayers().get((turn-1)%4).getColor()+"'s turn";
					window.getPane().changeAnnounce(message, colorText);
					window.setRemembererText("  "+message, convertColorpToAwtColor(board.getPlayers().get((turn-1)%4).getColor()));
				} else {
					window.getPane().changeAnnounce("Not enough players...", colorText);
				}
			}
			
			
			
			
		} else if(e.getActionCommand() == Actions.rollDice.name()) {
			rollDieAction();			
			
			
			
			
		} else if(e.getActionCommand() == Actions.startMenu.name()) {
			isAi = false;
			//setSize(1000,765);
			setSize(1020, 790);
			menu.setVisible(false);
			if (menu.startButton.getText().contentEquals("Restart game")) {
				gameStarted=false;
				board.restartBoard();
				window.restartInterface(this.board);
			} else {
				menu.changeToRestart();
				menu.resumeButton.setVisible(true);
			}
			
			
			
			
		} else if(e.getActionCommand() == Actions.startAiMenu.name()) {	
			isAi = true;
			//setSize(1000,765);
			setSize(1020, 805);
			menu.setVisible(false);
			if (menu.startButton.getText().contentEquals("Restart game")) {
				gameStarted=false;
				board.restartBoard();
				window.restartInterface(board);
			} else {
				menu.changeToRestart();
				menu.resumeButton.setVisible(true);
			}
			
			
			
			
		} else if(e.getActionCommand() == Actions.quitMenu.name()) {
			dispose();
			
			
			
			
			
		}else if(e.getActionCommand() == Actions.resumeMenu.name()) { 
			//setSize(1000,765);
			setSize(1020, 805);
			menu.setVisible(false);
			
		
		} else if(e.getActionCommand() == Actions.themeMenu.name()) {
			if(isDarkTheme) {
				isDarkTheme = false;
				colorText = Color.black;
				board.setOpaque(false);
				jl.setOpaque(false);
			} else {
				isDarkTheme = true;
				colorText = Color.white;
				board.setOpaque(true);
				jl.setOpaque(true);
			}
			menu.changeToDarkTheme(isDarkTheme);
			
			
		} else if(e.getActionCommand() == Actions.options.name()) {
			setSize(500,500);
			menu.setVisible(true);
		}
	}

	@Override
	public void pawnActionPerformed() {
		if(gameStarted && dieRolled) {
			if(!isAi || !isCorrespondingAi(board.getPlayers().get((turn-1)%4))){
				System.out.println("I entered here");
				boolean isPawnMove = board.getPlayers().get((turn-1)%4).movePerformed();
				/* To show the end */
				//board.getPlayers().get((turn-1)%4).getPawns().get(0).move(board.getPlayers().get((turn-1)%4).getEnd().get(1).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(1).getY());
				//board.getPlayers().get((turn-1)%4).getPawns().get(1).move(board.getPlayers().get((turn-1)%4).getEnd().get(2).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(2).getY());
				//board.getPlayers().get((turn-1)%4).getPawns().get(2).move(board.getPlayers().get((turn-1)%4).getEnd().get(3).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(3).getY());
				//board.getPlayers().get((turn-1)%4).getPawns().get(3).move(board.getPlayers().get((turn-1)%4).getEnd().get(4).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(4).getY());
				if(board.isLegalMove(board.getPlayers().get((turn-1)%4)) && !isPawnMove) {
					window.getPane().changeAnnounce("It's an illegal move !", colorText);
				} else if(resultDice == 6 ) {
					window.getDie().getButton().setEnabled(true);
					dieRolled=false; 				
				} else {
					if(board.isFinish(board.getPlayers().get((turn-1)%4))) {
						window.getDie().getButton().setEnabled(false);
						board.action =false;
						window.disableRememberer();
						window.getPane().changeAnnounce(board.getPlayers().get((turn-1)%4).getColor() + " won this game !!", colorText);
					} else {
						nextTurn();
					}
				}
			}	
			
		}
	}
	

	/**
	 * @brief change the user's turn when it's over
	 */
	private void nextTurn() {
		turn++;
		String message = board.getPlayers().get((turn-1)%4).getColor()+"'s turn";
		window.getPane().changeAnnounce(message, colorText);
		window.setRemembererText("  "+message, convertColorpToAwtColor(board.getPlayers().get((turn-1)%4).getColor()));
		if(!isAi) {
			window.getDie().getButton().setEnabled(true);
			dieRolled=false;
		} else {
			if(!isCorrespondingAi(board.getPlayers().get((turn-1)%4))) {
				window.getDie().getButton().setEnabled(true);
				dieRolled=false;
			} else {
				AI interstedAi =null;
				for(AI ai : ais) {
					if(ai.getPlayer() == board.getPlayers().get((turn-1)%4)){
						interstedAi = ai;
					}
				}
				window.getDie().getButton().setEnabled(false);
				interstedAi.setTurn();
			}
		}
		
	}
	
	/**
	 * @brief convert the Colorp argument in a color that is
	 * @param color - Colorp, color used for the players
	 * @return Color, color brought by awt
	 */
	private Color convertColorpToAwtColor(Colorp color) {
		switch(color){
			case yellow:
				return Color.yellow;
			case green:
				return Color.green;
			case red:
				return Color.red;
			case blue:
				return Color.blue;
			default :
				return null;
		}
	}
	/**
	 * 
	 * @return
	 */
	public boolean rollDieAction() {
		if(gameStarted && (!isAi || !isCorrespondingAi(board.getPlayers().get((turn-1)%4)))) {
			
			resultDice = window.getDie().performAction();
			dieRolled = true;
			board.setAction(board.getPlayers().get((turn-1)%4), resultDice);
			window.getDie().getButton().setEnabled(false);
			if(!board.isLegalMove(board.getPlayers().get((turn-1)%4))) {
				nextTurn();
				return false;
			}
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param p
	 * @return
	 */
	private boolean isCorrespondingAi(Player p) {
		boolean correspondingAi=false;
		for(AI ai : ais ) {
			if(ai.getPlayer() == p) {
				correspondingAi = true;
			}
		}
		return correspondingAi;
	}


	@Override
	public void aiFinished() {
		window.getDie().getButton().setEnabled(false);
		if(board.isFinish(board.getPlayers().get((turn-1)%4))) {
			board.action =false;
			window.disableRememberer();
			window.getPane().changeAnnounce(board.getPlayers().get((turn-1)%4).getColor() + " won !!!", colorText);
		} else {
			nextTurn();
		}
	}


	
	
}
