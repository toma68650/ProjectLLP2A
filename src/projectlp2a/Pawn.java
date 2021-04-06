package projectlp2a;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Here we use composition over inheritance because we don't want to keep the interface of an Image, which is pretty difficult to  
@SuppressWarnings("serial")
public class Pawn extends JPanel implements KeyListener {
	
	private Image pawnSprite;
	private int x;
	private int y;
	
	public Pawn() {
		loadImage();
		setBackground(Color.black);
		setBounds(0,0,735, 735);
		setOpaque(false);
		setVisible(true);
		x=12;
		y=0;
	}
	
	private void loadImage() {
		ImageIcon pawnSpriteIcon = new ImageIcon("Image/pawn_sprite_green.png");
		pawnSprite = pawnSpriteIcon.getImage();
		
		pawnSprite.getWidth(null);
		pawnSprite.getHeight(null);
	}
	
	public int getX() {
        
        return x;
    }

    public int getY() {
        
        return y;
    }
    
    public Image getImage() {
        
        return pawnSprite;
    }

    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
   protected void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.getImage(), this.getX()*48,this.getY()*48, this);
    }
   
   
   public void detectPawn(KeyEvent e, int dieResult) {
		   step(1,1);
   }
   
   public void move(int finalX, int finalY) {
       
       x = finalX;
       y = finalY;
   }
   
   private void step(int finalX, int finalY) {
       
       this.move(finalX, finalY);
       
       repaint(this.getX()-1, this.getY()-1, 
               this.getWidth()+2, this.getHeight()+2);     
   }
   

@Override
public void keyPressed(KeyEvent e) {
	if (e.getModifiers() == KeyEvent.BUTTON1_DOWN_MASK) {
	}
}

@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}

}
