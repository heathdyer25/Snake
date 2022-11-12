import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * 
 */

/**
 * Instance of Snake class to construct a new snake and control its movements.
 * @author Heath Dyer
 *
 */
public class Snake {
	/** Array list that stores the body parts of the snake */
	public ArrayList<SnakeBody> body;
	/** X direction of the snake. */
	int xDirection = 1;
	/** Y direction of the snake. */
	int yDirection = 0;
	
	/**
	 * Constructs a new Snake with only 1 body part for the head.
	 */
	public Snake() {
		body = new ArrayList<SnakeBody>();
		body.add(new SnakeBody(0, 0, GamePanel.GRID_SIZE));
	}
	
	/**
	 * Moves the snake in the direction its headed to. Does so by removing the snake body part at the tail and inserting at the head in front of the previous head.
	 */
	public void move() {
		body.get(body.size() - 1).x = body.get(0).x + (xDirection * GamePanel.GRID_SIZE);
		body.get(body.size() - 1).y = body.get(0).y + (yDirection * GamePanel.GRID_SIZE);
		SnakeBody temp = body.remove(body.size() - 1);
		body.add(0, temp);
	}
	
	/**
	 * Returns the head of the snake.
	 * @return Returns the ehad of the snake.
	 */
	public SnakeBody getHead() {
		return body.get(0);
	}
	
	/**
	 * Returns the tail of the snake.
	 * @return Returns the tail of the snake.
	 */
	public SnakeBody getTail() {
		return body.get(body.size() - 1);
	}
	
	/**
	 * Grows the snake by one body part. Adds the body part to the end of the snake.
	 */
	public void growSize() {
		body.add(new SnakeBody(body.get(body.size() - 1).x, body.get(body.size() - 1).y, GamePanel.GRID_SIZE));
	}
	
	/**
	 * Draws the snake to the screen.
	 * @param g graphics to draw to.
	 */
	public void draw(Graphics g) {
		for (SnakeBody part : body) {
			part.draw(g);
		}
	}
	
	/**
	 * Controls the snake when a key is pressed. Controlled by WASD. W moves the snake up, S down, A left, and D right. Snake can only move left or right in relation
	 * to its orientation.
	 * @param e KeyEvent to put action to.
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W && yDirection != 1) {
			xDirection = 0;
			yDirection = -1;
		}
		if (e.getKeyCode() == KeyEvent.VK_S && yDirection != -1) {
			xDirection = 0;
			yDirection = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_A && xDirection != 1) {
			xDirection = -1;
			yDirection = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_D && xDirection != -1) {
			xDirection = 1;
			yDirection = 0;
		}
	}
	
	
	
	
}
