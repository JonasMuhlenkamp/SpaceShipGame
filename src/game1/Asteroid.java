package game1;

import java.util.Random;

public class Asteroid extends Sprite {
	
	public Random rndm = new Random();
	
	public Asteroid(double x, double y) {
		
		super("boulder.png", x, y);
	
		dx = -2 + rndm.nextInt(4); 
		dy = -2 + rndm.nextInt(4);
		
		if(dx == 0 && dy == 0) dx++;
		
	}

	//These need fixing to bounce in a more random way
	public void bounceDx() {
		
		dx *= -1.1;
	}
	
	public void bounceDy() {
		
		dy *= -1.1;
	}
	
}
