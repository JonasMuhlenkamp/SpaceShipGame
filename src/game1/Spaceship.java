package game1;

public class Spaceship extends MovingSprite {

	//Should be around 1-4 for practical purposes
	private final double SPEED_MULTIPLIER = 2;
	
	public Spaceship(String filename, double x, double y) {

		super(filename, x, y);
		
		
	}
	
	public void calcDxDy(double mouseX, double mouseY) {

		//If we are hovering over the center of the sprite, we stop moving
		if((mouseX < (x + (w / 2.0) + 5) && mouseX > (x + (w / 2.0) - 5)) && (mouseY < (y + (h / 2.0) + 5) && mouseY > (y + (h / 2.0) - 5))) {		

			dx = 0;
			dy = 0;
			
		} else {

			//Otherwise, we use vectors to calculate dx and dy
			dx = SPEED_MULTIPLIER * (mouseX - (x + w / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
			dy = SPEED_MULTIPLIER * (mouseY - (y + h / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
		}
	
	}
	
}
