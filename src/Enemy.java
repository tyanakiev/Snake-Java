import java.util.HashMap;
import javax.swing.ImageIcon;

public class Enemy {
	
	public ImageIcon enemy;
	
	// X and Y coordinates for the enemy.
	public int[] enemeyXpos = {25, 50,75, 100, 125, 150, 175, 200, 225, 250, 275,
			300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625,
			650, 675, 700, 725, 750, 775, 800, 825, 850};

	public int[] enemeyYpos = {50, 75, 100, 125, 150, 175, 200, 225, 250, 275,
			300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};
	
	public HashMap<Integer, String> enemyImageMap = new HashMap<Integer, String>()
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
	        put(1, "enemy.png");
	        put(2, "enemy1.png");
	        put(3, "enemy2.png");
	        put(4, "enemy3.png");
	        put(5, "enemy4.png");
	    }
	};

}
