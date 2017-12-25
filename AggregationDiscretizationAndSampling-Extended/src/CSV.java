import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSV {
	public String filename, delimiter;
	public ArrayList<String[]> csvLines;
	
	public CSV(String filename, String delimiter) {
		this.filename = filename;
		this.delimiter = delimiter;
		csvLines = new ArrayList<>();
	}
	
	public void readCSV() {
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
			while((line = br.readLine())!=null) {
				String[] currentLine = line.split(delimiter);
				csvLines.add(currentLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void printCSV() {
		for(String[] currentLine : csvLines) {
			for(String s : currentLine) {
				System.out.print(s + ", ");
			}
			System.out.println();
		}
	}
	
}
