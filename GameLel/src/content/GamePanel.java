package content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener {

	public static int WIDTH = 512;
	public static int HEIGHT = 288;
	public static boolean EDIT_MODE;
	public static int MOUSE_X, MOUSE_Y;

	public int fensterH = 900, fensterW = 1600;

	private Thread thread;
	private boolean running;

	private BufferedImage image;
	private Graphics2D g;

	private int FPS = 60;
	private double averageFPS;

	private Player player;

	private Weltobjekt wo1;

	private JFrame window;

	private UI_object btn1;

	private boolean wahrheit;

	private World world;

	private UI ui;

	private Camera cam;

	// CONSTRUCTOR <===============================>
	public GamePanel(JFrame w) {

		super();
		setFocusable(true);
		requestFocus();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setCursor(w.getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(),
				null));

		window = w;
		player = new Player();
		cam = new Camera();
		// ui = new UI();
		// ui.setWorld(world);

		// ui.setMarker(world.getHoveredMaptile());

	}
	// CONSTRUCTOR END <==================================>

	// FUNCTIONS
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
		addMouseListener(this);

	}

	public void run() {
		running = true;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		System.out.println("HIIIIIIII");
		world = new World();
		world.startLoadWorld();

		wo1 = new Weltobjekt();

		long startTime;
		long URDTimeMS;
		long waitTime;
		long totalTime = 0;

		int frameCount = 0;
		int maxFrameCount = 60;

		long targetTime = 1000 / FPS;

		// GameLOOP
		while (running) {

			startTime = System.nanoTime();

			gameUpdate();

			gameRender();

			gameDraw();

			URDTimeMS = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMS;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
			}

			totalTime += System.nanoTime() - startTime;
			frameCount++;
			if (frameCount == maxFrameCount) {
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}

	}

	private void gameUpdate() {

		player.update();
		// ui.update();

		fensterAnpassen();

		// hitboxBerrechnen();
		rechnen();

	}

	private void rechnen() {
		int mouseX = ((int) MouseInfo.getPointerInfo().getLocation().getX()) + 1;
		MOUSE_X = (int) (mouseX / 3.75);
		int mouseY = ((int) MouseInfo.getPointerInfo().getLocation().getY()) + 1;
		MOUSE_Y = (int) (mouseY / 3.75);

	}

	private void gameRender() {

		g.setColor(new Color(220, 200, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);

		world.draw(g);
		wo1.draw(g);
		player.draw(g);

		g.drawString("FPS: " + EDIT_MODE, 100, 100);

		g.translate(-cam.getCameraOffsetX(), -cam.getCameraOffsetY());

		// ui.draw(g);

	}

	private void gameDraw() {

		Graphics g2 = this.getGraphics();

		// g2.translate(90, 40);
		g2.drawImage(image, 0, 0, fensterW, fensterH, null);

		g2.dispose();
	}

	private void fensterAnpassen() {
		if (fensterH != window.getHeight()) {
			fensterH = window.getHeight();

		}
		if (fensterW != window.getWidth()) {
			fensterW = window.getWidth();
		}
	}

//	private void hitboxBerrechnen() {
//		if (player.getPos().width > wo1.getPos().width
//				&& player.getPos().width < (wo1.getPos().width + wo1.getSiz().getWidth())) {
//			if ((player.getPos().height + player.getSiz().getHeight() - 10) >= (wo1.getPos().height)) {
//				player.setAufDemBoden(true);
//			} else {
//				player.setAufDemBoden(false);
//			}
//		} else {
//			wahrheit = false;
//		}
//	}

	@Override
	public void keyPressed(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if (keyCode == KeyEvent.VK_UP)
			player.setUp(true);
		if (keyCode == KeyEvent.VK_DOWN)
			player.setDown(true);
		if (keyCode == KeyEvent.VK_LEFT)
			player.setLeft(true);
		if (keyCode == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if (keyCode == KeyEvent.VK_ESCAPE)
			System.exit(1);
		if (keyCode == KeyEvent.VK_ALT) {
			if (!EDIT_MODE)
				EDIT_MODE = true;
			else
				EDIT_MODE = false;
		}

	}

	@Override
	public void keyReleased(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if (keyCode == KeyEvent.VK_UP)
			player.setUp(false);
		if (keyCode == KeyEvent.VK_DOWN)
			player.setDown(false);
		if (keyCode == KeyEvent.VK_LEFT)
			player.setLeft(false);
		if (keyCode == KeyEvent.VK_RIGHT)
			player.setRight(false);

	}

	@Override
	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (EDIT_MODE) {
			ui.updateMarker(world.getHoveredMaptile());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
