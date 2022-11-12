import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 
 */

/**
 * Instance of Score. Provides methods for resetting, incrementing, and drawing score to screen as well as drawing menu items.
 * @author heath
 *
 */
public class Score {
	/** Current score */
	private int score;
	
	/**
	 * Creates new instance of the score class when initial score of zero.
	 */
	public Score() {
		resetScore();
	}
	
	/**
	 * Increments the score by one.
	 */
	public void increment() {
		score++;
	}
	
	/**
	 * Resets the score to zero.
	 */
	public void resetScore() {
		score = 0;
	}
	
	/**
	 * Returns the current score.
	 * @return Returns score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Draws the score to the screen.
	 * @param g graphics to draw to
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, GamePanel.GRID_SIZE));
		g.drawString("Score: "  + score, 0, GamePanel.GRID_SIZE);
	}
	
	/**
	 * Draws the "Game Over!" to screen.
	 * @param g graphics to draw to
	 */
	public void drawLose(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, GamePanel.GRID_SIZE * 2));
		g.drawString("Game over!", GamePanel.GRID_SIZE * 7, GamePanel.GAME_HEIGHT/2);
	}
	
	/**
	 * Draws the "Press Space to Play" to screen.
	 * @param g graphics to draw to
	 */
	public void drawPlay(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, GamePanel.GRID_SIZE * 2));
		g.drawString("Press Space to Play.", GamePanel.GRID_SIZE * 2, GamePanel.GAME_HEIGHT/2);
	}
	
}
