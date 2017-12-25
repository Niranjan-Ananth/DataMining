
public class Demo {

	public static void main(String[] args) {
		String filename, delimiter;
		filename = "/home/niran/eclipse-workspace/DataMining2/AggregationDiscretizationAndSampling/src/input.csv";
		delimiter = ",";
		CSV csv = new CSV(filename, delimiter);
		csv.readCSV();
		csv.printCSV();
		AggregateDiscretizeAndSample ads = new AggregateDiscretizeAndSample(csv);
		ads.Aggregate();
		csv.csvLines = ads.diescretize();
		csv.printCSV();
		csv.csvLines = ads.Sample();
		csv.printCSV();
	}

}
