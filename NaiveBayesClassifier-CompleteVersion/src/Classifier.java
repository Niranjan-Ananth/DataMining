import java.util.ArrayList;
import java.util.Scanner;

public class Classifier {
	CSV csv;
	ArrayList<String[]> csvLines;
	String[] inputLine;
	public Classifier(CSV csv) {
		this.csv = csv;
		this.csvLines = csv.csvLines;
	}
	
	
	public double getProbForNumericalAttrbute(int col, String outputClass,  double numberInTheClass) {
		System.out.println("------------------------------");
		double prob=1, mean, variance=0, sum = 0, standardDeviation;
		int numberOfColumns = csvLines.get(0).length;
		for(String[] currentLine : csvLines) {
			if(currentLine[numberOfColumns-1].equals(outputClass)) {
				sum += Double.parseDouble(currentLine[col]);
			}
		}
		
		mean = sum/numberInTheClass;
		
		for(String[] currentLine : csvLines) {
			if(currentLine[numberOfColumns-1].equals(outputClass)) {
				double value = Double.parseDouble(currentLine[col]);
				variance += Math.pow((value-mean), 2);
			}
		}
		
		variance = variance/(numberInTheClass);
		standardDeviation = Math.sqrt(variance);
		
		double val = Double.parseDouble(inputLine[col]);
		double a = Math.pow((val-mean), 2);
		a = a/(2*variance);
		
		prob = Math.exp(-a);
		prob = prob/(Math.sqrt(2*Math.PI)*standardDeviation);
		
		System.out.println("Mean: " + mean);
		System.out.println("variance: " + variance);
		
		return prob;
	}
	
	public double getProbForCategoricalAttribute(int col, String outputClass, double numberInTheClass) {
		double prob=0, numberOfMatches=0;
		int numberOfColumns = csvLines.get(0).length;
		for(String[] currentLine : csvLines) {
			if(currentLine[numberOfColumns-1].equals(outputClass)) {
				if(currentLine[col].equals(inputLine[col])) {
					numberOfMatches++;
				}
			}
		}
		prob = numberOfMatches/numberInTheClass;
		return prob;
	}
	
	
	public void classify() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the details of the data whose output is to be predicted");
		int numberOfColumns = csvLines.get(0).length; 
		inputLine = new String[numberOfColumns];
		for(int i=0; i<numberOfColumns-1; i++) {
			try {
				double value = Double.parseDouble(csvLines.get(0)[i]);
				double input = s.nextDouble();
				inputLine[i] = Double.toString(input);
			}catch(Exception e) {
				String input = s.next();
				inputLine[i] = input;
			}
		}
		
		double conditionalProbOfTrue = 1, conditionalProbOfFalse = 1;
		double evidenceTrue, evidenceFalse;
		double numbertrue=0, numberFalse=0;
		for(String[] currentLine : csvLines) {
			if(currentLine[numberOfColumns-1].equals("True")) {
				numbertrue++;
			}
			else {
				numberFalse++;
			}
		}
		
		evidenceTrue = numbertrue/csvLines.size();
		evidenceFalse = numberFalse/csvLines.size();
		
		System.out.println("Evidence True: " + evidenceTrue);
		System.out.println("Evidence False: " + evidenceFalse);
		
		for(int i=0; i<numberOfColumns-1; i++) {
			double trueProbForColumn, falseProbForColumn;
			try {
				double value = Double.parseDouble(csvLines.get(0)[i]);
				trueProbForColumn = getProbForNumericalAttrbute(i, "True", numbertrue);
				falseProbForColumn = getProbForNumericalAttrbute(i, "False", numberFalse);
			}catch(Exception e) {
				trueProbForColumn = getProbForCategoricalAttribute(i, "True", numbertrue);
				falseProbForColumn = getProbForCategoricalAttribute(i, "False", numberFalse);
			}
			System.out.println("True prob fro column " + i + ": " + trueProbForColumn);
			System.out.println("False prob fro column " + i + ": " + falseProbForColumn);
			conditionalProbOfTrue *= trueProbForColumn;
			conditionalProbOfFalse *= falseProbForColumn;
		}
		
		double truePosteriorProb = conditionalProbOfTrue*evidenceTrue;
		double falsePosteriorProb = conditionalProbOfFalse*evidenceFalse;
		
		System.out.println();
		System.out.println("Posterior True probability : " + truePosteriorProb);
		System.out.println("Posterior False probability: " + falsePosteriorProb);
		
		if(truePosteriorProb > falsePosteriorProb) {
			System.out.println("Classification: True");
		}
		else {
			System.out.println("Classification:  False");
		}
	}
}
