package content;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.image.BufferedImage;

public class Weltobjekt {
	private BufferedImage sprite;
	private int x = 160, y = 200, w = 160, h = 16;
	
	Weltobjekt(){
		sprite = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	}
	
	
	
	public void draw(Graphics2D g) {
		//sprite.setRGB(66, 100, 200);
		
		g.drawImage(sprite, x, y, w, h, null);
	}
	
	public Dimension getPos() {
		return new Dimension(x, y);
	}
	
	public Dimension getSiz() {
		return new Dimension(w, h);
	}
	
}

