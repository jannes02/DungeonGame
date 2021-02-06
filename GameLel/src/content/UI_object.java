package content;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UI_object {
	private int x, y, h, w;
	private boolean active = true;
	private BufferedImage sprite;

	UI_object(int x, int y, BufferedImage s) {
		this.sprite = s;
		this.w = s.getWidth();
		this.h = s.getHeight();
		this.x = x;
		this.y = y;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSprite(BufferedImage s) {
		this.sprite = s;
	}

	public void setActive(boolean actv) {
		active = actv;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void draw(Graphics g) {
		g.drawImage(sprite, x, y, w, h, null);
	}

}
