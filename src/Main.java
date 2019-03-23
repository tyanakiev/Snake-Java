import java.awt.Color;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		//Draws the window of the game.
		JFrame mainFrame = new JFrame();
		GameEngine gameEngine = new GameEngine();
		
		mainFrame.setBounds(10, 10, 905, 700);
		mainFrame.setBackground(Color.DARK_GRAY);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(gameEngine);
	}

}
