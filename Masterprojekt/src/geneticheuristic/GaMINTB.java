package geneticheuristic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;

public class GaMINTB {

	ArrayList<Chromosom> chromosomesranked = new ArrayList<>();
	ArrayList<IloNumExpr> s11 = new ArrayList<>();
	ArrayList<IloNumExpr> s12 = new ArrayList<>();
	ArrayList<IloAddable> s2 = new ArrayList<>();

	public GaMINTB() {

	}



	public boolean evaluation(Graphs g, Chromosom xx) throws IloException {

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
			return false;
		}
	}

	public void saverank(ArrayList<Chromosom> chromosomes, int p) {
		// here is the sorting of the chromosoms list by their efficiency
		ArrayList<Chromosom> solvable = new ArrayList<>();
		ArrayList<Chromosom> notsolvable = new ArrayList<>();

		for (int i = 0; i < chromosomes.size(); i++) {
			System.out.println(" Print Array =" + Arrays.toString(chromosomes.get(i).getVector()));
			chromosomes.get(i).efficientycalculate();
			if (chromosomes.get(i).isFeasible()) {
				solvable.add(chromosomes.get(i));
			} else {
				notsolvable.add(chromosomes.get(i));
			}

		}

		solvable.sort(Comparator.comparing(Chromosom::getEfficiency));
		notsolvable.sort(Comparator.comparing(Chromosom::getEfficiency));
		chromosomesranked.addAll(solvable);
		chromosomesranked.addAll(notsolvable);

		for (int i = 0; i < chromosomesranked.size(); i++) {

			System.out
					.println(chromosomesranked.get(i).getEfficiency() + " : " + chromosomesranked.get(i).isFeasible());
		}

	}

	public void mains(Graphs g) throws IloException {

	
	

}
}
