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

	public DSSP(Graphs g) throws IloException {

		for (int i = 0; i < g.getEdges().size(); i++) {
			g.getEdges().get(i).setBeta(cplex.numVar(0, Double.MAX_VALUE, "beta in the edge number : " + i));

		}

		for (int i = 0; i < g.getEdges().size(); i++) {
			IloNumExpr tmp = cplex.prod(g.getEdges().get(i).getC(), g.getEdges().get(i).getBeta());
			s1.add(tmp);
		}

		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		cplex.addMinimize(cplex.sum(planet));

		for (int i = 0; i < g.getEdges().size(); i++) {

			IloNumExpr tmp = cplex.prod(g.getEdges().get(i).getSum(), cplex.sum(cplex.constant(g.getEdges().get(i).getB()), cplex.prod(cplex.constant(g.getEdges().get(i).getA()), g.getEdges().get(i).getSum()),g.getEdges().get(i).getBeta()));
            s11.add(tmp);
		}
		
		IloNumExpr[] planet1 = s11.toArray(new IloNumExpr[s11.size()]);
        IloNumExpr x = cplex.sum(planet1);
        
        

	}

}
