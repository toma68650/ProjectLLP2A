package projectlp2a;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener, PawnMoveListener {

	private JLayeredPane jl;
	public Board board;
	private Menu menu;
	private Interface window;
	private boolean finished=false;
	
	int resultDice;
	int turn=0;
	
	private boolean dieRolled = false;
	private boolean pawnMoved = false;
	private boolean gameStarted = false;
	
	public Main() {
		initUI();
	}
	
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
        menu.resumeButton.addActionListener(this);
        
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
	
	private void TurnPlayer(Player p) {
		window.getPane().changeAnnounce(p.getColor()+"'s turn", Color.black);
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
	
	private  void Turn() {
		for(Player p : board.getPlayers()) {
			TurnPlayer(p);
			finished=board.isFinish(p);
		}
	}
	
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
		//Pawn p = new Pawn(Colorp.yellow, main.jl, 2);
		//Pawn p1 = new Pawn(Colorp.blue, main.jl, 3);
		//Pawn p2 = new Pawn(Colorp.green, main.jl, 4);
		//p1.move(2, 2);
		//p.move(3,7);
		//p2.move(13,3);
		
		//JButton b = new JButton();
		//b.setBounds(0,0, 49, 49);

		//main.jl.add(b, new Integer(5));
		//b.setVisible(true);
		
		main.jl.setVisible(true);
		main.jl.setOpaque(true);
		//p.move(8, 3);
		main.setVisible(true);
				
		//b1.setPreferredSize(new Dimension(300, 200));


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == Actions.startGame.name()) {
			
			if(this.board.getPlayers().size() == 4) {
				window.getPane().changeAnnounce("Game's starting",Color.white);
				window.startGame.setVisible(false);
				gameStarted =true;
				turn = 1;
				String message = board.getPlayers().get((turn-1)%4).getColor()+"'s turn";
				window.getPane().changeAnnounce(message, Color.white);
				window.setRemembererText("  "+message, convertColorpToAwtColor(board.getPlayers().get((turn-1)%4).getColor()));
			} else {
				window.getPane().changeAnnounce("Not enough players...", Color.white);
			}
		} else if(e.getActionCommand() == Actions.rollDice.name()) {
			if(gameStarted) {
				
				resultDice = window.getDie().performAction();
				dieRolled = true;
				board.setAction(board.getPlayers().get((turn-1)%4), resultDice);
				window.getDie().getButton().setEnabled(false);
				if(!board.isLegalMove(board.getPlayers().get((turn-1)%4))) {
					nextTurn();
				}
			}
			
			
			
		} else if(e.getActionCommand() == Actions.startMenu.name()) {
			setSize(1000,765);
			menu.setVisible(false);
			if (menu.startButton.getText().contentEquals("Restart game")) {
				board.restartBoard();
				window.restartInterface();
			} else {
				menu.changeToRestart();
				menu.resumeButton.setVisible(true);
			}
		} else if(e.getActionCommand() == Actions.quitMenu.name()) {
			dispose();
		}else if(e.getActionCommand() == Actions.resumeMenu.name()) { 
			setSize(1000,765);
			menu.setVisible(false);
		} else if(e.getActionCommand() == Actions.options.name()) {
			setSize(500,500);
			menu.setVisible(true);
		}
	}

	@Override
	public void pawnActionPerformed() {
		if(gameStarted && dieRolled) {
			boolean isPawnMove = board.getPlayers().get((turn-1)%4).movePerformed();
			/* To show the end */
			//board.getPlayers().get((turn-1)%4).getPawns().get(0).move(board.getPlayers().get((turn-1)%4).getEnd().get(1).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(1).getY());
			//board.getPlayers().get((turn-1)%4).getPawns().get(1).move(board.getPlayers().get((turn-1)%4).getEnd().get(2).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(2).getY());
			//board.getPlayers().get((turn-1)%4).getPawns().get(2).move(board.getPlayers().get((turn-1)%4).getEnd().get(3).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(3).getY());
			//board.getPlayers().get((turn-1)%4).getPawns().get(3).move(board.getPlayers().get((turn-1)%4).getEnd().get(4).getX(),board.getPlayers().get((turn-1)%4).getEnd().get(4).getY());
			if(resultDice == 6) {
				window.getDie().getButton().setEnabled(true);
				dieRolled=false;
			} else if(board.isLegalMove(board.getPlayers().get((turn-1)%4)) && !isPawnMove) {
				window.getPane().changeAnnounce("It's an illegal move !", Color.white);
			} else {
				nextTurn();
			}
			if(board.isFinish(board.getPlayers().get((turn-1)%4))) {
				window.getDie().getButton().setEnabled(false);
				board.action =false;
				window.disableRememberer();
				window.getPane().changeAnnounce("Congratulations, "+ board.getPlayers().get((turn-1)%4).getColor() + "You won this amazing game !!", Color.white);
			}
		}
	}
	
	private void nextTurn() {
		turn++;
		String message = board.getPlayers().get((turn-1)%4).getColor()+"'s turn";
		window.getPane().changeAnnounce(message, Color.white);
		window.setRemembererText("  "+message, convertColorpToAwtColor(board.getPlayers().get((turn-1)%4).getColor()));
		window.getDie().getButton().setEnabled(true);
		dieRolled=false;
	}
	
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
	
	

}
