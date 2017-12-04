import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {

	public static ArrayList<Point> getInput(String inputFile) {
		ArrayList<Point> trainingData = new ArrayList<>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)));
			while ((line = br.readLine()) != null) {
				String[] currLine = line.split(",");
				double x = Double.parseDouble(currLine[0]);
				double y = Double.parseDouble(currLine[1]);
				Point p = new Point(x, y);
				trainingData.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trainingData;
	}
	
	public static void main(String[] args) {
		String inputFile = "/home/niran/eclipse-workspace/DataMining/LinearRegressionEasy/src/input.csv";
		ArrayList<Point> trainingData = getInput(inputFile);
		double meanX=0, meanY=0, varianceX=0, covarianceXY=0;
		double W1=0, W0=0;
		int numberOfRecords= trainingData.size();
		for(int i=0; i<numberOfRecords; i++) {
			meanX += trainingData.get(i).x;
			meanY += trainingData.get(i).y;
		}
		meanX = meanX/numberOfRecords;
		meanY = meanY/numberOfRecords;
		
		for(int i=0; i<numberOfRecords; i++) {
			Point p = trainingData.get(i);
			varianceX += Math.pow(p.x-meanX, 2);	
			covarianceXY += (p.x-meanX) * (p.y-meanY);
		}
		
		varianceX = varianceX/numberOfRecords;
		covarianceXY = covarianceXY/(numberOfRecords);
		
		W1 = covarianceXY/varianceX;
		W0 = meanY - W1*meanX;
		
		System.out.println("Enter the x value of the point who's value is to be predicted: ");
		Scanner s = new Scanner(System.in);
		double x = s.nextDouble();
		
		double y = x*W1 + W0;
		System.out.println("Predicted Output is: " + y);
	}

}
