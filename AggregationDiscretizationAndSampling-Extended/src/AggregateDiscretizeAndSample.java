import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AggregateDiscretizeAndSample {
	
	public ArrayList<String[]> csvLines;
	public CSV csv;
	
	public AggregateDiscretizeAndSample(CSV csv) {
		this.csv = csv;
		this.csvLines = csv.csvLines;
	}
	
	
	//Aggregation
	public void Aggregate() {	
		System.out.println("Enter the column number you want to aggregate");
		Scanner s = new Scanner(System.in);
		int columnNumber = s.nextInt();
		int numberOfRecords = 0;
		double total = 0, min = 999, max = -1;
		try {
			double trial = Double.parseDouble(csvLines.get(0)[columnNumber]);
			for(int i=0; i<csvLines.size(); i++) {
				String currentLine[] = csvLines.get(i);
				if(!currentLine[columnNumber].equals("-")) {
					double value = Double.parseDouble(currentLine[columnNumber]);
					total += value;
					numberOfRecords++;
					if(value < min) {
						min = value;
					}
					if(value > max) {
						max = value;
					}
				}			
			}
			System.out.println("Maximum value: " + max);
			System.out.println("Minimium value: " + min);
			double average = (total)/numberOfRecords;
			System.out.println("Average: " + average);
		}catch(Exception e) {
			System.out.println("That column cannot be aggregated");
		}
	}
	
	
	//Discretization
	public ArrayList<String[]> diescretize() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the column number you want to discretize");
		int columnNumber = s.nextInt();
		int numberOfDivisions;
		double min = 999, max = -1;
		System.out.println("Enter the number of divisions");
		numberOfDivisions = s.nextInt();
		try{
			double trial = Double.parseDouble(csvLines.get(0)[columnNumber]);
			for(int i=0; i<csvLines.size(); i++) {
				String currentLine[] = csvLines.get(i);
				if(!currentLine[columnNumber].equals("-")) {
					double value = Double.parseDouble(currentLine[columnNumber]);
					if(value < min) {
						min = value;
					}
					if(value > max) {
						max = value;
					}
				}
			}
			
			double divisionValues[] = new double[numberOfDivisions];
			divisionValues[0] = min;
			divisionValues[numberOfDivisions-1] = max;
			
			for(int i=1; i<numberOfDivisions-1; i++) {
				divisionValues[i] = divisionValues[i-1] + (max-min)/numberOfDivisions;
			}
			
			for(int i=0; i<csvLines.size(); i++) {
				String currentLine[] = csvLines.get(i);
				if(!currentLine[columnNumber].equals("-")) {
					double value = Double.parseDouble(currentLine[columnNumber]);
					System.out.print(value + " ");
					int clas = 0;
					for(int j=0; j<numberOfDivisions; j++) {
						if(value <= divisionValues[j]) {
							clas = j;
							break;
						}
					}
				
					System.out.println("Phase" + clas);
					currentLine[columnNumber] = "Stage" + clas;
					
				}
			}
			
			
		}catch(Exception e) {
			System.out.println("That column cannot be discretized");
		}
		
		return csvLines;
	}
	
	
	//Sampling
	public ArrayList<String[]> Sample() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the column number based on which you want to sample");
		int columnNumber = s.nextInt();
		HashMap<String, Integer> sizeMap = new HashMap<>();
		HashMap<String, ArrayList<String[]>> listMap = new HashMap<>();
		
		for(String[] currentLine : csvLines) {
			String type = currentLine[columnNumber];
			if(sizeMap.containsKey(type)) {
				sizeMap.put(type, sizeMap.get(type)+1);
			}
			else {
				sizeMap.put(type, 1);
			}
		}
		
		for(String[] currentLine : csvLines) {
			String type = currentLine[columnNumber];
			if(listMap.containsKey(type)) {
				ArrayList<String[]> arr = listMap.get(type);
				arr.add(currentLine);
				listMap.put(type, arr);
			}
			else {
				ArrayList<String[]> arr = new ArrayList<>();
				arr.add(currentLine);
				listMap.put(type, arr);
			}
		}
		
		int min = 999;
		for(Map.Entry<String, Integer> m : sizeMap.entrySet()) {
			if(m.getValue() < min) {
				min = m.getValue();
			}
		}
		
		csvLines = new ArrayList<>();
		for(Map.Entry<String, ArrayList<String[]>> m : listMap.entrySet()) {
			ArrayList<String[]> arr = m.getValue();
			for(int i=0; i<min; i++) {
				csvLines.add(arr.get(i));
			}
		}
		
		return csvLines;
	}
	
}	
