package geneticHeuristic;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import graphModel.Graphs;
import ilog.concert.IloException;
import ilog.cplex.IloCplex;

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
public class Population {

	int size;
	List<Chromosom> y;
	List<Chromosom> afterranking;
	List<Chromosom> parents;
	List<Chromosom> children;
	List<Chromosom> migration;
	Random r = new Random();
	private String populationResultSet;
	ByteArrayOutputStream stream = new ByteArrayOutputStream();
	String cplexSolverOutputStream;
	IloCplex cplex;

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @param size the population size
	 * @throws IloException if a CPLEX error occures
	 */
	public Population(int size) throws IloException {

		setSize(size);
		setY(new ArrayList<>());
		setAfterranking(new ArrayList<>());
		setParents(new ArrayList<>());
		setChildren(new ArrayList<>());
		setMigration(new ArrayList<>());

		cplex = new IloCplex();
		cplex.setOut(stream);

	}

	/**
	 * Generates new chromosomes out of the graph
	 * 
	 * --------------------------------------------
	 * 
	 * @param g the given graph
	 */
	public void generateChromosomes(Graphs g) {

		for (int i = 0; i < getSize(); i++) {
			y.add(new Chromosom(g.getEdges().size()));
			for (int j = 0; j < g.getEdges().size(); j++) {

				double randomValue = 0 + (1 - 0) * r.nextDouble();
				int x = (int) (randomValue + 0.5);
				if (x == 0) {
					y.get(i).vector[j] = false;
				} else {
					y.get(i).vector[j] = true;
				}
			}
		}
	}

	/**
	 * Stores the efficiency
	 */
	public void saveEtnicitiy() {
		for (int i = 0; i < getY().size(); i++) {
			getY().get(i).calculateEfficiency();
		}
	}

	/**
	 * Stores the rank for each chromosome
	 */
	public void saveRank() {

		List<Chromosom> solvable = new ArrayList<>();
		List<Chromosom> notsolvable = new ArrayList<>();

		for (int i = 0; i < getY().size(); i++) {
			getY().get(i).calculateEfficiency();
			if (getY().get(i).isFeasible()) {
				solvable.add(getY().get(i));
			} else {
				notsolvable.add(getY().get(i));
			}
		}
		solvable.sort(Comparator.comparing(Chromosom::getEfficiency));
		notsolvable.sort(Comparator.comparing(Chromosom::getEfficiency));

		setAfterranking(solvable);
		getAfterranking().addAll(notsolvable);

		for (int i = 0; i < getAfterranking().size(); i++) {
			getAfterranking().get(i).setRank(i);
		}

	}

	/**
	 * Stores the probability after ranking
	 */
	public void saveProbability() {

		for (int i = 0; i < getAfterranking().size(); i++) {
			getAfterranking().get(i).setProbability(getAfterranking().get(i).calculateProbability(getSize()));
		}

	}

	/**
	 * Saves extremal points (min, max)
	 */
	public void saveMinMax() {

		getY().get(0).setMin(0);
		getY().get(0).setMax(getY().get(0).getProbability());

		double tmp = getY().get(0).getProbability();

		for (int i = 1; i < getY().size(); i++) {
			getY().get(i).setMin(tmp);
			tmp += getY().get(i).getProbability();
			getY().get(i).setMax(tmp);
		}

	}

	/**
	 * Matches the parents
	 * 
	 * --------------------------------------------
	 * 
	 * @param rate       the given rate
	 * @param upperbound the given upper bound
	 */
	public void matchParents(int rate, double upperbound) {
		for (int i = 0; i < rate; i++) {
			double randomValue = upperbound * getR().nextDouble();
			for (int j = 0; j < getY().size(); j++) {
				if (randomValue > getY().get(j).getMin() && randomValue < getY().get(j).getMax()) {
					parents.add(getY().get(j));
				}
			}
		}

	}

	/**
	 * Creates new chromosome vectors
	 * 
	 * --------------------------------------------
	 * 
	 * @param x first chromosom
	 * @param y second chromosom
	 */
	public void newChromosomes(Chromosom x, Chromosom y) {

		boolean[] tmp = new boolean[x.getVector().length];
		boolean[] tmp1 = new boolean[x.getVector().length];

		for (int i = 0; i < x.getVector().length; i++) {
			if (x.getVector()[i] == y.getVector()[i]) {
				tmp[i] = x.getVector()[i];
				tmp1[i] = x.getVector()[i];
			}

			else {
				double randomValue = 0 + (1 - 0) * getR().nextDouble();
				int xx = (int) (randomValue + 0.5);
				if (xx == 0) {
					tmp[i] = false;
					tmp1[i] = true;
				} else {
					tmp[i] = true;
					tmp1[i] = false;
				}
			}
		}

		Chromosom child1 = new Chromosom(tmp.length);
		Chromosom child2 = new Chromosom(tmp.length);

		child1.setVector(tmp);
		child2.setVector(tmp1);

		children.add(child1);
		children.add(child2);
	}

	/**
	 * Generates the migrants to refill the population
	 * 
	 * --------------------------------------------
	 * 
	 * @param rest the rest of the population
	 * @param g    the given graph
	 */
	public void generateMigrants(int rest, Graphs g) {

		for (int i = 0; i < rest; i++) {
			migration.add(new Chromosom(g.getEdges().size()));
			for (int j = 0; j < g.getEdges().size(); j++) {

				double randomValue = 0 + (1 - 0) * r.nextDouble();
				int x = (int) (randomValue + 0.5);
				if (x == 0) {
					migration.get(i).vector[j] = false;
				}

				else {
					migration.get(i).vector[j] = true;
				}
			}
		}
	}

	
	public void mutation(List<Chromosom> list) {
		double m = 0.1 + (0.9)*r.nextDouble();
		System.out.println("M : " + m + " this : " + this.getSize());
		int bound  = (int) (m * this.getSize());
		System.out.println("the number of flipped bits is : " + bound);
		for (int i = 0; i < bound; i++) {
			int chosenvector = r.nextInt(this.getSize());
			int chosenbit = r.nextInt(list.get(chosenvector).getVector().length);
			
			System.err.println("ID : " + chosenvector + " bit number : " + chosenbit);
			
			if (list.get(chosenvector).getVector()[chosenbit] == false) {
				list.get(chosenvector).getVector()[chosenbit] = true;

			}

			else {

				list.get(chosenvector).getVector()[chosenbit] = false;

			}

		}

	}
	
	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Chromosom> getY() {
		return this.y;
	}

	public void setY(List<Chromosom> y) {
		this.y = y;
	}

	public List<Chromosom> getAfterranking() {
		return this.afterranking;
	}

	public void setAfterranking(List<Chromosom> afterranking) {
		this.afterranking = afterranking;
	}

	public Random getR() {
		return this.r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public List<Chromosom> getParents() {
		return this.parents;
	}

	public void setParents(List<Chromosom> parents) {
		this.parents = parents;
	}

	public List<Chromosom> getChildren() {
		return this.children;
	}

	public void setChildren(List<Chromosom> children) {
		this.children = children;
	}

	public List<Chromosom> getMigration() {
		return this.migration;
	}

	public void setMigration(List<Chromosom> migration) {
		this.migration = migration;
	}

	public String getPopulationResultSet() {
		return populationResultSet;
	}

	public void setPopulationResultSet(String populationResultSet) {
		this.populationResultSet = populationResultSet;
	}

	/**
	 * The toString() method returns the string representation of the object
	 * CharacteristicsCalculation.
	 */
	@Override
	public String toString() {
		return (cplexSolverOutputStream + "\n" + this.getPopulationResultSet());
	}
}
