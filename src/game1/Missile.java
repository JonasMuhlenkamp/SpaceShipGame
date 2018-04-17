package game1;

public class Missile extends MovingSprite {

	public Missile(String filename, double x, double y, double mouseX, double mouseY) {

		super(filename, x, y);

		dx = 3 * (mouseX - (x + w / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
		dy = 3 * (mouseY - (y + h / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
	}

}
