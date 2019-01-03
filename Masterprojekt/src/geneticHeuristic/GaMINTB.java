package geneticHeuristic;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
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
 *          Solver for the Genetic Algorithm Minimum Tollbooth Problem
 *          (GAMINTB).
 * 
 *          Steps:
 * 
 *          1) Initialization
 * 
 *          2) Evaluation
 * 
 *          3) Selection
 * 
 *          4) Crossover, Mutation
 * 
 *          5) Termination
 */
public class GaMINTB {

	Graphs g;
	Population population;
	Chromosom chromosom;
	List<Chromosom> bestsolutions;
	String gamintbResultSet;
	int generation;
	double upperbound;
	double rate;
	double nb;
	int finalrate;
	boolean odd;
	int rest;

	ByteArrayOutputStream stream = new ByteArrayOutputStream();
	String cplexSolverOutputStream;
	IloCplex cplex;

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @throws IloException
	 *             if a CPLEX error occures
	 */
	public GaMINTB(Graphs graph, int populationSize, int generation) throws IloException {

		setGraph(graph);
		setBestsolutions(new ArrayList<>());
		setPopulation(new Population(populationSize));
		setGeneration(generation);

		cplex = new IloCplex();
		cplex.setOut(stream);

		solveGAMINTB();
	}

	/**
	 * The main algorithm of solving the GaMINTB
	 * 
	 * --------------------------------------------
	 * 
	 * @param graph
	 *            the given graph
	 * @throws IloException
	 *             if a CPLEX error occures
	 */
	public void solveGAMINTB() throws IloException {

		/*
		 * =============================================================
		 * ================= STEP 1 : initialization ===================
		 * =============================================================
		 */
		step1_initialization();

		for (int i = 0; i < getGeneration(); i++) {

			/*
			 * =============================================================
			 * =================== STEP 2 : evaluation =====================
			 * =============================================================
			 */
			step2_evaluation();

			/*
			 * =============================================================
			 * =================== STEP 3 : selection =====================
			 * =============================================================
			 */
			step3_selection();

			/*
			 * =============================================================
			 * ==================== STEP 4 : crossover =====================
			 * =============================================================
			 */
			step4_crossover();
		}

		/*
		 * =============================================================
		 * =================== STEP 45 : termination ====================
		 * =============================================================
		 */
		step5_termination();
	}

	/**
	 * 
	 */
	public void step1_initialization() {
		population.generateChromosomes(getGraph());
	}

	/**
	 * Step 2: evaluation
	 * 
	 * --------------------------------------------
	 * 
	 * @param xx
	 *            the given chromosome
	 * @return true if evaluation succesful
	 * @throws IloException
	 *             if a CPLEX error occures
	 */
	public void step2_evaluation() throws IloException {

		for (int index = 0; index < population.getY().size(); index++) {

			setChromosom(population.getY().get(index));

			ArrayList<IloNumExpr> s11 = new ArrayList<>();
			ArrayList<IloNumExpr> s12 = new ArrayList<>();
			ArrayList<IloAddable> s2 = new ArrayList<>();

			// --- initialising ro ---
			for (int i = 0; i < getGraph().getVertices().size(); i++) {
				for (int j = 0; j < getGraph().getPlayers().size(); j++) {
					getGraph().getVertices().get(i).getRo().add(j,
							cplex.numVar(-Double.MAX_VALUE, Double.MAX_VALUE, "Ro of Player " + j + " in Vertex " + i));
				}
			}

			// --- initialising beta ---
			for (int i = 0; i < getGraph().getEdges().size(); i++) {
				if (getChromosom().getVector()[i] == true) {
					getGraph().getEdges().get(i)
							.setBeta(cplex.numVar(0, Double.MAX_VALUE, "beta in the edge number : " + i));
				} else {
					getGraph().getEdges().get(i).setBeta(cplex.numVar(0, 0, "beta in the edge number : " + i));
				}
			}

			for (int i = 0; i < getGraph().getEdges().size(); i++) {

				IloNumExpr tmp = cplex.prod(getGraph().getEdges().get(i).getSum(),
						cplex.sum(cplex.constant(getGraph().getEdges().get(i).getCostB()),
								cplex.prod(cplex.constant(getGraph().getEdges().get(i).getCostA()),
										getGraph().getEdges().get(i).getSum()),
								getGraph().getEdges().get(i).getBeta()));
				s11.add(tmp);
			}

			IloNumExpr[] planet1 = s11.toArray(new IloNumExpr[s11.size()]);
			IloNumExpr x = cplex.sum(planet1);

			for (int i = 0; i < getGraph().getPlayers().size(); i++) {
				IloNumExpr tmp = cplex.prod(cplex.constant(getGraph().getPlayers().get(i).getDemand()),
						cplex.sum(getGraph().getPlayers().get(i).getSource().getRo().get(i),
								cplex.prod(-1, getGraph().getPlayers().get(i).getSink().getRo().get(i))));
				s12.add(tmp);
			}

			IloNumExpr[] planet2 = s12.toArray(new IloNumExpr[s12.size()]);
			IloNumExpr y = cplex.sum(planet2);

			IloAddable amg = cplex.addEq(0, cplex.sum(x, cplex.prod(-1, y)));

			for (int i = 0; i < getGraph().getPlayers().size(); i++) {

				for (int j = 0; j < getGraph().getEdges().size(); j++) {

					IloNumExpr tmp = cplex.sum(getGraph().getEdges().get(j).getTo().getRo().get(i),
							cplex.prod(-1, getGraph().getEdges().get(j).getFrom().getRo().get(i)));
					IloNumExpr tmp1 = cplex.sum(tmp, getGraph().getEdges().get(j).getResult());
					IloAddable tmp2 = cplex.addGe(cplex.sum(tmp1, getGraph().getEdges().get(j).getBeta()), 0);
					s2.add(tmp2);
				}
			}

			IloAddable[] planet3 = s2.toArray(new IloAddable[s2.size()]);

			cplex.add(planet3);
			cplex.add(amg);
			cplex.minimize();

			switch (String.valueOf(cplex.solve())) {
			case "true":
				cplex.clearModel();
				population.getY().get(index).setFeasible(true);
			default:
				cplex.clearModel();
				population.getY().get(index).setFeasible(false);
			}
		}
	}

