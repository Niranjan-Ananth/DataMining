
public class Point {
	public int x;
	public int y;
	public int type;
	public double distance; //Distance represents the distance from the point to be classified.
	public Point(int x, int y, int type, double distance) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.distance = distance;
	}
	public double calculateDistance(Point p) {
		double distance = 0.0;
		double sqdist = Math.pow(this.x - p.x, 2)+Math.pow(this.y - p.y, 2);
		distance = Math.sqrt(sqdist);
		return distance;
	}
	
	public double getDistanceFromPoint() {
		return distance;
	}
}
