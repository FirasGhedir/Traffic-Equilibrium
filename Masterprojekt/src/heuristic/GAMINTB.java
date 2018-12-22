package heuristic;

import java.util.Random;

import graphModel.Graphs;

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          Solver for the Genetic Algorithm Minimnum Tollbooth Problem
 *          (GAMINTB).
 * 
 *          Steps:
 * 
 *          1) Initial population
 * 
 *          2) Fitness function
 * 
 *          3) Selection
 * 
 *          4) Crossover
 * 
 *          5) Mutation
 */
public class GAMINTB {

	Graphs graph;
	Population population = new Population();
	Chromosome fittest;
	Chromosome secondFittest;
	int generationCount = 0;
	String message;

	/**
	 * 
	 */
	public GAMINTB(Graphs graph) {
		this.graph = graph;

		// Initialize population
		this.population.initializePopulation(100, graph.getVertices().size());
		// Calculate fitness of each Chromosome
		this.population.calculateFitness();

		this.message = "Generation: " + this.generationCount + " Fittest: " + this.population.fittest + "\n";

		solveGAMINTB();
	}

	/**
	 * 
	 */
	public void solveGAMINTB() {

		Random rn = new Random();

		// While population gets an Chromosome with maximum fitness
		while (this.population.fittest < this.graph.getVertices().size()) {
			++this.generationCount;
			// Do selection
			this.selection();
			// Do crossover
			this.crossover();
			// Do mutation under a random probability
			if (rn.nextInt() % 7 < 5) {
				this.mutation();
			}
			// Add fittest offspring to population
			this.addFittestOffspring();
			// Calculate new fitness value
			this.population.calculateFitness();

			this.message += "Generation: " + this.generationCount + " Fittest: " + this.population.fittest + "\n";
		}

		this.message += "\nSolution found in generation " + this.generationCount + "\n";
		this.message += "Fitness: " + this.population.getFittest().fitness + "\n";
		this.message += "Genes: ";
		for (int i = 0; i < this.getGraph().getVertices().size(); i++) {
			this.message += this.population.getFittest().genes[i];
		}
	}

	/**
	 * Selection
	 */
	void selection() {

		// Select the most fittest Chromosome
		fittest = population.getFittest();

		// Select the second most fittest Chromosome
		secondFittest = population.getSecondFittest();
	}

	/**
	 * Crossover
	 */
	void crossover() {
		Random rn = new Random();

		// Select a random crossover point
		int crossOverPoint = rn.nextInt(population.Chromosomes[0].geneLength);

		// Swap values among parents
		for (int i = 0; i < crossOverPoint; i++) {
			int temp = fittest.genes[i];
			fittest.genes[i] = secondFittest.genes[i];
			secondFittest.genes[i] = temp;
		}
	}

	/**
	 * Mutation
	 */
	void mutation() {
		Random rn = new Random();

		// Select a random mutation point
		int mutationPoint = rn.nextInt(population.Chromosomes[0].geneLength);

		// Flip values at the mutation point
		if (fittest.genes[mutationPoint] == 0) {
			fittest.genes[mutationPoint] = 1;
		} else {
			fittest.genes[mutationPoint] = 0;
		}

		mutationPoint = rn.nextInt(population.Chromosomes[0].geneLength);

		if (secondFittest.genes[mutationPoint] == 0) {
			secondFittest.genes[mutationPoint] = 1;
		} else {
			secondFittest.genes[mutationPoint] = 0;
		}
	}

	/**
	 * Get fittest offspring
	 * 
	 * @return
	 */
	Chromosome getFittestOffspring() {
		if (fittest.fitness > secondFittest.fitness) {
			return fittest;
		}
		return secondFittest;
	}

	/**
	 * Replace least fittest Chromosome from most fittest offspring
	 */
	void addFittestOffspring() {

		// Update fitness values of offspring
		fittest.calcFitness();
		secondFittest.calcFitness();

		// Get index of least fit Chromosome
		int leastFittestIndex = population.getLeastFittestIndex();

		// Replace least fittest Chromosome from most fittest offspring
		population.Chromosomes[leastFittestIndex] = getFittestOffspring();
	}

