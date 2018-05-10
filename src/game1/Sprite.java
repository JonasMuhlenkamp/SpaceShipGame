package game1;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Sprite {

	private Image image;
		
	protected int w;
	protected int h;
	protected double x;
	protected double y;
	
	protected double dx;
	protected double dy;
	
	protected Rectangle imageRect;
	
	public Sprite(String filename, double x, double y) {
		
		this.x = x;
		this.y = y;
		
		loadImage(filename);
	}
	
	public Sprite(String filename, Point center) {
		
		loadImage(filename);
		
		x = center.getX() - w / 2.0;
		y = center.getY() - h / 2.0; 
	}

	private void loadImage(String path) {

		//Get the image icon and initialize image to it
		ImageIcon ii = new ImageIcon(path);
		image = ii.getImage();

		//Get the image width and height (null means no other objects are waiting for the image to load)
		w = image.getWidth(null);
		h = image.getHeight(null);
		
		imageRect = new Rectangle(x, y, w, h);
	}

	public void move() {

		x += dx;
		y += dy;
		
		imageRect.setX(x + dx);
		imageRect.setY(y + dy);
	}
	
	public boolean hitSprite(Sprite sprite) {
		
		return getImageRectangle().inRectangle(sprite);
	}
	
	public void destroy() {
		
		image = null;
		x = Double.NaN;
		y = Double.NaN;
	}
	
	//Simple getter methods that return important attributes of the sprite
	public double getX() {

		return imageRect.getX();
	}

	public double getY() {

		return imageRect.getY();
	}

	public int getWidth() {

		return imageRect.getWidth();
	}

	public int getHeight() {

		return imageRect.getHeight();
	}    

	public Image getImage() {

		return image;
	}
	
	public Rectangle getImageRectangle() {
		
		return imageRect;
	}
	
}
