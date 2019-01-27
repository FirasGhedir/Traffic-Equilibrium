package bai_A;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;

public class SocialNewModel {
	static ArrayList<IloNumExpr> s1 = new ArrayList<>();
	static ArrayList<IloAddable> s2 = new ArrayList<>();
        IloCplex cplex;
        public SocialNewModel() {
        	try {
				this.cplex = new IloCplex();
			} catch (IloException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	
	public void solveDSSP(Graphs graph) throws IloException {
		for (int i = 0; i < graph.getEdges().size(); i++) {
			for (int j = 0; j < graph.getPlayers().size(); j++) {
				graph.getEdges().get(i).getPlayers()
						.add(cplex.numVar(0, Double.MAX_VALUE, "PlayerNr : " + j + " in Edge : " + i));
			}
		}
		
		for (int i = 0; i < graph.getEdges().size(); i++) {
			IloNumExpr tmp =  cplex.sum(graph.getEdges().get(i).convertarray());
			IloNumExpr tmp1 =  cplex.sum(cplex.constant(graph.getEdges().get(i).getCostB()),
					cplex.prod(cplex.constant(graph.getEdges().get(i).getCostA()), tmp));
			IloNumExpr tmp2 =  cplex.prod(tmp, tmp1);
			s1.add(tmp2);

		}
		IloNumExpr[] planet = s1.toArray(new IloNumExpr[s1.size()]);

		
		ArrayList<IloNumVar> out = new ArrayList<>();
		ArrayList<IloNumVar> in = new ArrayList<>();

		int count = 0;
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

				IloNumExpr[] x1 =  in.toArray(new IloNumVar[in.size()]);
				IloNumExpr[] y1 =  out.toArray(new IloNumVar[out.size()]);

				IloNumExpr samara =  cplex.sum(cplex.sum(y1), cplex.prod(-1, cplex.sum(x1)));

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
		
		switch (String.valueOf(cplex.solve())) {
		case "true":

			
				System.out.println("true");
			
			break;

		default:

			throw new IllegalStateException("Problem not solved.");
		}		
	}

	public static void main(String[] args) throws IloException {
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(10, 10); // do not change !!
		test.generateGraph(graph, map);
		/*
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 4);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 4);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);
        graph.setPlayer(x);*/
		graph.generatePlayers();
		System.out.println(graph.getPlayers().size()+ " algo");
		graph.generateEdgesFunctions();
			
		
		SocialNewModel systemOptimalFlow = new SocialNewModel();
		systemOptimalFlow.solveDSSP(graph);
	}

}
