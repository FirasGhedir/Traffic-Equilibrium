package heuristic;

import java.util.ArrayList;

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
 *          A heuristic for solving the Dynamic Slope Scaling Procedure (DSSP)
 */
public class DSSP {

	static ArrayList<IloNumExpr> s1 = new ArrayList<>();
	static ArrayList<IloNumExpr> s11 = new ArrayList<>();
	static ArrayList<IloNumExpr> s12 = new ArrayList<>();
	static ArrayList<IloAddable> s2 = new ArrayList<>();
	static IloCplex cplex;

	/**
	 * Constructor
	 * 
	 * @throws IloException
	 */
	public DSSP() throws IloException {

		cplex = new IloCplex();

	}

	public void solving(Graphs g) throws IloException {

		// initialising ro
		for (int i = 0; i < g.getVertices().size(); i++) {
			for (int j = 0; j < g.getPlayers().size(); j++) {

				g.getVertices().get(i).getRo().add(j,
						cplex.numVar(0, Double.MAX_VALUE, "Ro of Player " + j + " in Vertex " + i));

			}

		}

		// initialising beta
		for (int i = 0; i < g.getEdges().size(); i++) {
			g.getEdges().get(i).setBeta(cplex.numVar(0, Double.MAX_VALUE, "beta in the edge number : " + i));

		}

		// C*beta
		for (int i = 0; i < g.getEdges().size(); i++) {
			IloNumExpr tmp = cplex.prod(g.getEdges().get(i).getC(), g.getEdges().get(i).getBeta());
			s1.add(tmp);
		}

		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		for (int i = 0; i < g.getEdges().size(); i++) {

			IloNumExpr tmp = cplex.prod(g.getEdges().get(i).getSum(),
					cplex.sum(cplex.constant(g.getEdges().get(i).getB()),
							cplex.prod(cplex.constant(g.getEdges().get(i).getA()), g.getEdges().get(i).getSum()),
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

			for (int j = 0; j < g.getEdges().size() ; j++) {

				IloNumExpr tmp = cplex.sum(g.getEdges().get(j).getFrom().getRo().get(i),cplex.prod(-1, g.getEdges().get(j).getTo().getRo().get(i)));
				IloNumExpr tmp1 = cplex.sum(cplex.prod(-1, tmp),cplex.constant(g.getEdges().get(j).getResult()));
				IloAddable tmp2 = cplex.addGe(0, tmp1);
				s2.add(tmp2);
			}

		}
		IloAddable[] planet3 = s2.toArray(new IloAddable[s2.size()]);

		cplex.addMinimize(cplex.sum(planet));
		cplex.add(planet3);
		cplex.add(amg);
		if (cplex.solve()) {
			System.out.println("obj: " + cplex.getObjValue());}
		else {

			throw new IllegalStateException("Problem not solved.");

		}
	}

}
