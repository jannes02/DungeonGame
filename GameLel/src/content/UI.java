package content;

import java.awt.Graphics;
import java.awt.PointerInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	PointerInfo m;
	int mouseX, mouseY;
	BufferedImage mouseCurser;

	UI() {
		try {
			mouseCurser = ImageIO.read(new File("rsc/sprites/ui/curser.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.drawImage(mouseCurser, GamePanel.MOUSE_X, GamePanel.MOUSE_Y, null);

	}

}
