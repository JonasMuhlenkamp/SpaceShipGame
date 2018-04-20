package game1;

public class Missile extends Sprite {

	public Missile(double x, double y, double mouseX, double mouseY) {

		super("missile.png", x, y);

		//Missile speed is always 3; direction is always wherever the mouse was when missile was fired
		dx = 3.5 * (mouseX - (x + w / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
		dy = 3.5 * (mouseY - (y + h / 2.0)) / Math.sqrt((mouseX - (x + w / 2.0)) * (mouseX - (x + w / 2.0)) + (mouseY - (y + h / 2.0)) * (mouseY - (y + h / 2.0)));
	}

}
