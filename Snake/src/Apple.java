import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 
 */

/**
 * Apple class to construct new apple for the snake to eat.
 * @author Heath Dyer
 *
 */
public class Apple extends Rectangle {
	
	/**
	 * Constructs new apple.
	 * @param x x location of apple.
	 * @param y y location of apple.
	 * @param bodySize width of apple.
	 */
	public Apple(int x, int y, int bodySize) {
		super(x, y, bodySize, bodySize);
	}
	
	/**
	 * Draws apple to graphics
	 * @param g graphics to draw to
	 */
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}
}
