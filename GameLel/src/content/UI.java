package content;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class UI {

	World world;
	HashMap<String, UI_object> UI_objList = new HashMap<String, UI_object>();

	UI() {

		setCurser();
	}

	public void draw(Graphics g) {

		UI_objList.get("mouse").draw(g);

		// UI_objList.get("marker").draw(g);

	}

	public void update() {

		updateCurser();

	}

	public void setMarker(MapTile mt) {

		BufferedImage markerSprite;
		try {
			markerSprite = ImageIO.read(new File("rsc/sprites/ui/marker.png"));
			UI_object marker = new UI_object(0, 0, markerSprite);
			UI_objList.put("marker", marker);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void updateMarker(MapTile mt) {

		UI_object uio = UI_objList.get("marker");

		uio.setX(mt.getX());
		uio.setY(mt.getY());

		UI_objList.put("marker", uio);

	}

	private void updateCurser() {

		UI_objList.get("mouse").setX(GamePanel.MOUSE_X);
		UI_objList.get("mouse").setY(GamePanel.MOUSE_Y);

	}

	private void setCurser() {

		BufferedImage mouseCurser;
		try {
			mouseCurser = ImageIO.read(new File("rsc/sprites/ui/curser.png"));
			UI_object mouse = new UI_object(0, 0, mouseCurser);
			UI_objList.put("mouse", mouse);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setWorld(World w) {
		world = w;
	}

}
