package content;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation extends Thread{
	
	private BufferedImage[] animation;
	private BufferedImage aktuellerSprite, aktuellerSpriteReverse;

	public void setAnimation(String quelle, int x, int y, int anzahl) throws IOException {
		
		//import JPG image1
		BufferedImage spriteSheet = ImageIO.read(new File(quelle));
		
		animation = new BufferedImage[anzahl];
		
		for (int i = 0; i < anzahl; i++) {
			animation[i] = spriteSheet.getSubimage(x * i, 0, x, y);
			//System.out.println("Bild Frame gesetzt: " + animation[i]);
		}			
	}
	
	
	public BufferedImage getSprite(boolean gespiegelt) {
		if (gespiegelt) {
			return aktuellerSpriteReverse;
		}
		return aktuellerSprite;
		
	}
	
	public void run() {

		
		boolean animIsRunning = true;
		while (animIsRunning) {
			for (int i = 0; i < (animation.length); i++) {
				aktuellerSprite = animation[i];
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
	}
	

	
	
	
}
