package geneticheuristic;

public class Chromosom {

	boolean feasible;
	Boolean[] vector;
	int rank;
	int efficiency;
	double probability;

	public Chromosom(Boolean[] vector) {
		this.vector = vector;

	}

	public boolean isFeasible() {
		return feasible;
	}

	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	public Boolean[] getVector() {
		return vector;
	}

	public void setVector(Boolean[] vector) {
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
		for (int i = 0; i < this.vector.length; i++) {

			if (this.vector[i] == false)
				continue;
			tmp++;
		}
		this.setEfficiency(tmp);
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public void probabilitycalculate(int p) {

		double x = (2 * ((p + 1) - getRank())) / (p * (p + 1));
		this.setProbability(x);
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

}
