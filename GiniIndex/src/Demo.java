//Works for Binary and multi-way splitting of nominal attributes only.
//The output classes are "Yes" and "No" only.

public class Demo {

	public static void main(String[] args) {
		String filename = "/home/niran/eclipse-workspace/DataMining2/GiniIndex/src/input.csv";
		String delimiter = ",";
		CSV csv = new CSV(filename, delimiter);
		csv.readCSV();
		csv.printCSV();
		GiniIndexHandler gh = new GiniIndexHandler(csv);
		gh.findBestSplit();
	}

}
