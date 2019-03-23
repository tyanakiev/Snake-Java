import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Arrays;

public class GameEngine extends JPanel implements KeyListener, ActionListener{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -9133352428340702530L;
	Snake snake = new Snake();
	Enemy enemy = new Enemy();
	private Random random = new Random();
	private Timer timer;
	
	private int inxpos = random.nextInt(34);
	private int inypos = random.nextInt(23);

	//Counters for moves and length of snake and score.
	private int moves = 0;
	private int lenghtOfSnake = 3;
	private int score = 0;
	private int highScore = 0;

	private boolean gameover = false;
	private boolean enemyVisible = false;
	
	private int delay = 100;
	
	
	public GameEngine() {
		//Registering the key listener.
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		//If this is the start of the game set SnakeX and snakeY length to the following values.
		if(moves==0) {
			snake.ResetSnakePosition();
		}

		
		//Draw border for gameplay
		g.setColor(Color.WHITE);
		g.drawRect(25, 25, 850, 625);
		
		//Draw the background for the gameplay
		g.setColor(Color.BLACK);
		g.fillRect(25, 25, 850, 625);
		
		//Draw the score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN, 14));
		g.drawString("Highest score is: " + highScore, 750, 37);
		
		g.drawString("Score is: " + score, 750, 50);
		
		//Draw the snakes mouth.
		snake.rightMouth = new ImageIcon("rightmouth.png");
		snake.rightMouth.paintIcon(this, g, snake.snakeXLength[0], snake.snakeYLength[0]);
		
		//Changes the image of the snake mouth depending on where is the snake going.
		for(int i = 0; i < lenghtOfSnake; i++) {
			if(i == 0 && snake.right) {
				snake.rightMouth = new ImageIcon("rightmouth.png");
				snake.rightMouth.paintIcon(this, g, snake.snakeXLength[i], snake.snakeYLength[i]);
			}
			if(i == 0 && snake.left) {
				snake.leftMouth = new ImageIcon("lefttmouth.png");
				snake.leftMouth.paintIcon(this, g, snake.snakeXLength[i], snake.snakeYLength[i]);
			}
			if(i == 0 && snake.down) {
				snake.downMouth = new ImageIcon("downmouth.png");
				snake.downMouth.paintIcon(this, g, snake.snakeXLength[i], snake.snakeYLength[i]);
			}
			if(i == 0 && snake.up) {
				snake.upMouth = new ImageIcon("upmouth.png");
				snake.upMouth.paintIcon(this, g, snake.snakeXLength[i], snake.snakeYLength[i]);
			}
			if(i != 0) {
				snake.snakeImage = new ImageIcon("snakeimage.png");
				snake.snakeImage.paintIcon(this, g, snake.snakeXLength[i], snake.snakeYLength[i]);
			}
		}
		
		//Creating the enemy at random position and incrementing the snakes lenght when snakes mouth index is the same as the enemy position.
		if(enemy.enemeyXpos[inxpos] == snake.snakeXLength[0] && enemy.enemeyYpos[inypos] == snake.snakeYLength[0]) {
			lenghtOfSnake++;
			score++;
			enemyVisible = false;
			if(highScore < score) {
				highScore = score;
			}
			
			inxpos = random.nextInt(34);
			inypos = random.nextInt(23);

			repaint();
		}	
		
		//Draw the enemy.
		if(!enemyVisible) {
			//Generates random image for the enemy.
			enemy.enemy = new ImageIcon(enemy.enemyImageMap.get(random.nextInt(5)+1));
			enemyVisible = true;
		}
		enemy.enemy.paintIcon(this, g, enemy.enemeyXpos[inxpos], enemy.enemeyYpos[inypos]);

		
		//Checks if the snake eat its self and then displays a message for end of game and restart.
		for(int i = 1; i < lenghtOfSnake; i++) {
			
			if(snake.snakeXLength[i] == snake.snakeXLength[0] && snake.snakeYLength[i] == snake.snakeYLength[0]) {
				
				snake.right  = false;
				snake.left  = false;
				snake.up  = false;
				snake.down = false;
				gameover = true;
				delay = 100;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game over", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press SPACE to restart", 320, 340);
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();

		//Changes the direction of the snake. Moves all elements of the body of the snake.
		if(snake.right) {
			for(int i=lenghtOfSnake - 1; i >=0; i--) {
				snake.snakeYLength[i+1] = snake.snakeYLength[i];
			}
			for(int i=lenghtOfSnake; i >=0; i--) {
				if(i == 0) {
					//Moves  the image of the snake with 25px to the right
					snake.snakeXLength[i] = snake.snakeXLength[i] + 25;
				}
				else {
					snake.snakeXLength[i] = snake.snakeXLength[i-1];
				}
				if(snake.snakeXLength[i] > 850) {
					snake.snakeXLength[i] = 25;
				}
				
			}
			repaint();
			
		}
		//Snake moving left and its not starting.
		if(snake.left && moves != 0) {
			for(int i=lenghtOfSnake - 1; i >=0; i--) {
				snake.snakeYLength[i+1] = snake.snakeYLength[i];
			}
			for(int i=lenghtOfSnake; i >=0; i--) {
				if(i == 0) {
					//Moves  the image of the snake with 25px to the left
					snake.snakeXLength[i] = snake.snakeXLength[i] - 25;
				}
				else {
					snake.snakeXLength[i] = snake.snakeXLength[i-1];
				}
				if(snake.snakeXLength[i] < 25) {
					snake.snakeXLength[i] = 850;
				}
				
			}
			repaint();	
		}
		//Snake moving up
		if(snake.up) {
			for(int i=lenghtOfSnake - 1; i >=0; i--) {
				snake.snakeXLength[i+1] = snake.snakeXLength[i];
			}
			for(int i=lenghtOfSnake; i >=0; i--) {
				if(i == 0) {
					snake.snakeYLength[i] = snake.snakeYLength[i] - 25;
				}
				else {
					snake.snakeYLength[i] = snake.snakeYLength[i-1];
				}
				if(snake.snakeYLength[i] < 25) {
					snake.snakeYLength[i] = 625;
				}
				
			}
			repaint();
			
		}
		//Snake moving down
		if(snake.down) {
			for(int i=lenghtOfSnake - 1; i >=0; i--) {
				snake.snakeXLength[i+1] = snake.snakeXLength[i];
			}
			for(int i=lenghtOfSnake; i >=0; i--) {
				if(i == 0) {
					snake.snakeYLength[i] = snake.snakeYLength[i] + 25;
				}
				else {
					snake.snakeYLength[i] = snake.snakeYLength[i-1];
				}
				if(snake.snakeYLength[i] > 625) {
					snake.snakeYLength[i] = 25;
				}
			}
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE && gameover) {
			moves=0;
			score=0;
			lenghtOfSnake = 3;
			gameover = false;
			repaint();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && !gameover) {
			moves++;
			snake.right = true;
			if(!snake.left) {
				snake.right = true;
			}
			else {
				snake.right = false;
				snake.left = true;
			}
			snake.up = false;
			snake.down = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT && !gameover && moves !=0) {
			moves++;
			snake.left = true;
			if(!snake.right) {
				snake.left = true;
			}
			else {
				snake.left = false;
				snake.right = true;
			}
			snake.up = false;
			snake.down = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN && !gameover) {
			moves++;
			snake.down = true;
			if(!snake.up) {
				snake.down = true;
			}
			else {
				snake.down = false;
				snake.up = true;
			}
			snake.right = false;
			snake.left = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP && !gameover) {
			moves++;
			snake.up = true;
			if(!snake.down) {
				snake.up = true;
			}
			else {
				snake.up = false;
				snake.down = true;
			}
			snake.right = false;
			snake.left = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
