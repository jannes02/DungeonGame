package content;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	private String tileName;
	private BufferedImage tile[];
	private int x = 16, y = 16;

	Tile(String tName) {
		this.tileName = tName;
		tile = new BufferedImage[8];
		try {
			tile[0] = ImageIO.read(new File("rsc/sprites/tiles/" + tileName + "_mid.png"));
			tile[1] = ImageIO.read(new File("rsc/sprites/test13.png"));
			tile[2] = ImageIO.read(new File("rsc/sprites/test31.png"));
			tile[3] = ImageIO.read(new File("rsc/sprites/test33.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	BufferedImage drawTile(int i) {
		return tile[i];
	}

	public String getTileName() {
		return tileName;
	}
}
