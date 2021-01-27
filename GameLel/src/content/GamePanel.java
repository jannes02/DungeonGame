package content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	public static int WIDTH = 1600;
	public static int HEIGHT = 900;
	public int höhe = 900, breite = 1600;
	
	
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 60;
	private double averageFPS;
	
	private Player player;
	
	private JFrame window;
	
	private UI_object btn1;
	
	
	public GamePanel(JFrame w){
		
		super();
		setFocusable(true);
		requestFocus();
		setPreferredSize(new Dimension(breite, höhe));
		
		window = w;
		btn1 = new UI_object();
		
		btn1.setObject(100, 100, 300, 300);
		
	}
		
	//FUNCTIONS
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);	
			thread.start();
		}
		addKeyListener(this);
	}
		
	public void run() {
			running = true;
			
			image = new BufferedImage(breite, höhe, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) image.getGraphics();
			
			try {
				player = new Player();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			long startTime;
			long URDTimeMS;
			long waitTime;
			long totalTime = 0;
			
			int frameCount = 0;
			int maxFrameCount = 60;
			
			long targetTime = 1000 / FPS;
			
			
			
			//GameLOOP
			while (running) {
				
				startTime = System.nanoTime();
				
				gameUpdate();
				
				gameRender();

				gameDraw();
			
				URDTimeMS = (System.nanoTime() - startTime) / 1000000;
				waitTime = targetTime - URDTimeMS;
				
				try {
					Thread.sleep(waitTime);
				}
				catch (Exception e) {
				}
				
				totalTime += System.nanoTime() - startTime;
				frameCount ++;
				if (frameCount == maxFrameCount) {
					averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
					frameCount = 0;
					totalTime = 0;
				}
			}   
			

			
			
		}
	

	
	private void gameUpdate() {
			
			player.update();
			
			fensterAnpassen();
			
		}
		
	private void gameRender(){
			g.setColor(new Color(220, 200, 0));
			g.fillRect(9, 0, breite, höhe);
			g.setColor(Color.BLACK);
			g.drawString("FPS: " + btn1.getStatus(), 100, 100);
			
			player.draw(g);

		}
		
	private void gameDraw() {
			Graphics g2 = this.getGraphics();
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
		}

		
	private void fensterAnpassen() {
		if (höhe != window.getHeight()) {
			höhe = window.getHeight();
			
			//neues "Bühnenbild in richtiger auflösung erzeugen"
			image = new BufferedImage(breite, höhe, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) image.getGraphics();
		}
		if (breite != window.getWidth()) {
			breite = window.getWidth();
			
			//neues "Bühnenbild in richtiger auflösung erzeugen"
			image = new BufferedImage(breite, höhe, BufferedImage.TYPE_INT_RGB);
			g = (Graphics2D) image.getGraphics();
		}
	}

		
	@Override
	public void keyPressed(KeyEvent key) {
			int keyCode = key.getKeyCode();
			if(keyCode == KeyEvent.VK_UP) player.setUp(true);
			if(keyCode == KeyEvent.VK_DOWN) super.setSize(196,108);
			if(keyCode == KeyEvent.VK_LEFT) player.setLeft(true);
			if(keyCode == KeyEvent.VK_RIGHT) player.setRight(true);
			
		}

	@Override
	public void keyReleased(KeyEvent key) {
			int keyCode = key.getKeyCode();
			if(keyCode == KeyEvent.VK_UP) {
				player.setUp(false);

			}
			if(keyCode == KeyEvent.VK_DOWN) player.setDown(false);
			if(keyCode == KeyEvent.VK_LEFT) player.setLeft(false);
			if(keyCode == KeyEvent.VK_RIGHT) player.setRight(false);
			
		}

	@Override
	public void keyTyped(KeyEvent key) {
			// TODO Auto-generated method stub
			
	}
}
	
	
