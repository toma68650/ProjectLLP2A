package projectlp2a;

import javax.swing.*;

import java.lang.Object;
import java.math.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Here we use composition over inheritance because we don't want to keep the interface of an Image, which is pretty difficult to  
@SuppressWarnings("serial")
public class Pawn extends JPanel implements KeyListener {
	
	private Timer timer;
	private final int NB_OF_IMAGES_PER_SECOND = 40;
	private Image pawnSprite;
	
	
	private int targetx;
	private int targety;
	private int relativex;
	private int relativey;
	private int x=0;
	private int y=0;
	
	private int dx=0;
	private int dy=0;
	private String color;
	
	public Pawn() {
		color = "yellow";
		relativex=1;
		relativey=2;
		x=(relativex%2)*25 + (relativex/2)*49;
		y=(relativey%2)*25 + (relativey/2)*49;
		loadImage();
		setBackground(Color.black);
		setBounds(0,0,735,735);
		setOpaque(false);
		setVisible(true);

		timer = new Timer(1000 / NB_OF_IMAGES_PER_SECOND, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!((dx==0)&(dy==0))) {
                	if(( x - targetx != 0)&( y - targety != 0)) {
                		System.out.println("Valeur de realx : "+x+"; Valeur de realy : "+y+"; Valeur de targetx : "+targetx+"; Valeur de targety : "+targety);
                		driveVector();
                		step();
                	} else {
                		dx=0;
                		dy=0;
                	}
                }
            }
        });
        timer.start();
	}
	
	private void loadImage() {
		ImageIcon pawnSpriteIcon = new ImageIcon("Image/pawn_sprite_"+color+".png");
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

        g2d.drawImage(this.getImage(), this.getX(), this.getY(), this);
    }
   
   
   public void detectPawn(KeyEvent e, int dieResult) {
		   step();
   }
   
   public void move(int finalX, int finalY) {
	   /* Get the real place of the pawn and the target */
	   targetx=(finalX%2)*25 + (finalX/2)*49;
	   targety=(finalY%2)*25 + (finalY/2)*49;
	   x=(relativex%2)*25 + (relativex/2)*49;
	   y=(relativey%2)*25 + (relativey/2)*49;
       /* We create vector which observe the movement of the pawn */
	   driveVector();
       
       System.out.println("value of dx : "+dx+"; value of dy : "+dy);
      
       relativex = finalX;
       relativey = finalY;
   }
   
   private void driveVector() {
	   /* We create vector which observe the movement of the pawn */
	   int xv = targetx-x;
	   System.out.println("xv is : "+xv);
       int yv = targety-y;
       System.out.println("yv is : "+yv);
       /* We normalize the vector */
       if(yv >= xv) {
    	   if(xv < 0) {
    		   dx=-1;
    	   } else {
        	   dx=1;
    	   }
           dy=(int) Math.ceil((double) yv/((double) xv));
       } else {
    	   if(yv < 0) {
    		   dy=-1;
    	   } else {
        	   dy=1;
    	   }
    	   dx=(int) Math.ceil((double) xv/((double) yv));
       }
   }
   
   private void step() {
	   x+=dx;
	   y+=dy;
       repaint(x-10, y-10,this.getWidth()+2,this.getHeight()+2);     
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
