import java.util.ArrayList;
import java.util.Random;

public class CurveFitting {

	int numOfCoffients;
	int numOfPoints;
	Point[] points;

	int maxGeneration;
	int currentGeneration;
	int chromoSize;

	ArrayList<Chromosome> population;
	ArrayList<Chromosome> newPopulation;
	ArrayList<Double> fitnessValues;

	double totalFitness;

	Random randomGenerator;
	double mutationProb;
	double crossoverProb;

	public CurveFitting() {

	}

	public CurveFitting(int degree, int n, Point[] points) {
		this.numOfCoffients = degree + 1;
		this.numOfPoints = n;
		this.points = new Point[n];

		for (int i = 0; i < numOfPoints; i++)
			this.points[i] = points[i];

		chromoSize = numOfCoffients;

		population = new ArrayList<>();
		newPopulation = new ArrayList<>();
		fitnessValues = new ArrayList<>();
		
		randomGenerator = new Random();
		crossoverProb = 0.7;
		mutationProb = 0.01;
		
		currentGeneration = 0;
		maxGeneration = 5000;
	}

	public Chromosome getSolution() {
		intializePopulation();
		evalutatePopFitness();

		int indexOfBest = getBestOfPopulation();
		population.add(population.get(indexOfBest));
		fitnessValues.add(fitnessValues.get(indexOfBest));

		for (int i = 0; i < maxGeneration; i++) {
			currentGeneration = i+1;
			for (int j = 0; j < population.size() / 2; j++) {
				int index1 = select();
				int index2 = select();

				Chromosome chromosome1 = new Chromosome(population.get(index1));
				Chromosome chromosome2 = new Chromosome(population.get(index2));

				crossover(chromosome1, chromosome2);
				mutate(chromosome1);
				mutate(chromosome2);

				newPopulation.add(chromosome1);
				newPopulation.add(chromosome2);
			}

			Chromosome currentBest = new Chromosome(population.get(population.size() - 1));
			double currentBestFitness = fitnessValues.get(fitnessValues.size()-1);
			
			population = new ArrayList<>(newPopulation);
			newPopulation.clear();

			evalutatePopFitness();
			
			indexOfBest = getBestOfPopulation();
			Chromosome newBest = new Chromosome(population.get(indexOfBest));
			double newBestFitness = fitnessValues.get(indexOfBest);

			if (currentBestFitness < newBestFitness) {
				population.add(newBest);
				fitnessValues.add(newBestFitness);
			} else {
				population.add(currentBest);
				fitnessValues.add(currentBestFitness);
			}

		}

		return population.get(population.size() - 1);
	}

	public void intializePopulation() {
		
		int populationSize = 500;
		for (int i = 0; i < populationSize; i++) {
			Chromosome chromosome = new Chromosome(chromoSize);
			population.add(chromosome);
		}
	}
        
        public double calculate_y_expected(double x , Chromosome chromosome){
            double y_expected = 0;
            for(int i=0 ; i<chromoSize ; i++){
                y_expected+=chromosome.genes[i]*Math.pow(x, i);
            }
            
            return y_expected;
        }

	public double getFitness(Chromosome chromosome) {
            double sum=0 ;
            for(int i=0 ; i<points.length; i++ ){
                sum+=Math.pow(points[i].y - calculate_y_expected(points[i].x, chromosome) , 2);
            }
            
            double fitness = sum/points.length;
            return fitness;
	}

	public void evalutatePopFitness() {
		fitnessValues.clear();
		totalFitness = 0;
		for (int i = 0; i < population.size(); i++) {
			double fitnessValue = getFitness(population.get(i));
			totalFitness += fitnessValue;
			fitnessValues.add(fitnessValue);
		}
	}

	public int select() {
		
		double randomNumber = totalFitness * randomGenerator.nextDouble();
		double startFrom = 0;
		for (int i = 0; i < population.size(); i++) {
			double endAt = startFrom + fitnessValues.get(i);
			if (randomNumber >= startFrom && randomNumber < endAt) {
				return i;
			}
			startFrom = endAt;
		}

		return -1;
	}

	public void crossover(Chromosome chromosome1, Chromosome chromosome2) {
		double randomNumber = randomGenerator.nextDouble();
		if (randomNumber <= crossoverProb) {
			int randomIndex = randomGenerator.nextInt(chromoSize);
			if (randomIndex == 0)
				randomIndex = 1;
			for (int i = randomIndex; i < chromoSize; i++) {
				double tmp = chromosome1.genes[i];
				chromosome1.genes[i] = chromosome2.genes[i];
				chromosome2.genes[i] = tmp;
			}
		}
	}

	public void mutate(Chromosome chromosome) {
		//TODO: MAMDOUH
		/*
		 * Parameters needed are defined in code like this:
		 *** t ==> currentGeneration 
		 *** T ==> maxGeneration
		 *** lower bound of xi ==> chromosome.lower
		 *** upper bound of xi ==> chromosome.upper
		 *
		 *For testing purposes you can set or change 
		 *current and max generation values here locally in function
		 */
	}

	public int getBestOfPopulation() {
		int maxIndex = 0;
		for (int i = 1; i < population.size(); i++) {
			if (fitnessValues.get(maxIndex) < fitnessValues.get(i))
				maxIndex = i;
		}
		return maxIndex;
	}

	public void test() {
		
	}
}
