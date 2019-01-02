package geneticHeuristic;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.SocialOptimum;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;
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
	 * 
	 * @param size
	 * @throws IloException
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
	 * 
	 * @param g
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
	 * 
	 * @param g
	 * @param xx
	 * @return
	 * @throws IloException
	 */
	public boolean evaluation(Graphs g, Chromosom xx) throws IloException {

		ArrayList<IloNumExpr> s11 = new ArrayList<>();
		ArrayList<IloNumExpr> s12 = new ArrayList<>();
		ArrayList<IloAddable> s2 = new ArrayList<>();

		// --- initialising ro ---
		for (int i = 0; i < g.getVertices().size(); i++) {
			for (int j = 0; j < g.getPlayers().size(); j++) {
				g.getVertices().get(i).getRo().add(j,
						cplex.numVar(-Double.MAX_VALUE, Double.MAX_VALUE, "Ro of Player " + j + " in Vertex " + i));
			}
		}

		// --- initialising beta ---
		for (int i = 0; i < g.getEdges().size(); i++) {
			if (xx.getVector()[i] == true) {
				g.getEdges().get(i).setBeta(cplex.numVar(0, Double.MAX_VALUE, "beta in the edge number : " + i));
			} else {
				g.getEdges().get(i).setBeta(cplex.numVar(0, 0, "beta in the edge number : " + i));
			}
		}

		for (int i = 0; i < g.getEdges().size(); i++) {

			IloNumExpr tmp = cplex.prod(g.getEdges().get(i).getSum(),
					cplex.sum(cplex.constant(g.getEdges().get(i).getCostB()),
							cplex.prod(cplex.constant(g.getEdges().get(i).getCostA()), g.getEdges().get(i).getSum()),
							g.getEdges().get(i).getBeta()));
			s11.add(tmp);
		}

		IloNumExpr[] planet1 = s11.toArray(new IloNumExpr[s11.size()]);
		IloNumExpr x = cplex.sum(planet1);

		for (int i = 0; i < g.getPlayers().size(); i++) {
			IloNumExpr tmp = cplex.prod(cplex.constant(g.getPlayers().get(i).getDemand()),
					cplex.sum(g.getPlayers().get(i).getSource().getRo().get(i),
							cplex.prod(-1, g.getPlayers().get(i).getSink().getRo().get(i))));
			s12.add(tmp);

		}
		IloNumExpr[] planet2 = s12.toArray(new IloNumExpr[s12.size()]);
		IloNumExpr y = cplex.sum(planet2);

		IloAddable amg = cplex.addEq(0, cplex.sum(x, cplex.prod(-1, y)));

		for (int i = 0; i < g.getPlayers().size(); i++) {

			for (int j = 0; j < g.getEdges().size(); j++) {

				IloNumExpr tmp = cplex.sum(g.getEdges().get(j).getTo().getRo().get(i),
						cplex.prod(-1, g.getEdges().get(j).getFrom().getRo().get(i)));
				IloNumExpr tmp1 = cplex.sum(tmp, g.getEdges().get(j).getResult());
				IloAddable tmp2 = cplex.addGe(cplex.sum(tmp1, g.getEdges().get(j).getBeta()), 0);
				s2.add(tmp2);
			}

		}
		IloAddable[] planet3 = s2.toArray(new IloAddable[s2.size()]);

		cplex.add(planet3);
		cplex.add(amg);
		cplex.minimize();

		if (cplex.solve()) {
			cplex.clearModel();
			return true;
		} else {
			cplex.clearModel();
			return false;
		}
	}

	/**
	 * 
	 */
	public void savefetnicitiy() {
		for (int i = 0; i < getY().size(); i++) {
			getY().get(i).efficientycalculate();
		}
	}

	/**
	 * 
	 */
	public void saverank() {

		List<Chromosom> solvable = new ArrayList<>();
		List<Chromosom> notsolvable = new ArrayList<>();

		for (int i = 0; i < getY().size(); i++) {
			getY().get(i).efficientycalculate();
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
	 * 
	 */
	public void saveprobability() {

		for (int i = 0; i < getAfterranking().size(); i++) {
			getAfterranking().get(i).setProbability(getAfterranking().get(i).probabilitycalculate(getSize()));
		}

	}

	/**
	 * 
	 */
	public void saveminmax() {

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
	 * 
	 * @param rate
	 * @param upperbound
	 */
	public void matchparents(int rate, double upperbound) {
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
	 * 
	 * @param x
	 * @param y
	 */
	public void newchromosomes(Chromosom x, Chromosom y) {

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
	 * 
	 * @param rest
	 * @param g
	 */
	public void generatemigranten(int rest, Graphs g) {

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

	/**
	 * 
	 * @param t
	 * @param graph
	 * @param population
	 * @throws IloException
	 */
	public void run(List<Chromosom> t, Graphs graph, Population population) throws IloException {

		for (int i = 0; i < population.getY().size(); i++) {
			population.getY().get(i).setFeasible(population.evaluation(graph, population.getY().get(i)));
		}

		population.savefetnicitiy();

		population.saverank();

		population.saveprobability();

		population.setY(population.getAfterranking());

		t.add(population.getY().get(0));

		population.saveminmax();

		double upperbound = population.getY().get(population.getY().size() - 1).getMax();

		double rate = population.getR().nextDouble();

		double nb = (rate * population.getSize()) / 2;

		int finalrate = (int) nb;

		boolean odd = (finalrate & 1) != 0;

		if (odd) {
			finalrate++;
		}

		population.matchparents(finalrate, upperbound);

		for (int i = 0; i < population.getParents().size(); i += 2) {

			population.newchromosomes(population.getParents().get(i), population.getParents().get(i + 1));

		}

		int rest = population.getSize() - finalrate;
		population.generatemigranten(rest, graph);

		List<Chromosom> newgeneration = new ArrayList<>();
		newgeneration.addAll(population.getChildren());
		newgeneration.addAll(population.getMigration());

		population.setY(newgeneration);

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
