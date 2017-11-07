
public class Discretize {
	CSV csv;
	public Discretize(CSV csv) {
		this.csv = csv;
	}
	
	public CSV doDiscretize(int col) {
		System.out.println("Discretizing-----------------------------------");
		int numberOfPartitions = 3;
		double max = 0, min = 99999;
		for(String[] currLine : csv.csvLines) {
			double value = Double.parseDouble(currLine[col]);
			if(max < value) {
				max = value;
			}
			if(value < min) {
				min = value;
			}
		}
		
		double part1 = (double)(max-min)/3.0;
		double part2 = 2*part1;
		
		System.out.println("Min: " + min);
		System.out.println("Max: " + max);
		System.out.println("Mean1: " + part1);
		System.out.println("Mean2: " + part2);
		
		for(String[] currLine : csv.csvLines) {
			double value = Double.parseDouble(currLine[col]);
			if(value < part1) {
				currLine[col] = "Stage1";
			}
			else if(value >= part1 && value<=part2){
				currLine[col] = "Stage2";
			}
			else {
				currLine[col] = "Stage3";
			}
		}
		System.out.println("---------------------------------------------------");
		return csv;
	}
}
