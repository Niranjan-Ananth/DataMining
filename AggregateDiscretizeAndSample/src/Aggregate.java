
public class Aggregate {
	CSV csv;
	public Aggregate(CSV csv) {
		this.csv = csv;
	}
	
	public CSV doAggregate(int col) {
		System.out.println("Aggregating-----------------------------------");
		double total = 0;
		for(String[] currLine : csv.csvLines) {
			total += Double.parseDouble(currLine[col]);
		}
		double mean = total/csv.csvLines.size();
		System.out.println("Mean of the attribute: " + mean);
		System.out.println("--------------------------------------------");
		return csv;
	}
}
