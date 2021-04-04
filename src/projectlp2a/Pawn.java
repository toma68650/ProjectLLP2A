package projectlp2a;

import javax.swing.*;

import java.awt.Image;

// Here we use composition over inheritance because we don't want to keep the interface of an Image, which is pretty difficult to  
public class Pawn {
	JLabel pawn;
	ImageIcon pawnSprite;
	
	public Pawn(JFrame f) {
		loadImage();
		pawn = new JLabel();
		pawn.setIcon(pawnSprite);
		f.add(pawn);
		
	}
	
	private void loadImage() {
		pawnSprite = new ImageIcon("Image/pawn_sprite_green");		
	}


}
