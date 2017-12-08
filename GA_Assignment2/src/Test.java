import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("input.txt"));
		
		scanner.nextInt();
		
		
		int numOfPoints = scanner.nextInt();
		int equDegree = scanner.nextInt();
		
		Point[] points = new Point[numOfPoints];
		
		for(int i = 0;i < numOfPoints;i++)
			points[i] = new Point(scanner.nextDouble(),scanner.nextDouble());
		
		CurveFitting curveFitter = new CurveFitting(equDegree, numOfPoints, points);
		
		curveFitter.test();
		
	}

}
