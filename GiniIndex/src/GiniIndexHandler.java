import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GiniIndexHandler {
	public CSV csv;
	public ArrayList<String[]> csvLines;
	
	public GiniIndexHandler(CSV csv) {
		this.csv = csv;
		this.csvLines = csv.csvLines;
	}
	
	private double calculateGiniIndex(int col) {
		double giniIndexValue = 0;
		int numberOfColumns = csvLines.get(0).length;
		HashMap<String, ArrayList<String[]>> hm = new HashMap<>();
		
		for(String[] currentLine : csvLines) {
			String key = currentLine[col];
			if(hm.containsKey(key)) {
				ArrayList<String[]> arr = hm.get(key);
				arr.add(currentLine);
				hm.put(key, arr);
			}
			else {
				ArrayList<String[]> arr = new ArrayList<>();
				arr.add(currentLine);
				hm.put(key, arr);
			}
		}
		
		ArrayList<Double> nodesGiniIndexValues = new ArrayList<>();
		for(Map.Entry<String, ArrayList<String[]>> m : hm.entrySet()) {
			ArrayList<String[]> currentNode = m.getValue();
			int size = currentNode.size();
			//System.out.println("Size: " + size);
			double numberOfYes=0, numberOfNo=0;
			for(String[] currentLine : currentNode) {
				if(currentLine[numberOfColumns-1].equals("Yes")) {
					numberOfYes++;
				}
				else {
					numberOfNo++;
				}
			}
			//System.out.println("Yes: " + numberOfYes);
			//System.out.println("No: " + numberOfNo);
			//double currentNodeGiniValue = 1 - Math.pow((numberOfYes/size), 2) - Math.pow((numberOfNo/size), 2);
			double currentNodeGiniValue; 
			currentNodeGiniValue = 1 - (numberOfYes*numberOfYes)/(size*size) - (numberOfNo*numberOfNo)/(size*size);
			//System.out.println("Current: " + currentNodeGiniValue);
			currentNodeGiniValue  = currentNodeGiniValue*currentNode.size();
			nodesGiniIndexValues.add(currentNodeGiniValue);
		}
		
		for(Double d : nodesGiniIndexValues) {
			//System.out.println(d);
			giniIndexValue += d/csvLines.size();
		}
		
		return giniIndexValue;
	}
	
	
	
	public void findBestSplit() {
		int numberOfColumns = csvLines.get(0).length;
		int columnToSplit = -1;
		double minGiniIndexValue = 999;
		for(int i=0; i<numberOfColumns-1; i++) {
			double giniValue = calculateGiniIndex(i);
			System.out.println("Column " + i + ": " + giniValue);
			if(giniValue < minGiniIndexValue) {
				minGiniIndexValue = giniValue;
				columnToSplit = i;
			}
		}
		
		System.out.println("Best column to split: " + columnToSplit);
	}
	
}
