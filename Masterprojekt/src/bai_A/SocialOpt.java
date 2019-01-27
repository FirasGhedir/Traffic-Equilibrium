package bai_A;

import java.util.ArrayList;

import graphModel.Graphs;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class SocialOpt {
	static ArrayList<IloNumExpr> s1 = new ArrayList<>();
	static ArrayList<IloAddable> s2 = new ArrayList<>();
	IloCplex cplex;
	
	public SocialOpt() throws IloException {
		cplex = new IloCplex();
	}

	public void solve(Graphs graph) throws IloException {
		
		for (int i = 0; i < graph.getEdges().size(); i++) {
			for (int j = 0; j < graph.getPlayers().size(); j++) {
				graph.getEdges().get(i).getPlayers()
						.add(cplex.numVar(0, Double.MAX_VALUE, "PlayerNr : " + j + " in Edge : " + i));
			}
		}
		
		for (int i = 0; i < graph.getEdges().size(); i++) {
			IloNumExpr tmp =  cplex.sum(graph.getEdges().get(i).convertarray());
			IloNumExpr xx = cplex.sum(graph.getEdges().get(i+1).convertarray());
			IloNumExpr tmpp = cplex.sum(tmp,xx);
			IloNumExpr tmp1 =  cplex.sum(cplex.constant(graph.getEdges().get(i).getCostB()),
					cplex.prod(cplex.constant(graph.getEdges().get(i).getCostA()), tmpp));
			IloNumExpr tmp2 =  cplex.prod(tmpp, tmp1);
			s1.add(tmp2);

		}
		
		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		ArrayList<IloNumVar> out = new ArrayList<>();
		ArrayList<IloNumVar> in = new ArrayList<>();

		int count = 0;
		for (int i = 0; i < graph.getPlayers().size(); i++) {
			for (int j = 0; j < graph.getVertices().size(); j++) {
				out.clear();
				for (int k = 0; k < graph.getEdges().size(); k++) {
					if (graph.getEdges().get(k).getFrom().equals(graph.getVertices().get(j))) {
						out.add(graph.getEdges().get(k).getPlayers().get(i));
                        in.add(graph.getEdges().get(k+1).getPlayers().get(i));
					}

					
				}

				IloNumVar[] y1 = out.toArray(new IloNumVar[out.size()]);
				IloNumVar[] x1 = in.toArray(new IloNumVar[out.size()]);

				IloNumExpr samara = cplex.sum(cplex.sum(y1), cplex.prod(-1, cplex.sum(x1)));

				IloAddable amg;
				
				if (graph.getVertices().get(j).equals(graph.getPlayers().get(i).getSource())) {
					amg = cplex.addEq(graph.getPlayers().get(i).getDemand(), samara);
				}

				else if (graph.getVertices().get(j).equals(graph.getPlayers().get(i).getSink())) {
					amg = cplex.addEq(-graph.getPlayers().get(i).getDemand(), samara);

				}

				else {
					amg = cplex.addEq(0, samara);

				}

				s2.add(count, amg);
				count++;
			}

		}

		IloAddable[] beta = s2.toArray(new IloAddable[s2.size()]);

		cplex.addMinimize(cplex.sum(planet));
		cplex.add(beta);
		
		
	}
}
