import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		double tp, tn, fp, fn;
		Scanner s = new Scanner(System.in);
		tp = s.nextDouble();
		fn = s.nextDouble();
		fp = s.nextDouble();
		tn = s.nextDouble();
		System.out.println(tp + " " + fn + " " + fp + " " + tn);
		double sensitivity = tp/(tp+fn);
		double specificity = tn/(tn+fp);
		double precision = tp/(tp+fp);
		double recall = sensitivity;
		int w1 = 2;
		int w2 = 1;
		int w3 = 1;
		int w4 = 2;
		double weightedAccuracy = (w1*tp + w4*tn)/(w1*tp + w2*fn + w3*fp + w4*tn);
		
		System.out.println("Sensitivity: " + sensitivity);
		System.out.println("Specificity: " + specificity);
		System.out.println("Precision: " + precision);
		System.out.println("Recall: " + recall);
		System.out.println("WeightedAccuracy: " + weightedAccuracy);
	}

}
