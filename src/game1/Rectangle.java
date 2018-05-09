package game1;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {

	private int width;
	private int height;
	private double x;
	private double y;

	public Rectangle(double xCoor, double yCoor, int w, int h) {

		setX(xCoor);
		setY(yCoor);
		setWidth(w);
		setHeight(h);
	}

	//Checks a point to see if it is inside the rectangle
	public boolean inRectangle(Point point) {
		
		if(point.getX() > x && point.getX() < x + width) 

			if(point.getY() > y && point.getY() < y + height) 

				return true;
		
		return false;

	}

	//Wrapper for above method that takes a sprite as a parameter
	public boolean inRectangle(Sprite sprite) {

		List<Point> pointList = sprite.getImageRectangle().generatePointList();
 
		for(int i = 0; i < pointList.size(); i++)

			if(inRectangle(pointList.get(i))) 

				return true;
		
		return false;
	}

	//Creates an ArrayList of the four corners and the center 
	public List<Point> generatePointList() {

		List<Point> pointList = new ArrayList<Point> ();

		pointList.add(getCenter());
		pointList.add(getUpperLeft());
		pointList.add(getUpperRight());
		pointList.add(getLowerLeft());
		pointList.add(getLowerRight());
		
		return pointList;
	}

	//Simple setter methods
	public void setX(double xCoor) {

		x = xCoor;
	}

	public void setY(double yCoor) {

		y = yCoor;		
	}

	public void setHeight(int h) {

		if(h < 1)

			throw new IllegalArgumentException("Height cannot be negative or zero. It is " + h);

		height = h;
	}

	public void setWidth(int w) {

		if(w < 1)

			throw new IllegalArgumentException("Width cannot be negative or zero. It is " + w);

		width = w;
	}

	//Simple getter methods
	public int getHeight() {

		return height;
	}

	public int getWidth() {

		return width;
	}

	public double getArea() {

		return height * width;
	}

	public double getPerimeter() {

		return height * 2 + width * 2; 
	}

	public double getX() {

		return x;
	}

	public double getY() {

		return y;
	}

	//These methods return the four corners of the rectangle, as well as the center in point form
	public Point getCenter() {

		return new Point(x + width / 2.0, y + height / 2.0);
	}

	public Point getUpperLeft() {

		return new Point(x, y);
	}

	public Point getUpperRight() {

		return new Point(x + height, y);
	}

	public Point getLowerLeft() {

		return new Point(x, y + width);
	}

	public Point getLowerRight() {

		return new Point(x + height, y + width);
	}

}
