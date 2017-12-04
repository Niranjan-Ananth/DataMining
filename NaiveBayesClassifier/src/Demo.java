import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo {

	public static ArrayList<DataPoint> getInput(String inputFile){
		ArrayList<DataPoint> trainingData = new ArrayList<>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)));
			while((line = br.readLine())!=null) {
				String[] currLine = line.split(",");
				int x1 = Integer.parseInt(currLine[0]);
				int x2 = Integer.parseInt(currLine[1]);
				int x3 = Integer.parseInt(currLine[2]);
				boolean y = false;
				if(x3 > 0)
					y = true;
				DataPoint dp = new DataPoint(x1, x2, y);
				trainingData.add(dp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trainingData;
	}
	
	public static void main(String[] args) {
		int numberOfRecords;
		ArrayList<DataPoint> trainingData = new ArrayList<>();
		String inputFile = "/home/niran/eclipse-workspace/DataMining/NaiveBayesClassifier/src/input.csv";
		trainingData = getInput(inputFile);	
		System.out.println("Enter the test datapoint");
		Scanner s = new Scanner(System.in);
		DataPoint dataToBePredicted = new DataPoint(s.nextInt(), s.nextInt(), s.nextBoolean());
		System.out.println("Data: " + dataToBePredicted.x1 + " " + dataToBePredicted.x2 + " " + dataToBePredicted.y);
		numberOfRecords = trainingData.size();
		double posteriorProbabilityTrue, posteriorProbabilityFalse, probOfTrue, probOfFalse, evidenceTrue, evidenceFalse;
		double numberTrue=0, numberFalse=0;
		for(int i=0; i<numberOfRecords; i++) {
			if(trainingData.get(i).y) {
				numberTrue++;
			}
			else {
				numberFalse++;
			}
		}
		probOfTrue = numberTrue/numberOfRecords;
		probOfFalse = numberFalse/numberOfRecords;
		System.out.println("True Prob: " + probOfTrue);
		System.out.println("False prob: " + probOfFalse);
		
		double numberX1=0, numberX2=0, numberX1F=0, numberX2F=0;
		for(int i=0; i<numberOfRecords; i++) {
			DataPoint d = trainingData.get(i);
			if(d.y) {
				if(d.x1 == dataToBePredicted.x1) {
					numberX1++;
				}
				if(d.x2==dataToBePredicted.x2) {
					numberX2++;
				}
			}
			else {
				if(d.x1 == dataToBePredicted.x1) {
					numberX1F++;
				}
				if(d.x2==dataToBePredicted.x2) {
					numberX2F++;
				}
			}
		}
		
		evidenceTrue = (numberX1/numberTrue) * (numberX2/numberTrue);
		evidenceFalse = (numberX1F/numberFalse) * (numberX2F/numberFalse);
		
		posteriorProbabilityTrue = probOfTrue*evidenceTrue;
		posteriorProbabilityFalse = probOfFalse*evidenceFalse;
		
		System.out.println("Probs: " + posteriorProbabilityTrue + " " + posteriorProbabilityFalse);
		
		if(posteriorProbabilityTrue > posteriorProbabilityFalse) {
			dataToBePredicted.y = true;
			System.out.println("Predicted class: True, " + posteriorProbabilityTrue);
		}
		else {
			dataToBePredicted.y = false;
			System.out.println("Predicted class: False, " + posteriorProbabilityFalse);
		}
	}

}
