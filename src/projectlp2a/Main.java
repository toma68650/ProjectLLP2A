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
		jl.add(board, new Integer(1));
        getContentPane().add(jl);

        try {
			window = new Interface(jl, board);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        resultDice = 0;
        
        /* Setup of the action listeners */
        this.window.getStartGame().addActionListener(this);
        this.window.getDie().getButton().addActionListener(this);
        this.board.addPawnMoveListener(this);
        
        
        /*JLabel label = new JLabel("Yo la street !");
        label.setBounds(800, 500, 100, 100);
        label.setText("Yo la street");
        jl.add(label, new Integer(100));
        label.setVisible(true); */
        setTitle("Game of poney");
        //setSize(735,765);
        setSize(1000,765);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
	
	private void TurnPlayer(Player p) {
		window.getPane().changeAnnounce(p.getColor()+"'s turn", Color.black);
		int d= 0;
		boolean hasPlay=false;
		boolean moved = p.movePerformed();
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
		//p.move(8, 3);
		main.setVisible(true);
				
		//b1.setPreferredSize(new Dimension(300, 200));

;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == Actions.startGame.name()) {
			
			if(this.board.getPlayers().size() == 4) {
				window.getPane().changeAnnounce("Game's starting",Color.black);
				window.startGame.setVisible(false);
				gameStarted =true;
				turn = 1;
				window.getPane().changeAnnounce(board.getPlayers().get((turn-1)%4).getColor()+"'s turn", Color.black);
			} else {
				window.getPane().changeAnnounce("Not enough players...", Color.black);
			}
		} else if(e.getActionCommand() == Actions.rollDice.name()) {
			if(gameStarted) {
				
				resultDice = window.getDie().performAction();
				dieRolled = true;
				board.setAction(board.getPlayers().get((turn-1)%4), resultDice);
				window.getDie().getButton().setEnabled(false);
			}
		} 		
	}

	@Override
	public void pawnActionPerformed() {
		if(gameStarted && dieRolled) {
			if(resultDice == 6) {
				window.getDie().getButton().setEnabled(true);
				dieRolled=false;
			} else if(board.isLegalMove(board.getPlayers().get((turn-1)%4)) && !board.getPlayers().get((turn-1)%4).movePerformed()) {
				window.getPane().changeAnnounce("It's an illegal move !", Color.black);
			} else {
				turn++;
				window.getPane().changeAnnounce(board.getPlayers().get((turn-1)%4).getColor()+"'s turn", Color.black);
				window.getDie().getButton().setEnabled(true);
				dieRolled=false;
			}
			
		}
	}
	
	

}
