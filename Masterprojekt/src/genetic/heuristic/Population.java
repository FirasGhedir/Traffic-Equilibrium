package genetic.heuristic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import genetic.heuristic.Chromosom;
import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;

public class Population {
	
	int size;
	List<Chromosom> y;
	List<Chromosom> afterranking;
	List<Chromosom> parents;
	List<Chromosom> children;
	List<Chromosom> migration;
	Random r = new Random();
    int checkrefused =0;
	public Population(int size) {
		this.size = size;
		y = new ArrayList<>();
		afterranking = new ArrayList<>();
		parents = new ArrayList<>();
		children = new ArrayList<>();
		migration = new ArrayList<>();

	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Chromosom> getY() {
		return y;
	}

	public void setY(List<Chromosom> y) {
		this.y = y;
	}

	public void generatechromosomes(Graphs g) {
		for (int i = 0; i < getSize(); i++) {
			y.add(new Chromosom(g.getEdges().size()));
			for (int j = 0; j < g.getEdges().size(); j++) {

				double randomValue = 0 + (1 - 0) * r.nextDouble();
				int x = (int) (randomValue + 0.5);
				if (x == 0) {
					y.get(i).vector[j] = false;
				}

				else {

					y.get(i).vector[j] = true;

				}
			}

		}

	}

	public List<Chromosom> getAfterranking() {
		return afterranking;
	}

	public void setAfterranking(List<Chromosom> afterranking) {
		this.afterranking = afterranking;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public List<Chromosom> getParents() {
		return parents;
	}

	public void setParents(List<Chromosom> parents) {
		this.parents = parents;
	}

	public List<Chromosom> getChildren() {
		return children;
	}

	public void setChildren(List<Chromosom> children) {
		this.children = children;
	}

	public List<Chromosom> getMigration() {
		return migration;
	}

	public void setMigration(List<Chromosom> migration) {
		this.migration = migration;
	}

	public boolean evaluation(Graphs g, Chromosom xx) throws IloException {

		ArrayList<IloNumExpr> s11 = new ArrayList<>();
		ArrayList<IloNumExpr> s12 = new ArrayList<>();
		ArrayList<IloAddable> s2 = new ArrayList<>();

		IloCplex cplex = new IloCplex();

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
			this.checkrefused++;
			return false;
		}
	}

	public void savefetnicitiy() {
		for (int i = 0; i < getY().size(); i++) {
			getY().get(i).calculateEfficiency();
		}
	}

	public void saverank() {

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

		
		setAfterranking(new ArrayList<>(solvable));
		getAfterranking().addAll(new ArrayList<>(notsolvable));

		for (int i = 0; i < getAfterranking().size(); i++) {
			getAfterranking().get(i).setRank(i);
		}
		
		solvable.clear();
		notsolvable.clear();

	}

	public void saveprobability() {

		for (int i = 0; i < getAfterranking().size(); i++) {
			getAfterranking().get(i).setProbability(getAfterranking().get(i).calculateProbability(getSize()));
		}

	}

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

	public void mutation(List<Chromosom> list) {
		double m = 0.1 + (0.9) * r.nextDouble();

		int bound = (int) (m * this.getSize());

		for (int i = 0; i < bound; i++) {
			int chosenvector = r.nextInt(this.getSize());
			int chosenbit = r.nextInt(list.get(chosenvector).getVector().length);
			
			if (list.get(chosenvector).getVector()[chosenbit] == false) {
				list.get(chosenvector).getVector()[chosenbit] = true;

			}

			else {

				list.get(chosenvector).getVector()[chosenbit] = false;

			}

		}

	}

	public void run(List<Chromosom> list, Graphs graph, Population firas) throws IloException {

		for (int i = 0; i < firas.getY().size(); i++) {
			firas.getY().get(i).setFeasible(firas.evaluation(graph, firas.getY().get(i)));
		}

		firas.savefetnicitiy();

		firas.saverank();

		firas.saveprobability();

		firas.setY(new ArrayList<>(firas.getAfterranking()));
		firas.getAfterranking().clear();
        if(firas.getY().get(0).isFeasible()) {
		list.add(firas.getY().get(0));
        }
		firas.saveminmax();

		double upperbound = firas.getY().get(firas.getY().size() - 1).getMax();

		double rate = firas.getR().nextDouble();

		double nb = (rate * firas.getSize()) / 2;

		int finalrate = (int) nb;

		boolean odd = (finalrate & 1) != 0;

		if (odd) {
			finalrate++;
		}

		firas.matchparents(finalrate, upperbound);

		for (int i = 0; i < firas.getParents().size(); i += 2) {

			firas.newchromosomes(firas.getParents().get(i), firas.getParents().get(i + 1));

		}
		
		firas.getParents().clear();

		int rest = firas.getSize() - finalrate;
		firas.generatemigranten(rest, graph);

		List<Chromosom> newgeneration = new ArrayList<>();
		newgeneration.addAll(firas.getChildren());
		newgeneration.addAll(firas.getMigration());

		firas.mutation(newgeneration);
				
		firas.setY(new ArrayList<>(newgeneration));
		
		newgeneration.clear();
		firas.getChildren().clear();
		firas.getMigration().clear();
		
		
	}

}
