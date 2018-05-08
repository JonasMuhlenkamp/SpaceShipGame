package game1;

public class Point {

	private double x;
	private double y;
	
	public Point(double xCoor, double yCoor) {
		
		setX(xCoor);
		setY(yCoor);
	}
	
	public Point(String xyForm) {
		
		xyForm = xyForm.trim();
		
		int commaIdx = xyForm.indexOf(',');
		
		setX(Double.valueOf(xyForm.substring(1, commaIdx)));
		setY(Double.valueOf(xyForm.substring(commaIdx + 1, xyForm.length() - 1)));
	}
	
	public double getX() {
		
		return x;	
	}
	
	public double getY() {
	
		return y;
	}
	
	public void setX(double xCoor) {
		
		x = xCoor;
	}
	
	public void setY(double yCoor) {
		
		y = yCoor;		
	}
	
	public String toString() {
		
		return "(" + x + ", " + y + ")";		
	}
	
	public double distanceToPoint(Point point) {
		
		//Use distance formula to calculate the distance between two points
		double distance = Math.sqrt((x - point.getX()) * (x - point.getX()) + (y - point.getY()) * (y - point.getY()));
		
		return distance;
	}
		
}
