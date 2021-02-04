package content;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setContentPane(new GamePanel(window));

		window.pack();
		window.setVisible(true);
		window.setSize(1600, 900);

		window.setResizable(false);

		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = graphics.getDefaultScreenDevice();
		// System.out.println("WIDTH: " + device.getDisplayMode().getWidth());
		device.setFullScreenWindow(window);

	}

}
