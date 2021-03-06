import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StratifiedSampling {
	CSV csv;
	public StratifiedSampling(CSV csv) {
		this.csv = csv;
	}
	
	public CSV doSample() {
		System.out.println("Sampling----------------------------");
		HashMap<String, Integer> sizeMap = new HashMap<>();
		HashMap<String, ArrayList<String[]>> listMap = new HashMap<>();
		for(String[] currLine : csv.csvLines) {
			String type = currLine[0];
			if(sizeMap.containsKey(type)) {
				sizeMap.put(type, sizeMap.get(type)+1);
			}
			else {
				sizeMap.put(type, 1);
			}
			
			if(listMap.containsKey(type)) {
				ArrayList<String[]> arr = listMap.get(type);
				arr.add(currLine);
				listMap.put(type, arr);
			}
			else {
				ArrayList<String[]> arr = new ArrayList<>();
				arr.add(currLine);
				listMap.put(type, arr);
			}
		}
		
		int min = 99999;
		for(Map.Entry<String, Integer> m : sizeMap.entrySet()) {
			if(m.getValue() < min) {
				min = m.getValue();
			}
		}
		
		csv.csvLines = new ArrayList<>();
		for(Map.Entry<String, ArrayList<String[]>> m : listMap.entrySet()) {
			ArrayList<String[]> arr = m.getValue();
			for(int i=0; i<min; i++) {
				csv.csvLines.add(arr.get(i));
			}
		}
		System.out.println("---------------------------------");	
		return csv;
	}
}
