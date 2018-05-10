package game1;

public class Spaceship extends Sprite {

	//Should be around 1-4 for practical purposes
	private final double SPEED_MULTIPLIER = 1.75;
	
	public Spaceship(double x, double y) {

		super("spaceship.png", x, y);
		
	}
	
	public void calcDxDy(double mouseX, double mouseY) {

		//If we are hovering over the center of the sprite, we stop moving
		if((mouseX < (x + (w / 2.0) + 10) && mouseX > (x + (w / 2.0) - 10)) && (mouseY < (y + (h / 2.0) + 10) && mouseY > (y + (h / 2.0) - 10))) {		

			dx = 0;
			dy = 0;
			
		} else {

			//Otherwise, we use vectors to calculate dx and dy
			dx = SPEED_MULTIPLIER * (mouseX - (x + w / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
			dy = SPEED_MULTIPLIER * (mouseY - (y + h / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
		}
	
	}
	
	public void hit() {
		
		System.out.println("Oh no, you've been hit!");
	}
	
}
