import java.util.Collections;
import java.util.Comparator;

public class Demo {

	public static void main(String[] args) {
		String filename = "/home/niran/eclipse-workspace/DataMining/Apriori/src/Input.csv";
		String outFilename = "/home/niran/eclipse-workspace/DataMining/AggregateDiscretizeAndSample/Output.csv";
		String delimiter = ",";
		CSV csv = new CSV(filename, delimiter);
		
		csv.readCSV();
		csv.printCSV();
		
		Collections.sort(csv.csvLines, new Comparator<String[]>() {

			@Override
			public int compare(String[] arg0, String[] arg1) {
				for(int i=0; i<arg0.length; i++) {
					if(arg0[i].compareTo(arg1[i])!=0) {
						return arg0[i].compareTo(arg1[i]);
					}
				}
				return 0;
			}
			
		});
		System.out.println("-----------------------------------------");
		csv.printCSV();
		
		CandidateGenerator g = new CandidateGenerator(csv);
		g.generate();
	}

}
