package heuristic;

import java.util.ArrayList;

import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
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
 *          This class contains everything for solving the system optimal flow
 *          (social optimum)
 */
public class SocialOptimum {

	static ArrayList<IloNumExpr> s1 = new ArrayList<>();
	static ArrayList<IloAddable> s2 = new ArrayList<>();

	/**
	 * Constructor
	 */
	public SocialOptimum() {

	}

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 *
	 * @param graph
	 *            the command line argument
	 * @throws IloException
	 *             if a CPLEX error occures
	 */
	public static void step1(Graphs graph) throws IloException {

		IloCplex cplex = new IloCplex();

		for (int i = 0; i < graph.getEdges().size(); i++) {
			for (int j = 0; j < graph.getPlayers().size(); j++) {
				graph.getEdges().get(i).getPlayers()
						.add(cplex.numVar(0, Double.MAX_VALUE, "PlayerNr : " + j + " in Edge : " + i));
			}
		}

		for (int i = 0; i < graph.getEdges().size(); i++) {
			IloNumExpr tmp = cplex.sum(graph.getEdges().get(i).convertarray());
			IloNumExpr tmp1 = cplex.sum(cplex.constant(graph.getEdges().get(i).getB()),
					cplex.prod(cplex.constant(graph.getEdges().get(i).getA()), tmp));
			IloNumExpr tmp2 = cplex.prod(tmp, tmp1);
			s1.add(tmp2);

		}

		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		ArrayList<IloNumVar> out = new ArrayList<>();
		ArrayList<IloNumVar> in = new ArrayList<>();
		
		int count=0;

		for (int i = 0; i < graph.getPlayers().size(); i++) {
			for (int j = 0; j < graph.getVertices().size(); j++) {
				in.clear();
				out.clear();
				for (int k = 0; k < graph.getEdges().size(); k++) {
					if (graph.getEdges().get(k).getFrom().equals(graph.getVertices().get(j))) {
						out.add(graph.getEdges().get(k).getPlayers().get(i));

					}

					if (graph.getEdges().get(k).getTo().equals(graph.getVertices().get(j))) {
						in.add(graph.getEdges().get(k).getPlayers().get(i));

					}

				}

				IloNumVar[] x1 = in.toArray(new IloNumVar[in.size()]);
				IloNumVar[] y1 = out.toArray(new IloNumVar[out.size()]);

				System.out.println("----------------------------------------------------");

				IloNumExpr samara = cplex.sum(cplex.sum(y1), cplex.prod(-1, cplex.sum(x1)));
			
				IloAddable amg;
				if (graph.getVertices().get(j).equals(graph.getPlayers().get(i).getSource())) {
					amg = cplex.addEq(graph.getPlayers().get(i).getDemand(), samara);
					System.err.println(amg.toString());
				}

				else if (graph.getVertices().get(j).equals(graph.getPlayers().get(i).getSink())) {
					amg = cplex.addEq(-graph.getPlayers().get(i).getDemand(), samara);

				}

				else {
					amg = cplex.addEq(0, samara);
   
				}
				
				System.out.println("equation numero "+ count +" : "+ amg.toString());
            
				s2.add(count,amg);
                count++;
			}


		}

		IloAddable[] beta = s2.toArray(new IloAddable[s2.size()]);

		cplex.addMinimize(cplex.sum(planet));
		cplex.add(beta);

		if (cplex.solve()) {
			System.out.println("obj: " + cplex.getObjValue());
			for(int i=0; i<graph.getEdges().size() ; i++) {
                  for(int j=0 ; j<graph.getEdges().get(i).getPlayers().size() ; j++) {
                	  System.out.println(graph.getEdges().get(i).getPlayers().get(j).getName()  + " :  " + cplex.getValue((graph.getEdges().get(i).getPlayers().get(j))));
                  }
			}

		} else {

			throw new IllegalStateException("Problem not solved.");

		}

	}

}
