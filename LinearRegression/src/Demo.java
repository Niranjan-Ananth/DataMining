
public class Demo {

	public static void main(String[] args) {
		int numberOfRecords = 3;
		int trainingData[][] = new int[numberOfRecords][2];
		for (int i = 0; i < numberOfRecords; i++) {

		}
		trainingData[0][0] = 1;
		trainingData[0][1] = 1;
		trainingData[1][0] = 2;
		trainingData[1][1] = 2;
		trainingData[2][0] = 3;
		trainingData[2][1] = 4;

		double w1 = 0, w0 = 0, y1, y, x0 = 1, x1;
		double learningRate = 0.3;
		int k = 1;
		double totalError = 1000, meanSquaredError;
		while (k < 10) {
			totalError = 0;
			for (int i = 0; i < numberOfRecords; i++) {
				y = trainingData[i][1];
				x1 = trainingData[i][0];
				y1 = w1 * x1;
				totalError += (y1 - y)*(y1 - y);
				w0 = w0 + learningRate * (y1 - y) * x0;
				w1 = w1 + learningRate * (y1 - y) * x1;
				//System.out.println("Total error: " + totalError);
			}
			meanSquaredError = totalError / (2 * numberOfRecords);
			System.out.println("Mean squared error in epoch " + k + " = " + totalError);
			k++;
		}
	}

}
