package projectlp2a;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {

	private JLayeredPane jl;
	public Board board;
	private Interface window;
	private boolean actionRealized = false;
	private int d=0;
	
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
		boolean played=false;
		boolean moved = p.movePerformed();
		while(!played) {
			if(d==0) {
				System.out.println("You must throw a die first ! ");
			} else {
				if(!moved) {
					System.out.println("You must move a pawn ! ");
				} else {
					moved = true;
				}
			}
		}
	}
	
	private void Turn() {
		for(Player p : board.getPlayers()) {
			TurnPlayer(p);
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
		main.Turn();
		//b1.setPreferredSize(new Dimension(300, 200));
		
;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == Actions.rollDice.name()) {
			
		}
		
	}
	
	

}
