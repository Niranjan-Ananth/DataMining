import java.util.HashMap;
import java.util.Map;

public class GiniIndex {
	CSV csv;
	public GiniIndex(CSV csv) {
		this.csv = csv;
	}
	
	public String findBestAttributeToSplit() {
		System.out.println("-------------------------------------");
		String attr = "";
		double currentGini = 99;
		int size = csv.csvLines.get(0).length;
		for(int i=0; i<csv.csvLines.get(0).length-1; i++) {
			System.out.println(i + " " + currentGini);
			HashMap<String, Integer> hmYes = new HashMap<>();
			HashMap<String, Integer> hmNo = new HashMap<>();
			int y = 0;
			for(String[] currLine : csv.csvLines) {
				//System.out.println("Length: " + currLine.length);
				String key = currLine[i];
				if(currLine[currLine.length-1].equals("yes")) {
					if(hmYes.containsKey(key)) { 
						hmYes.put(key, hmYes.get(key)+1);
					}
					else {
						hmYes.put(key, 1);
					}
				}
				
				else {
					if(hmNo.containsKey(key)) { 
						hmNo.put(key, hmNo.get(key)+1);
					}
					else {
						hmNo.put(key, 1);
					}
				}
			}
			
			for(Map.Entry<String, Integer> m : hmYes.entrySet()) {
				System.out.println(m.getKey() + " " + m.getValue());
			}
			System.out.println();
			
			/*for(Map.Entry<String, Integer> m : hmNo.entrySet()) {
				System.out.println(m.getKey() + " " + m.getValue());
			}
			System.out.println();*/
			
			double giniIndex[] = new double[2];
			double t[] = new double[2];
			int ind=0;
			double yes, no, total;
			for(Map.Entry<String, Integer> m : hmYes.entrySet()) {
				yes = m.getValue();
				if(hmNo.get(m.getKey())!=null) {
					no = hmNo.get(m.getKey());
				}
				else {
					no = 0;
				}
				total = yes + no;
				giniIndex[ind] = 1 - (yes*yes)/(total*total) - (no*no)/(total*total);
				System.out.println("EE" + ind + " " + yes + " " + no + " " + giniIndex[ind]);
				t[ind] = yes + no;
				ind++;
			}
			double finalGini = (t[0]*giniIndex[0] + t[1]*giniIndex[1])/(t[0] + t[1]);
			//System.out.println("gini " + giniIndex[0] + " " + giniIndex[1]);
			if(finalGini < currentGini) {
				currentGini = finalGini;
				attr = "Attribute" + (i+1);
			}
		}
		
		return attr;
	}
}
