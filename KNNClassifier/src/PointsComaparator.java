import java.util.Comparator;

public class PointsComaparator implements Comparator<Point>{

	@Override
	public int compare(Point o1, Point o2) {
		// TODO Auto-generated method stub
		return (int) Math.ceil(o1.getDistanceFromPoint() - o2.getDistanceFromPoint());
	}
	
}