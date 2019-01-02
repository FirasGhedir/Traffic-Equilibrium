package geneticHeuristic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.SocialOptimum;
import ilog.concert.IloException;
import player.Player;

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
public class GaMINTB {

	Graphs g;
	Population population;
	List<Chromosom> bestsolutions;
	String gamintbResultSet;
	int generation;
	
	public GaMINTB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IloException
	 * 
	 */
	public GaMINTB(Graphs graph, int populationSize, int generation) throws IloException {

		setGraph(graph);
		setBestsolutions(new ArrayList<>());
		setPopulation(new Population(populationSize));
		setGeneration(generation);
		
		solveGAMINTB();
	}

	/**
	 * 
	 * @throws IloException
	 */
	public void solveGAMINTB() throws IloException {

		population.generateChromosomes(getGraph());

		for (int i = 0; i < getGeneration(); i++) {
			population.run(getBestsolutions(), getGraph(), getPopulation());
		}

		Optional<Chromosom> alpha = getBestsolutions().stream().min(Comparator.comparingInt(Chromosom::getEfficiency));
		setGamintbResultSet(alpha.get().getEfficiency() + "\n");
	}

	public Graphs getGraph() {
		return g;
	}

	public void setGraph(Graphs g) {
		this.g = g;
	}

	public Population getPopulation() {
		return this.population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}
	
	public int getGeneration() {
		return this.generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public List<Chromosom> getBestsolutions() {
		return this.bestsolutions;
	}

	public void setBestsolutions(List<Chromosom> bestsolutions) {
		this.bestsolutions = bestsolutions;
	}

	public String getGamintbResultSet() {
		return this.gamintbResultSet;
	}

	public void setGamintbResultSet(String gamintbResultSet) {
		this.gamintbResultSet = gamintbResultSet;
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
	 * The toString() method returns the string representation of the object
	 * CharacteristicsCalculation.
	 */
	@Override
	public String toString() {
		return (printTitle("GaMINTB") + "Best chromsom in generation " +this.getGamintbResultSet());
	}
}
