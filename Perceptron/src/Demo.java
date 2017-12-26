import java.util.Random;

public class Demo {

	public static void main(String[] args) {
		int numberOfRecords = 5;
		int numberOfInputFeatures = 3;
		int[][] trainingData = new int[numberOfRecords][numberOfInputFeatures+1]; //One extra after the numberofInputFeatures, for the output
		double weights[] = new double[numberOfInputFeatures];
		double learningRate = 0.5, bias = 0.4;
		Random r = new Random();
		
		//Generate random training data
		int score;
		for(int i=0; i<numberOfRecords; i++) {
			score = 0;
			for(int j=0; j<numberOfInputFeatures; j++) {
				int x = r.nextInt(2);
				trainingData[i][j] = x;
				if(x==0) {
					score--;
				}
				else {
					score++;
				}
			}
			//If more 1's than 0's, output is 1
			if(score > 0) {
				trainingData[i][numberOfInputFeatures] = 1;
			}
			//Else output is 0
			else {
				trainingData[i][numberOfInputFeatures] = -1;
			}
		}
		
		//Show the training data
		for(int i=0; i<numberOfRecords; i++) {
			for(int j=0; j<numberOfInputFeatures+1; j++) {
				System.out.print(trainingData[i][j] + " ");
			}
			System.out.println();
		}
		
		//Random initial weights
		for(int i=0; i<numberOfInputFeatures; i++) {
			weights[i] = r.nextDouble();
		}
		
		//Array to hold the predicted outputs
		int pred[] = new int[numberOfRecords];
		
		double totalPrevError = 999, totalError = 998;
		while((totalError < totalPrevError) && (totalError!=0)) {
			totalPrevError = totalError;
			totalError = 0;
			for(int i=0; i<numberOfRecords; i++) {
				double a = 0;
				//Find the output of the perceptron
				for(int j=0; j<numberOfInputFeatures; j++) {
					a += weights[j]*trainingData[i][j];
				}
				a -= bias;
				int y1;
				if(a > 0) {
					y1 = 1;
				}
				else {
					y1 = -1;
				}
				pred[i] = y1;
				int y = trainingData[i][numberOfInputFeatures];
				totalError += Math.abs(y-y1);
				
				//Update weights after each input
				for(int j=0; j<numberOfInputFeatures; j++) {
					weights[j] = weights[j] + learningRate*(y-y1)*trainingData[i][j];
				}
				
			}
			
			System.out.print("Predicted values: ");
			for(int i=0; i<numberOfRecords; i++) {
				System.out.print(pred[i] + " ");
			}
			
			System.out.println("Error: " + totalError);
			
		}
		
		System.out.println("Final Error: " + totalError);
		
	}

}
