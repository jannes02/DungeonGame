package content;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	private BufferedImage sprite;
	private int width, height;

	Tile(String tName) {

		try {
			sprite = ImageIO.read(new File("rsc/sprites/tiles/" + tName + "_mid.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
	}

	BufferedImage drawTile() {
		return sprite;
	}

	public int getInt(String ind) {
		int r = 0;
		if (ind.equals(new String("widht"))) {
			r = this.width;
		}
		if (ind.equals(new String("height"))) {
			r = this.height;
		}
		return r;
	}

}
