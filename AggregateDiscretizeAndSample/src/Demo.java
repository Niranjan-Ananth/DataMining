
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "/home/niran/eclipse-workspace/DataMining/AggregateDiscretizeAndSample/src/Input.csv";
		String outFilename = "/home/niran/eclipse-workspace/DataMining/AggregateDiscretizeAndSample/Output.csv";
		String delimiter = ",";
		CSV csv = new CSV(filename, delimiter);
		csv.readCSV();
		csv.printCSV();
		Aggregate a = new Aggregate(csv);
		csv = a.doAggregate(1);
		csv.printCSV();
		Discretize d = new Discretize(csv);
		csv = d.doDiscretize(1);
		csv.printCSV();
		StratifiedSampling ss = new StratifiedSampling(csv);
		csv = ss.doSample();
		csv.printCSV();
		csv.writeCSV(outFilename);
	}

}
