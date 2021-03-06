package content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;

public class Player extends Thread {

	private int r, dx, dy, speed, lives;
	public static int x, y;
	private boolean left, right, up, down, aufDemBoden, amSprigen = false;
	Color color1, color2;
	BufferedImage sprite;
	private Animation idleAnimation, walkAnimation, runAnimation, jumpAnimation;
	private boolean yAchseSpielgeln = false;
	private Timer lol;

	private Thread jumpT;
	private Runnable jump;

	// CONSTRUCTOR
	public Player() {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		r = 5;

		dx = 0;
		dy = 0;
		speed = 1;

		lives = 3;

		lol = new Timer();

		jumpT = new Thread(jump);

		jump = new Runnable() {
			public void run() {
				if (aufDemBoden) {
					amSprigen = true;
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					amSprigen = false;
				}
			}
		};

		idleAnimation = new Animation();
		try {
			idleAnimation.setAnimation("rsc/sprites/player_idle.png", 24, 32, 11);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		idleAnimation.start();

		walkAnimation = new Animation();
		try {
			walkAnimation.setAnimation("rsc/sprites/player_walk.png", 22, 32, 13);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		walkAnimation.start();

	}

	// FUNCTIONS
	public void setUp(boolean b) {
		up = b;
	}

	public void setDown(boolean b) {
		down = b;
	}

	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void setAufDemBoden(boolean b) {
		aufDemBoden = b;
	}

	public void update() {
		if (up == true) {

			if (!jumpT.isAlive()) {

				jumpT = new Thread(jump);
				jumpT.start();

			}

		} else if (down) {
			dy = speed;
			sprite = idleAnimation.getSprite(false);
		} else if (left) {
			dx = -speed;
			sprite = walkAnimation.getSprite(false);
			yAchseSpielgeln = true;

		}

		else if (right) {
			dx = +speed;
			sprite = walkAnimation.getSprite(false);
			yAchseSpielgeln = false;
		} else {
			sprite = idleAnimation.getSprite(false);
		}

		x += dx;
		y += dy;

		/*
		 * if (x < r) x = r; if(y < r) y = r; if(x > GamePanel.WIDTH - r) x =
		 * GamePanel.WIDTH - r; if(y > GamePanel.HEIGHT - r) x = GamePanel.HEIGHT - r;
		 */

		dx = 0;

		dy = 0;

	}

	public void draw(Graphics2D g) {

		if (yAchseSpielgeln) {
			g.drawImage(sprite, x - r + sprite.getWidth(), y - r, -sprite.getWidth(), sprite.getHeight(), null);
		} else {
			g.drawImage(sprite, x - r, y - r, null);

		}

		// g.fillOval(x - r, y - r, 2 * r, 2 * r);

		/*
		 * g.setStroke(new BasicStroke(3)); g.setColor(color1.darker()); g.drawOval(x -
		 * r, y - r, 2 * r, 2 * r); g.setStroke(new BasicStroke(1));
		 */
	}

	public Dimension getPos() {
		return new Dimension(x, y);
	}

	public Dimension getSiz() {
		return new Dimension(sprite.getWidth(), sprite.getHeight());
	}

}
