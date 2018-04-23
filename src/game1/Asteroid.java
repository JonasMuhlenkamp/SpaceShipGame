package game1;

import java.util.Random;

public class Asteroid extends Sprite {

	public Asteroid(double x, double y) {
		
		super("asteroid.png", x, y);
	
		Random rndm = new Random();
		
		dx = -2 + rndm.nextInt(4); 
		dy = -2 + rndm.nextInt(4);
		
		if(dx == 0 && dy == 0) dx++;
		
	}

	public void bounceDx() {
		
		dx *= -1.1;
	}
	
	public void bounceDy() {
		
		dy *= -1.1;
	}
	
	public boolean hitSprite(Sprite sprite) {
	
		if(sprite.getX() > x && sprite.getX() < x + w) {
			
			if(sprite.getY() > y && sprite.getY() < y + h) {
				
				return true;
			}
			
			if(sprite.getY() + sprite.getHeight() > y && sprite.getY() + sprite.getHeight() < y + h) {
				
				return true;
			}
			
		}
		
		if(sprite.getX() + sprite.getWidth() > x && sprite.getX() + sprite.getWidth() < x + w) {
			

			if(sprite.getY() > y && sprite.getY() < y + h) {
				
				return true;
			}
			
			if(sprite.getY() + sprite.getHeight() > y && sprite.getY() + sprite.getHeight() < y + h) {
				
				return true;
			}
			
		}
		
		return false;
	}
	
}
