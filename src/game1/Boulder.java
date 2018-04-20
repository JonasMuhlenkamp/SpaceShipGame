package game1;

import java.util.Random;

public class Boulder extends Sprite {

	public Boulder(double x, double y) {
		
		super("boulder.png", x, y);
	
		Random rndm = new Random();
		
		dx = -2 + rndm.nextInt(4); 
		dy = -2 + rndm.nextInt(4);
		
		if(dx == 0) dx++;
		if(dy == 0) dy++;
		
	}

	private void loadImage() {
		
		
	}
	
	public void bounceDx() {
		
		dx *= -1.1;
	}
	
	public void bounceDy() {
		
		dy *= -1.1;
	}
	
}