	/**
	 * Getter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @return the given graph
	 */
	public Graphs getGraph() {
		return this.graph;
	}

	/**
	 * Setter method for the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param g the given graph
	 */
	public void setGraph(Graphs g) {
		this.graph = g;
	}

	/**
	 * Prints a title in a fancy frame on the console
	 * 
	 * --------------------------------------------
	 * 
	 * @param title the title to print
	 */
	private static String printTitle(String title) {
		return ("\n ==============================\n|     " + title + ":\n ==============================\n");
	}

	/**
	 * The toString() method returns the string representation of the object GAMINTB
	 */
	@Override
	public String toString() {
		return (printTitle("GAMINTB") + this.message);
	}

}

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          Class for Chromosomes
 */
class Chromosome {

	int fitness = 0;
	int[] genes;
	int geneLength;

	/**
	 * @param chromsomeSize
	 * 
	 */
	public Chromosome(int chromsomeSize) {

		this.geneLength = chromsomeSize;
		this.genes = new int[geneLength];

		Random rn = new Random();

		// Set genes randomly for each Chromosome
		for (int i = 0; i < genes.length; i++) {
			genes[i] = Math.abs(rn.nextInt() % 2);
		}

		fitness = 0;
	}

	/**
	 * Calculate fitness
	 */
	public void calcFitness() {

		fitness = 0;
		for (int i = 0; i < this.geneLength; i++) {
			if (genes[i] == 1) {
				++fitness;
			}
		}
	}

}

/**
 * Universität Ulm
 * 
 * Projekt Algorithm Engineering-Projekt --- WiSe 2018/19
 * 
 * @author Firas Ghedir (firas.ghedir@uni-ulm.de)
 * @author Julian Bestler (julian.bestler@uni-ulm.de)
 * 
 * @version 1.0
 * 
 *          _____________________________________________
 * 
 *          Class for Population
 */
class Population {

	int popSize;
	Chromosome[] Chromosomes;
	int fittest = 0;

	/**
	 * Initialize population
	 * 
	 * @param size
	 */
	public void initializePopulation(int popSize, int chromsomeSize) {

		this.popSize = popSize;
		this.Chromosomes = new Chromosome[popSize];

		for (int i = 0; i < Chromosomes.length; i++) {
			Chromosomes[i] = new Chromosome(chromsomeSize);
		}
	}

	/**
	 * Get the fittest Chromosome
	 * 
	 * @return
	 */
	public Chromosome getFittest() {
		int maxFit = Integer.MIN_VALUE;
		int maxFitIndex = 0;
		for (int i = 0; i < Chromosomes.length; i++) {
			if (maxFit <= Chromosomes[i].fitness) {
				maxFit = Chromosomes[i].fitness;
				maxFitIndex = i;
			}
		}
		fittest = Chromosomes[maxFitIndex].fitness;
		return Chromosomes[maxFitIndex];
	}

	/**
	 * Get the second most fittest Chromosome
	 * 
	 * @return
	 */
	public Chromosome getSecondFittest() {
		int maxFit1 = 0;
		int maxFit2 = 0;
		for (int i = 0; i < Chromosomes.length; i++) {
			if (Chromosomes[i].fitness > Chromosomes[maxFit1].fitness) {
				maxFit2 = maxFit1;
				maxFit1 = i;
			} else if (Chromosomes[i].fitness > Chromosomes[maxFit2].fitness) {
				maxFit2 = i;
			}
		}
		return Chromosomes[maxFit2];
	}

	/**
	 * Get index of least fittest Chromosome
	 * 
	 * @return
	 */
	public int getLeastFittestIndex() {
		int minFitVal = Integer.MAX_VALUE;
		int minFitIndex = 0;
		for (int i = 0; i < Chromosomes.length; i++) {
			if (minFitVal >= Chromosomes[i].fitness) {
				minFitVal = Chromosomes[i].fitness;
				minFitIndex = i;
			}
		}
		return minFitIndex;
	}

	/**
	 * Calculate fitness of each Chromosome
	 */
	public void calculateFitness() {

		for (int i = 0; i < Chromosomes.length; i++) {
			Chromosomes[i].calcFitness();
		}
		getFittest();
	}
}
