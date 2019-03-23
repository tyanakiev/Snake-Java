import javax.swing.ImageIcon;

public class Snake {
	
	//Image of the snake and enemy.
	public ImageIcon rightMouth;
	public ImageIcon leftMouth;
	public ImageIcon upMouth;
	public ImageIcon downMouth;
	public ImageIcon snakeImage;
	
	//Boolean variables to know where the snake is moving
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	
	//Arrays that  hold the snake position. 
	public int[] snakeXLength = new int[1000];
	public int[] snakeYLength = new int[1000];
	
	//Starting coordinates for the snake.
	public void ResetSnakePosition() {
		this.snakeXLength[2] = 50;
		this.snakeXLength[1] = 75;
		this.snakeXLength[0] = 100;
		
		this.snakeYLength[2] = 100;
		this.snakeYLength[1] = 100;
		this.snakeYLength[0] = 100;
	}

}
