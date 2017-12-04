import java.util.Random;

public class Demo {

	public static void main(String[] args) {
		int numberOfRecords = 5;
		int trainData[][] = new int[numberOfRecords][5];
		double weights[] = new double[3];
		double bias = 0.4, learningRate = 0.5;
		Random r = new Random();
		//Generating training data
		for(int i=0; i<numberOfRecords; i++) {
			//Random inputs
			int x1 = r.nextInt(2);
			int x2 = r.nextInt(2);
			int x3 = r.nextInt(2);
			int score = 0, y;
			
			//Using score to find the number of 1's in each record of input data.
			if(x1 > 0) 
				score++;
			if(x2 > 0)
				score++;
			if(x3 > 0)
				score++;
			
			//Output is 1, if there are at least two 1's. Else, it's -1.
			if(score>1) {
				y = 1;
			}
			else {
				y = -1;
			}
			
			trainData[i][0] = x1;
			trainData[i][1] = x2;
			trainData[i][2] = x3;
			trainData[i][3] = y;
		}
		
		for(int i=0; i<numberOfRecords; i++) {
			System.out.println(trainData[i][0] + " " + trainData[i][1] + " " + trainData[i][2] + " " + trainData[i][3]);
		}
		
		//Initialize weights to a random double value between 0 and 1.
		weights[0] = r.nextDouble();
		weights[1] = r.nextDouble();
		weights[2] = r.nextDouble();
		
		int pred[] = new int[numberOfRecords];
		
		double totalError = 998;
		double totalPrevError = 999;
		while(totalError < totalPrevError) {
			totalPrevError = totalError;
			totalError = 0;
			for(int i=0; i<numberOfRecords; i++) {
				double a = (weights[0]*trainData[i][0] + weights[1]*trainData[i][1] + weights[2]*trainData[i][2] - bias);
				int y1;
				if(a > 0)
					y1 = 1;
				else
					y1 = -1;
				int y = trainData[i][3];
				pred[i] = y1;
				totalError += Math.abs(y-y1);
				for(int j=0; j<3; j++) {
					weights[j] = weights[j] + learningRate*(y - y1)*trainData[i][j];
					//System.out.print(weights[j] + " ");
				}
				//System.out.println();
			}
			System.out.print("Predicted values: ");
			for(int i=0; i<numberOfRecords; i++) {
				System.out.print(pred[i] + " ");
			}
			System.out.println("Error: " + totalError);
			/*-System.out.print("Weights: ");
			for(int i=0; i<3; i++) {
				System.out.print(weights[i] + " ");
			}
			System.out.println();*/
		}
		
		System.out.println("Final error: " + totalError);
		
	}
}
