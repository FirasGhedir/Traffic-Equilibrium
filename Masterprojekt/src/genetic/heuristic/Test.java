package genetic.heuristic;

import ilog.concert.IloNumVar;
import ilog.concert.IloRange;
import ilog.cplex.IloCplex;
import ilog.cplex.IloCplexModeler;

import java.util.ArrayList;

import graphModel.Graphs;
import ilog.concert.IloException;
import ilog.concert.IloModel;
import ilog.concert.IloNumExpr;

public class Test {

	public void solvegraph(Graphs g) throws IloException {
		IloCplexModeler m1 = new IloCplexModeler();
		IloCplex cplex = new IloCplex();

		ArrayList<IloNumVar> beta = new ArrayList<>();
		ArrayList<IloNumVar> ro = new ArrayList<>();

		for (int i = 0; i < g.getVertices().size(); i++) {
			for (int j = 0; j < g.getPlayers().size(); j++) {

				ro.add(m1.numVar(-Double.MAX_VALUE, Double.MAX_VALUE));

			}

		}

		for (int i = 0; i < g.getEdges().size(); i++) {
			beta.add(m1.numVar(0, Double.MAX_VALUE));
		}

		ArrayList<IloNumExpr> s1 = new ArrayList<>();

		for (int i = 0; i < g.getEdges().size(); i++) {
			IloNumExpr tmp = m1.prod(g.getEdges().get(i).getC(), beta.get(i));
			s1.add(tmp);
		}

		ArrayList<IloRange> s2 = new ArrayList<>();
		for (int i = 0; i < g.getPlayers().size(); i++) {

			for (int j = 0; j < g.getEdges().size(); j++) {

				
			}

		}

		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		cplex.addMinimize(m1.sum(planet));

	}

	public static void main(String[] args) {
		try {

			
			IloCplex cplex = new IloCplex();
			cplex.importModel("savfile.sav");
			cplex.solve();

			
			
		} catch (IloException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
