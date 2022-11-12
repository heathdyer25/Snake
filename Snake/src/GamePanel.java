import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Game panel the game runs in. Provides functionality to play.
 * @author Heath Dyer
 *
 */
public class GamePanel extends JPanel implements ActionListener {
	/** Width of the game */
	public static final int GAME_WIDTH = 500;
	/** Height of the game */
	public static final int GAME_HEIGHT = GAME_WIDTH;
	/** Length of one block on the snake grid */
	public static final int GRID_SIZE = GAME_WIDTH/25;
	/** Dimension of the screen */
	private static Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	/** Initial delay to create timer with */
	static final int DELAY = 175;
	
	/** Timer to update the game at */
	Timer timer;
	/** Image of game to paint graphics to */
	Image image;
	/** Graphics to paint to */
	Graphics graphics;
	/** Whether the game is running */
	boolean running = false;
	/** Whether the game has been lost */
	boolean lost =  false;
	/** Instance of random */
	Random random;
	/** Current instance of Snake */
	Snake snake;
	/** Current instance of apple */
	Apple apple;
	/** Instance of score */
	Score score;
	
	/**
	 * Constructs new GamePanel
	 */
	public GamePanel() {
		super();
		this.setFocusable(true);
		this.setPreferredSize(SCREEN_SIZE);
		this.addKeyListener(new KA());
		newSnake();
		score = new Score();
		
	}
	
	/**
	 * Draws elements in the game
	 * @param g graphics to draw to.
	 */
	public void draw(Graphics g) {
		if (!running && !lost)  {
			score.drawPlay(g);
		} else if (!running && lost) {
			score.drawLose(g);
		} else {
			apple.draw(g);
		}
		snake.draw(g);
		score.draw(g);
	}
	
	/**
	 * Moves the elements on the screen.
	 */
	public void move() {
		snake.move();
	}
	
	/**
	 * Creates a new snake
	 */
	public void newSnake() {
		snake = new Snake();
	}
	
	/**
	 * Creates a new apple at a location where the snake is not.
	 */
	public void newApple() {
		random = new Random();
		int x;
		int y;
		while (true) {
			x = random.nextInt(20) * GRID_SIZE;
			y = random.nextInt(20) * GRID_SIZE;
			for (SnakeBody part : snake.body) {
				if (part.x == x && part.y == y) {
					continue;
				}
			}
			break;
		}
		apple = new Apple(x, y, GRID_SIZE);
	}
	
	
	@Override
	public void paint(Graphics g) {
		image = createImage(GAME_WIDTH, GAME_HEIGHT);
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	
	/**
	 * Starts the game by create a timer, apple, and starts running the game.
	 */
	public void startGame() {
		running = true;
		lost = false;
		timer = new Timer(DELAY, this);
		timer.start();
		newApple();
	}
	
	/**
	 * Resets the game after the game has been lost. Resets timer, snake, and score.
	 */
	public void resetGame() {
		running = true;
		lost = false;
		timer.setDelay(DELAY);
		newApple();
		newSnake();
		score.resetScore();
	}

	/**
	 * Checks the collisions in the game. This includes the snake going off screen, the snake getting the apple, and the snake running into itself.
	 */
	public void checkCollision() {
		//checks snake runs off screen
		if (snake.getHead().x < 0 || snake.getHead().x > GAME_WIDTH || snake.getHead().y < 0 || snake.getHead().y > GAME_HEIGHT) {
			//lose operations
			running = false;
			lost = true;
		}
		//get apple
		if (snake.getHead().intersects(apple)) {
			score.increment();
			snake.growSize();
			timer.setDelay(DELAY - 2 * score.getScore());
			newApple();
		}
		
		//snake collides with self
		if (snake.body.size() > 2 && snake.getTail().x == snake.body.get(snake.body.size() - 2).x && snake.getTail().y == snake.body.get(snake.body.size() - 2).y) {
			for (int idx = 1; idx < snake.body.size() - 1; ++idx) {
				if (snake.body.get(idx).intersects(snake.getHead())) {
					//lose operations
					running = false;
					lost = true;
				}
			}
		} 
		else if (snake.body.size() == 2 && snake.getTail().x == snake.getHead().x && snake.getTail().y == snake.getHead().y) {
			//do nothing for 1 frame;
		}
		else {
			for (int idx = 1; idx < snake.body.size(); ++idx) {
				if (snake.body.get(idx).intersects(snake.getHead())) {
					//lose operations
					running = false;
					lost = true;
				}
			}
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkCollision();
			repaint();
		} else {
			repaint();
		}
	}
	
	/**
	 * Key adapter to control snake.
	 * @author Heath Dyer
	 *
	 */
	private class KA extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			snake.keyPressed(e);
			
			if (!running && !lost && e.getKeyCode() == KeyEvent.VK_SPACE) {
				startGame();
			}
			if (!running && lost && e.getKeyCode() == KeyEvent.VK_SPACE) {
				resetGame();
			}
			
		}
		
		
	}

}
