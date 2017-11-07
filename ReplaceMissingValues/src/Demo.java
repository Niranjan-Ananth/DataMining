
public class Demo {

	public static void main(String[] args) {
		String filename = "/home/niran/eclipse-workspace/DataMining/ReplaceMissingValues/src/Input.csv";
		String delimiter = ",";
		CSV csv = new CSV(filename, delimiter);
		csv.readCSV();
		csv.printCSV();	
		MissingValuesHandler m = new MissingValuesHandler(csv);
		csv = m.replaceMissingValues();
		csv.printCSV();
	}
}
