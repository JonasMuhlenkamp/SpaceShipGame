package game1;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Sprite {

	private Image image;
	private String path;
		
	protected int w;
	protected int h;
	protected double x;
	protected double y;
	
	protected double dx;
	protected double dy;
	
	public Sprite(String filename, double x, double y) {
		
		this.x = x;
		this.y = y;
		
		path = filename;
		loadImage();
	}

	private void loadImage() {

		//Get the image icon and initialize image to it
		ImageIcon ii = new ImageIcon(path);
		image = ii.getImage();

		//Get the image width and height (null means no other objects are waiting for the image to load)
		w = image.getWidth(null);
		h = image.getHeight(null);
	}

	public void move() {

		x += dx;
		y += dy;   
	}
	
	//Simple getter methods that return important attributes of the sprite
	public double getX() {

		return x;
	}

	public double getY() {

		return y;
	}

	public int getWidth() {

		return w;
	}

	public int getHeight() {

		return h;
	}    

	public Image getImage() {

		return image;
	}
	
}
