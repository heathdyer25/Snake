import java.awt.Color;

import javax.swing.JFrame;

/**
 * GameFrame class to contain GamePanel to run game in.
 * @author Heath Dyer
 *
 */
public class GameFrame extends JFrame {
	/** Panel the game runs in */
	GamePanel panel;
	
	/**
	 * Constructs new GameFrame. Holds the panel the game runs in.
	 */
	public GameFrame() {
		super("Snake!");
		panel = new GamePanel();
		this.add(panel);
		this.setBackground(Color.BLACK);
		this.setResizable(false);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
