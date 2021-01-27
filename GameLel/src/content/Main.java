package content;
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
		

	}

}