	/**
	 * Step 3: selection
	 */
	public void step3_selection() {

		List<Chromosom> t = getBestsolutions();

		population.saveEtnicitiy();
		population.saveRank();
		population.saveProbability();
		population.setY(new ArrayList<>(population.getAfterranking()));
		population.getAfterranking().clear();

		t.add(population.getY().get(0));
		setBestsolutions(t);

		population.saveMinMax();

		setUpperbound(population.getY().get(population.getY().size() - 1).getMax());
		setRate(population.getR().nextDouble());
		setNb((rate * population.getSize()) / 2);
		setFinalrate((int) nb);
		setOdd((finalrate & 1) != 0);

		switch (String.valueOf(odd)) {
		case "true":
			setFinalrate(getFinalrate() + 1);
		default:
		}
	}

	/**
	 * Step 4: crossover
	 */
	public void step4_crossover() {

		population.matchParents(getFinalrate(), getUpperbound());

		for (int i1 = 0; i1 < population.getParents().size(); i1 += 2) {
			population.newChromosomes(population.getParents().get(i1), population.getParents().get(i1 + 1));
		}
		
		population.getParents().clear();

		setRest(population.getSize() - getFinalrate());
		population.generateMigrants(getRest(), getGraph());

		List<Chromosom> newgeneration = new ArrayList<>();
		newgeneration.addAll(new ArrayList<>(population.getChildren()));
		newgeneration.addAll(new ArrayList<>(population.getMigration()));
		population.getChildren().clear();
		population.getMigration().clear();
		population.setY(new ArrayList<>(newgeneration));
		population.mutation(population.getY());
		newgeneration.clear();

	}

	/**
	 * Step 5: termination
	 */
	public void step5_termination() {
		Optional<Chromosom> alpha = getBestsolutions().stream().min(Comparator.comparingInt(Chromosom::getEfficiency));
		setGamintbResultSet(alpha.get().getEfficiency() + "\n--\nalpha: " + Arrays.toString(alpha.get().getVector()) +  "\n");
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

	public Chromosom getChromosom() {
		return this.chromosom;
	}

	public void setChromosom(Chromosom chromosom) {
		this.chromosom = chromosom;
	}

	public int getGeneration() {
		return this.generation;
	}

	public int getFinalrate() {
		return this.finalrate;
	}

	public void setFinalrate(int finalrate) {
		this.finalrate = finalrate;
	}

	public double getUpperbound() {
		return this.upperbound;
	}

	public void setUpperbound(double upperbound) {
		this.upperbound = upperbound;
	}

	public double getRate() {
		return this.rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getRest() {
		return this.rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}

	public double getNb() {
		return this.nb;
	}

	public void setNb(double nb) {
		this.nb = nb;
	}

	public void setOdd(boolean odd) {
		this.odd = odd;
	}

	public boolean isOdd() {
		return this.odd;
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
	 * @param title
	 *            the title to print
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
		return (printTitle("GaMINTB") + "Best chromsom in generation " + this.getGamintbResultSet() + population.getPopulationResultSet());
	}
}
