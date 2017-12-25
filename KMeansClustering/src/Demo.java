import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class Demo {
	
	public static ArrayList<Point> getInput(String inputFile) {
		ArrayList<Point> trainingData = new ArrayList<>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)));
			while ((line = br.readLine()) != null) {
				String[] currLine = line.split(",");
				int x = Integer.parseInt(currLine[0]);
				int y = Integer.parseInt(currLine[1]);
				Point p = new Point(x, y, -1);
				trainingData.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trainingData;
	}

	public static double calcDistance(Point a, Point b) {
		double distance = 0;
		distance = Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2);
		distance = Math.sqrt(distance);
		return distance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputFile = "/home/niran/eclipse-workspace/DataMining/KMeansClustering/src/input.csv";
		Random r = new Random();
		ArrayList<Point> trainingData = getInput(inputFile);
		int numberOfRecords = trainingData.size();
		int k = 3;
		Point centroids[] = new Point[k];
		double meansX[] = new double[k];
		double meansY[] = new double[k];
		double num[] = new double[k];
		for (int i = 0; i < k; i++) {
			num[i] = 0;
		}
		// Initially taking random centroids
		for (int i = 0; i < k; i++) {
			Point p = new Point(r.nextInt(11), r.nextInt(11), i);
			centroids[i] = p;
		}

		int numberOfChanges = 0;
		int iter=0;
		do {
			iter++;
			for (int i = 0; i < k; i++) {
				num[i] = 0;
			}
			System.out.println("Number: " + numberOfChanges);
			System.out.println("Centroids: ");
			for (int i = 0; i < k; i++) {
				System.out.println(centroids[i].x + " " + centroids[i].y);
			}
			numberOfChanges = 0;
			for (int i = 0; i < numberOfRecords; i++) {
				Point p = trainingData.get(i);
				double distance = 999;
				boolean changed = false;
				for (int j = 0; j < k; j++) {
					double dis = calcDistance(p, centroids[j]);
					if (dis < distance) {
						distance = dis;
						if (p.cluster != j) {
							changed = true;
						}
						p.cluster = j;
						// System.out.print(j + " ");
					}
				}
				if(changed)
					numberOfChanges++;
			}

			for (int i = 0; i < numberOfRecords; i++) {
				Point p = trainingData.get(i);
				meansX[p.cluster] += p.x;
				meansY[p.cluster] += p.y;
				num[p.cluster]++;
			}

			for (int i = 0; i < k; i++) {
				if (num[i] != 0) {
					meansX[i] = meansX[i] / num[i];
					meansY[i] = meansY[i] / num[i];
					centroids[i].x = meansX[i];
					centroids[i].y = meansY[i];
				}
				
			}

		} while (iter < 10);

		for (int i = 0; i < numberOfRecords; i++) {
			System.out.print(trainingData.get(i).cluster + " ");
		}
		System.out.println();

		for (int i = 0; i < k; i++) {
			System.out.print(num[i] + " ");
		}
	}

}
