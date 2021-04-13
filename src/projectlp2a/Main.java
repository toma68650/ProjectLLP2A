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
		board = new Board(this, jl);
        jl.setLayout(null);
		jl.add(board, new Integer(1));
        getContentPane().add(jl);
        
        try {
			Interface window = new Interface(jl, board);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
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
	
	

}
