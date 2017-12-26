
public class Demo {

	public static void main(String[] args) {
		String filename, delimiter;
		filename = "/home/niran/eclipse-workspace/DataMining2/NaiveBayesClassifier/src/input.csv";
		delimiter = ",";
		CSV csv = new CSV(filename, delimiter);
		csv.readCSV();
		csv.printCSV();
		Classifier c = new Classifier(csv);
		c.classify();
	}

}
