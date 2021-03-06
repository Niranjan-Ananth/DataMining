import java.util.HashMap;
import java.util.Map;

public class MissingValuesHandler {
	CSV csv;

	public MissingValuesHandler(CSV csv) {
		this.csv = csv;
	}

	public void replaceNumericValues(int col) {
		double total = 0;
		int number = 0;
		for (String[] currLine : csv.csvLines) {
			if (!currLine[col].equals("-")) {
				total += Double.parseDouble(currLine[col]);
				number++;
			}
		}
		double mean = total / number;
		for (String[] currLine : csv.csvLines) {
			if (currLine[col].equals("-")) {
				currLine[col] = Double.toString(mean);
			}
		}
	}

	public void replaceStringValues(int col) {
		HashMap<String, Integer> hm = new HashMap<>();
		for (String[] currLine : csv.csvLines) {
			if (!currLine[col].equals("-")) {
				if (hm.containsKey(currLine[col])) {
					hm.put(currLine[col], hm.get(currLine[col]) + 1);
				} else {
					hm.put(currLine[col], 1);
				}
			}
		}
		int max = 0;
		String value = "";
		for (Map.Entry<String, Integer> m : hm.entrySet()) {
			if (m.getValue() > max) {
				max = m.getValue();
				value = m.getKey();
			}
		}
		
		for(String[] currLine : csv.csvLines) {
			if(currLine[col].equals("-")) {
				currLine[col] = value;
			}
		}

	}

	public CSV replaceMissingValues() {
		System.out.println("ReplaceMissingValues------------------");
		int numCols = csv.csvLines.get(0).length;
		for (int i = 0; i < numCols; i++) {
			for (String[] currLine : csv.csvLines) {
				try {
					Double value = Double.parseDouble(currLine[i]);
					replaceNumericValues(i);
				} catch (Exception e) {
					String value = currLine[i];
					replaceStringValues(i);
				}
			}
		}
		System.out.println("------------------------------------");
		return csv;
	}
}
