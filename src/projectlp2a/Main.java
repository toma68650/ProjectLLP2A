package projectlp2a;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;

@SuppressWarnings("serial")
public class Main extends JFrame {

	JLayeredPane jl;
	public Board board;
	
	public Main() {
		initUI();
	}
	
	private void initUI() {
		jl = new JLayeredPane();
		board = new Board(this);
        getContentPane().setLayout(null);
		jl.add(board, new Integer(1));
        add(jl);
        
        setTitle("Game of poney");
        //setSize(735,765);
        setSize(1000,765);
        setBackground(Color.black);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
	
	public static void main(String[] args) {
		Main main = new Main();
		Pawn p = new Pawn(Colorp.yellow);
		Pawn p1 = new Pawn(Colorp.blue);
		Pawn p2 = new Pawn(Colorp.green);
		main.jl.add(p, new Integer(2));
		main.jl.add(p1, new Integer(3));
		main.jl.add(p2, new Integer(4));
		p1.move(2, 2);
		p.move(3,7);
		p2.move(13,3);
		
		JButton b = new JButton();
		b.setBounds(0,0, 25, 25);
		b.setVisible(true);
		main.jl.add(b);
		
		main.jl.setVisible(true);
		//p.move(8, 3);
		main.setVisible(true);
		
				
		//b1.setPreferredSize(new Dimension(300, 200));
		
;
	}
	
	

}
