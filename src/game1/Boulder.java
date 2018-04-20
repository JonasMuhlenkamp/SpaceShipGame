package game1;

import java.util.Random;

public class Boulder extends Sprite {

	public Boulder(double x, double y) {
		
		super("boulder.png", x, y);
	
		Random rndm = new Random();
		
		dx = -2 + rndm.nextInt(4); 
		dy = -2 + rndm.nextInt(4);
	}

	private void loadImage() {
		
		
	}
	
	
	
}
