/*
 * Author : Niranjan A
 * Class description: This is a class to represent a csv object. 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSV {
	String filename, delimiter;
	ArrayList<String[]> csvLines;
	
	CSV(String filename, String delimiter){
		this.filename = filename;
		this.delimiter = delimiter;
		csvLines = new ArrayList<>();
	}
	
	public void readCSV() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while((line = br.readLine())!=null) {
				String[] currLine = line.split(delimiter);
				csvLines.add(currLine);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void printCSV() {
		for(String[] currLine : csvLines) {
			for(String s : currLine) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}
	
	public void writeCSV(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			for(String[] currLine : csvLines) {
				String line = "";
				for(String s : currLine) {
					line += s + ",";
				}
				out.write(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
