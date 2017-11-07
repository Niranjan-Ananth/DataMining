import java.util.ArrayList;
import java.util.Arrays;

public class CandidateGenerator {
	CSV csv;
	public CandidateGenerator(CSV csv) {
		this.csv = csv;
	}
	
	public void generate() {
		int i,j;
		ArrayList<String> a = new ArrayList<>();
		for(i=0; i<csv.csvLines.size(); i++) {
			String[] currLine = csv.csvLines.get(i);
			for(j=i+1; j<csv.csvLines.size(); j++) {
				String[] line = csv.csvLines.get(j);
				if(!currLine[2].equals(line[2])) {
					if((currLine[0].equals(line[0])) && (currLine[1].equals(line[1]))) {
						String s = currLine[0] + currLine[1] + currLine[2] + line[2];
						a.add(s);
					}
				}
			}
		}
		
		System.out.println("Candidate 4 set-----------");
		for(String e : a) {
			System.out.println(e);
		}
	}
	
}
