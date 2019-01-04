package genetic.heuristic;

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
public class Chromosom {

	private boolean feasible;
	public boolean[] vector;
	private int rank;
	private int efficiency;
	private double probability;
	private double max;
	private double min;

	/**
	 * Constructor
	 * 
	 * --------------------------------------------
	 * 
	 * @param size the chromosom size
	 */
	public Chromosom(int size) {

		setVector(new boolean[size]);
	}

	/**
	 * Calclulates the efficiency
	 * 
	 */
	public void calculateEfficiency() {

		int tmp = 0;
		for (int i = 0; i < getVector().length; i++) {

			if (getVector()[i] == true) {
				tmp++;
			}
		}
		this.setEfficiency(tmp);
	}

	/**
	 * Calclulates the probability according to roulette-wheel-selection
	 * 
	 * --------------------------------------------
	 * 
	 * @param p the given probability
	 * @return the probability according to roulette-wheel-selection
	 */
	public double calculateProbability(int p) {
		double x = 2 * ((p + 1) - getRank());
		double y = p * (p + 1);
		return x / y;

	}

	public boolean isFeasible() {
		return this.feasible;
	}

	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	public boolean[] getVector() {
		return this.vector;
	}

	public void setVector(boolean[] vector) {
		this.vector = vector;
	}

	public int getRank() {
		return this.rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getEfficiency() {
		return this.efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public double getProbability() {
		return this.probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getMax() {
		return this.max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return this.min;
	}

	public void setMin(double min) {
		this.min = min;
	}

}
