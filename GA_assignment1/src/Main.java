import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("input.txt"));
		
		int numOfTestCases = scanner.nextInt();
		int numOfPoints;
		int equDegree;
		Point[] points;
		
		double min_error;
		
		for(int i = 0;i < numOfTestCases;i++)
		{
			numOfPoints = scanner.nextInt();
			equDegree = scanner.nextInt();
			
			points = new Point[numOfPoints];
			
			for(int j = 0;j < numOfPoints;j++)
				points[j] = new Point(scanner.nextDouble(),scanner.nextDouble());
			
			CurveFitting curveFitter = new CurveFitting(equDegree, numOfPoints, points);
			
			Chromosome bestSolution = curveFitter.getSolution();
			min_error = curveFitter.getFitness(bestSolution);
			
			System.out.println("Case " + (i+1));
			for (int j = 0; j < bestSolution.size; j++) {
				System.out.print(bestSolution.genes[j] + " ");
			}
			System.out.println(min_error);
			
		}
		
		
	}

}
