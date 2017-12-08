import java.util.Random;

public class Chromosome {
	
	int size;
	double[] genes;
	double lower = -10;
	double upper = 10;
	
	public Chromosome(){
		
	}
	
	public Chromosome(int n){
		this.size = n;
		this.genes = new double[n];
		
		Random randomGenerator = new Random();
		
		for(int i =0;i < n;i++)
			genes[i] = lower + (upper -lower) * randomGenerator.nextDouble();
	}
	
	public Chromosome(Chromosome other){
		this.size = other.size;
		this.genes = new double[size];
		
		for(int i =0;i < size;i++)
			this.genes[i] = other.genes[i];
	}
	
	public void print(){
		System.out.print("Chromosome : ");
		for (int i = 0; i < genes.length; i++) {
			System.out.print(genes[i]);
		}
		System.out.println("");
	}
	
}
