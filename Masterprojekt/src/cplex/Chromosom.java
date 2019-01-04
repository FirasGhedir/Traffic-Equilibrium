package cplex;

public class Chromosom {

	
	private boolean feasible;
	public boolean[] vector;
	private int rank;
	private int efficiency;
	private double probability;
	private double max;
	private double min;

	public Chromosom(int size) {
		this.vector = new boolean[size];
	}

	public boolean isFeasible() {
		return feasible;
	}

	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	public boolean[] getVector() {
		return vector;
	}

	public void setVector(boolean[] vector) {
		this.vector = vector;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void efficientycalculate() {

		int tmp = 0;
		for (int i = 0; i < getVector().length; i++) {

			if (getVector()[i] == true) {
				tmp++;
			}
		}
		this.setEfficiency(tmp);
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public double probabilitycalculate(int p) {
		double x = 2 * ((p + 1) - getRank());
		double y = p * (p + 1);
		return x/y;

	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}
	
	

}
