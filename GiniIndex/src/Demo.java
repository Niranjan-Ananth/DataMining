
public class Demo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "/home/niran/eclipse-workspace/DataMining/GiniIndex/Input.csv";
		String outFilename = "/home/niran/eclipse-workspace/DataMining/AggregateDiscretizeAndSample/Output.csv";
		String delimiter = ",";
		CSV csv = new CSV(filename, delimiter);
		csv.readCSV();
		csv.printCSV();
		GiniIndex g = new GiniIndex(csv);
		String ans = g.findBestAttributeToSplit();
		System.out.println(ans);
	}

}
