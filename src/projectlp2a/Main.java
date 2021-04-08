package projectlp2a;
import javax.swing.*;
import java.awt.Dimension;

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
		
		jl.add(board, new Integer(1));
        add(jl);
        
        setTitle("Game of poney");
        setSize(735,765);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public static void main(String[] args) {
		Main main = new Main();
		Pawn p = new Pawn();
		main.jl.add(p, new Integer(2));
		main.jl.setVisible(true);
		p.move(7, 12);
		p.move(8,8);
		main.setVisible(true);
		
				
		//b1.setPreferredSize(new Dimension(300, 200));
		
;
	}
	
	

}
