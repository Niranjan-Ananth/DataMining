import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Demo {

	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<>();
		Random r = new Random();
		int numberOfPoints = 10;
		int k=5;
		//Point kNearestNeighbours[] = new Point[10];
		System.out.println("The points generated are: ");
		ArrayList<Point> kNearestNeighbours = new ArrayList<>();
		for(int i=0; i<numberOfPoints; i++) {
			int x = r.nextInt(100);
			int y = r.nextInt(100);
			int type = r.nextInt(2);
			Point p = new Point(x, y, type, 999);
			points.add(p);
			System.out.println(x + " " + y + " " + type);
		}
		Point pointToBeClassified = new Point(r.nextInt(100), r.nextInt(100), -1, 0);
		System.out.println("The point to be classified is: " + pointToBeClassified.x + " " + pointToBeClassified.y);
		for(int i=0; i<numberOfPoints; i++) {
			points.get(i).distance = pointToBeClassified.calculateDistance(points.get(i));
		}
		
		/*for(int i=0; i<numberOfPoints; i++) {
			Point p = points.get(i);
			System.out.println(p.x + " " + p.y + " " + p.type + " " + p.distance);
		}
		System.out.println("---------------------");*/
		Collections.sort(points, new PointsComaparator());

		/*for(int i=0; i<numberOfPoints; i++) {
			Point p = points.get(i);
			System.out.println(p.x + " " + p.y + " " + p.type + " " + p.distance);
		}
		System.out.println("---------------------");*/
		
		System.out.println("The k-Nearest Neighbours are: ");
		int score = 0;
		for(int i=0; i<k; i++) {
			Point p = points.get(i);
			System.out.println(p.x + " " + p.y + " " + p.type + " " + p.distance);
			if(p.type==0)
				score--;
			else
				score++;
		}
		
		
		if(score > 0) {
			pointToBeClassified.type = 1;
			System.out.println("Type of the Point to be classified: Type 1");
		}
		else {
			pointToBeClassified.type = 0;
			System.out.println("Type of the Point to be classified: Type 0");
		}
		
	}

}
