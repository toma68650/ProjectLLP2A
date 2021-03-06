package projectlp2a;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * @class Pawn.java
 * @brief Pawn class, represents a pawn on a board
 * @details Pawn inherits from a JPanel.
 * @author alexandrev - thomasl
 * @version 1.0
 * @date 2021
 */ 
@SuppressWarnings("serial")
public class Pawn extends JPanel {
	
	private Timer timer; //!< The timer of the pawn. It is used to update the position of the pawn each second.
	private final int NB_OF_IMAGES_PER_SECOND = 100; //!< A constant. It stands for the refresh rate of the pawn.
	private Image pawnSprite; //!< The image representing the image of the pawn.
	
	private int targetx; //!< the real position of the case targeted on the X axis.
	private int targety; //!< the real position of the case targeted on the Y axis.
	
	private int beginingx=0; //!< The relative position of the first case took by the pawn. X axis.
	private int beginingy=0; //!< The relative position of the first case took by the pawn. Y axis.
	
	private int relativex=0; //!< The relative position of the pawn. X axis.
	private int relativey=0; //!< The relative position of the pawn. Y axis.
	
	private int x=0; //!< The real position of the pawn at each moment. X axis.
	private int y=0; //!< The real position of the pawn at each moment. Y axis.
	
	
	private boolean moved =false; //!< Used to know if the pawn has moved or not.
	
	private int dx=0; //!< The little delta performed at each second to move the pawn on the X axis.
	private int dy=0; //!< The little delta performed at each second to move the pawn on the Y axis. 
	private Colorp color; //!< The color of the pawn.
	
	
	/**
	 * @method public Pawn(Colorp color, JLayeredPane jl, int i, Case c)
	 * @brief Constructor of Pawn
	 * @param color - Colorp, color of the Pawn
	 * @param jl - JLayeredPane, the container where the pawn will be stick to
	 * @param i - index of the pawn in depth
	 * @param c - the case where the Pawn is summoned
	 */
	public Pawn(Colorp color, JLayeredPane jl, int i, Case c) {
		this.color = color;
		beginingx=c.getX();
		beginingy=c.getY();
		relativex=beginingx;
		relativey=beginingy;
		x=(relativex%2)*25 + (relativex/2)*49;
		y=(relativey%2)*25 + (relativey/2)*49;
		System.out.println("Position is : "+x+" "+y);
		loadImage();
		setBackground(Color.black);
		setBounds(0,0,735,735);
		setOpaque(false);
		setVisible(true);
		
		jl.add(this, new Integer(i));

		timer = new Timer(1000 / NB_OF_IMAGES_PER_SECOND, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if((dx!=0)|(dy!=0)) {
                	if(( x - targetx != 0)|( y - targety != 0)) {
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
	
	/**
	 * @brief load the images of the pawn
	 */
	private void loadImage() {
		ImageIcon pawnSpriteIcon = new ImageIcon("Image/pawn_sprite_"+color+".png");
		pawnSprite = pawnSpriteIcon.getImage();
		
		pawnSprite.getWidth(null);
		pawnSprite.getHeight(null);
	}
	/**
	 * @brief getter for relative x coordinate
	 * @return Integer, relativex
	 */
	public int getRelativeX() {
		return relativex;
	}
	
	/**
	 * @brief getter for relative y coordinate
	 * @return Integer, relativey
	 */
	public int getRelativeY() {
		return relativey;
	}
	
	/**
	 * @brief getter for x coordinates
	 * @return Integer, x
	 */
	public int getX() {
        return x;
    }

	/**
	 * @brief getter for y coordinates
	 * @return Integer, y
	 */
    public int getY() {
        return y;
    }
    
    /**
     * @brief getter for pawn's Sprite
     * @return Image, pawnSprite
     */
    public Image getImage() {
        
        return pawnSprite;
    }

    /**
     * @Override
     * @brief override of paintComponent method.
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
   
    
    /**
     * @brief Draw the pawn sprite at his origin position.
     * @param g
     */
   protected void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.getImage(), this.getX(), this.getY(), this);
    }   
   
   /**
    * @brief Method that generate a movement considering the target given.
    * @param finalX - Integer, x coordinate of the target.
    * @param finalY - Integer, y coordinate of the target.
    */
   public void move(int finalX, int finalY) {
	   if((finalX != relativex)||(finalY !=relativey)){

		   /* Get the real place of the pawn and the target */
		   targetx=(finalX%2)*25 + (finalX/2)*49;
		   targety=(finalY%2)*25 + (finalY/2)*49;
		   x=(relativex%2)*25 + (relativex/2)*49;
		   y=(relativey%2)*25 + (relativey/2)*49;
	       /* We create vector which observe the movement of the pawn */
		   driveVector();
		   
		   /* Verify if the pawn moved */
		   if((finalX != relativex)||(finalY !=relativey)) moved=true;
	      
	       relativex = finalX;
	       relativey = finalY;
	   }
   }
   
   
   /**
    * @brief Function that create a vector between the target and the current position. The it sets the dx and dy considering these vector.
    */
   private void driveVector() {
	   /* We create vector which observe the movement of the pawn */
	   int xv = targetx-x;
       int yv = targety-y;
       /* We "normalize" the vector */
       if(xv == 0) {
    	   dx=0;
    	   dy= yv/Math.abs(yv);
       } else if (yv == 0) {
    	   dy=0;
    	   dx= xv/Math.abs(xv);
       } else if(yv >= xv) {
    	   if(xv < 0) {
    		   dx=-1;
    	   } else {
        	   dx=1;
    	   }
    	   if (yv < 0) {
    		   dy=(int) Math.floor((double) yv/(Math.abs((double) xv)));
    	   } else {
    		   dy=(int) Math.ceil((double) yv/(Math.abs((double) xv)));
    	   }
           
       } else {
    	   if(yv < 0) {
    		   dy=-1;
    	   } else {
        	   dy=1;
    	   }
    	   if (xv < 0) {
    		   dx=(int) Math.floor(((double) xv)/(Math.abs((double) yv)));
    	   } else {
    		   dx=(int) Math.ceil(((double) xv)/(Math.abs((double) yv)));
    	   }
       }
   }
   
   
   /**
    * @brief Method that move the Pawn sprite of dx and dy respectively on x axis and y axis. 
    */
   private void step() {
	   x+=dx;
	   y+=dy;
       repaint(x-100, y-100,this.getWidth()+2,this.getHeight()+2);     
   }
   
   /**
    * @brief make a pawn go to his barn
    */
   public void comeBackHome() {
	   move(beginingx, beginingy);
   }
   
   /**
    * @brief getter for color
    * @return Color, color
    */
	public Colorp getColor() {
		return color;
	}

	/**
	 * @brief verify if a pawn has moved
	 * @return Boolean, moved
	 */
	public boolean getMoved() {
		return moved;
	}
	
	/**
	 * @brief make that a pawn can't move
	 */
	public void disableMoved() {
		moved=false;
	}
	
}
