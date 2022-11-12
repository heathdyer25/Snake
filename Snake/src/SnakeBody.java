import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Constrcuts one body part of the snake.
 * @author Heath Dyer
 *
 */
public class SnakeBody extends Rectangle {

	/**
	 * Constructs a new snake body part.
	 * @param x x position of the snake body.
	 * @param y y position of the snake body.
	 * @param bodySize Length of one square of the snakes body.
	 */
	public SnakeBody(int x, int y, int bodySize) {
		super(x, y, bodySize, bodySize);
	}
	
	/**
	 * Draws the snake body to the graphics
	 * @param g graphics to draw to.
	 */
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}
}
