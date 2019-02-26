package nickerl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;
import heuristic.SocialOptimum;
import ilog.concert.IloAddable;
import ilog.concert.IloException;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;
import player.Player;

public class Nickerl {

	IloCplex cplex;
	Graphs g;
	ArrayList<Integer> y;
	ArrayList<Player> players;

	public Nickerl(Graphs g) throws IloException {
		this.g = g;
		cplex = new IloCplex();
		y = new ArrayList<>();
		players = new ArrayList<>();
	}

	public void solve() throws IloException {

		// 2

		// 3

		// 4

	}

	private Graphs newgraph(Graphs g2, int x) {
		Graphs graph = new Graphs();
		graph.setVertices(g2.getVertices());
		for (int i = 0; i < g2.getEdges().size(); i++) {

			if (g2.getEdges().get(i).getValues().get(x) > 0) {
				graph.getEdges().add(g2.getEdges().get(i));
			}

		}

		return graph;
	}

	public void step1() throws IloException {
		for (int i = 0; i < g.getPlayers().size(); i++) {
			while (g.getPlayers().get(i).getDemand() > 0) {
				Graphs x = newgraph(g, i);
				List<Edge> KP = BellmanFordShortestPath
						.findPathBetween(x, g.getPlayers().get(i).getSource(), g.getPlayers().get(i).getSink())
						.getEdgeList();

				ArrayList<Double> tmp = new ArrayList<>();

				for (int k = 0; k < KP.size(); k++) {
					tmp.add(KP.get(k).getValues().get(i));
				}

				double fmax = Collections.max(tmp);
				Player sigma = new Player(players.size(), g.getPlayers().get(i).getSource(),
						g.getPlayers().get(i).getSink(), fmax);
				players.add(sigma);
				for (int k = 0; k < g.getEdges().size(); k++) {
					if (KP.contains(g.getEdges().get(k))) {
						g.getEdges().get(k).getValues().set(i, g.getEdges().get(k).getValues().get(i) - fmax);
						g.getEdges().get(k).getValues().add(fmax);

					} else {
						g.getEdges().get(k).getValues().add(0.0);
					}

				}

				g.getPlayers().get(i).setDemand(g.getPlayers().get(i).getDemand() - fmax);
				tmp.clear();

			}

		}
		System.out.println("I am jumping to step 2");
		step2(0);
	}

	public void step2(int x) throws IloException {

		Collections.shuffle(g.getEdges().subList(x, g.getEdges().size()));
		// Collections.shuffle(g.getEdges());
		for (int i = 0; i < g.getEdges().size(); i++) {
			g.getEdges().get(i).setBetta(0.0);

		}
		System.out.println("I am jumping to step 3");

		step3();
	}

	public boolean step3() throws IloException {
		ArrayList<Player> p0 = new ArrayList<>(players);
		ArrayList<ArrayList<Player>> list = new ArrayList<>();
		list.add(p0);
		for (int i = 1; i < g.getEdges().size(); i++) {
			list.add(i, new ArrayList<Player>());
		}
		for (int i = 1; i < g.getEdges().size(); i++) {
			
				list.set(i, list.get(i - 1));
			
			System.out.println("before the while");

			Boolean flag = true;
			while (flag) {
				int tmp = 0;
				flag = false;
				System.out.println(g.getEdges().get(0).getValues().size() + " values ");
				System.err.println(g.getPlayers().size() + " " + list.get(0).size());

				for (int j = 0; j < list.get(i).size(); j++) {
					if (g.getEdges().get(i).getValues().get(g.getPlayers().size() + j) > 0) {
						System.out.println(g.getEdges().get(i).getValues().get(g.getPlayers().size()+j-1));
						tmp = j;
						

						flag = true;
						
					}
					flag = false;
				}

			
			}
		}
		
				System.out.println("after the while");

		for (int i = g.getEdges().size(); i > 1; i++) {
			if (y.get(i) != 1) {
				y.set(i, 0);
			}
			g.setPlayer(list.get(i - 1));
			if (evaluation(g)) {
				return true;
			} else {
				y.set(i - 1, 1);
				if (evaluation(g)) {
					return true;
				} else {
					y.set(i, 0);
					step2(i);

				}
			}

		}
		System.out.println("finish");

		return false;
	}


	public void check(ArrayList<Player> alpha, int id) {
		for(int i = 0 ; i < alpha.size() ; i++) {
			if(this.g.getEdges().get(id).getValues().get(alpha.get(i).getId())>0) {
				Player k1 = new Player(alpha.size(), alpha.get(i).getSource(), g.getEdges().get(id).getFrom(),
						alpha.get(i).getDemand());
				Player k2 = new Player(alpha.size()+1, g.getEdges().get(id).getTo(), alpha.get(i).getSink(),
						alpha.get(i).getDemand());
                g.getEdges().get(id).getValues().set(alpha.get(i).getId(), 0.0);
				alpha.remove(alpha.get(i));
				alpha.add(k1);
				alpha.add(k2);
			}
		}
		
	}
	public void run() throws IloException {

		step1();
		for(int i = 0 ; i<y.size() ; i++) {
			System.out.println(y.get(i));
		}

	}

	public boolean evaluation(Graphs g) throws IloException {

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
			if (y.get(i) == 1) {
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

	public static void main(String[] args) throws IloException {
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(graph, map);

		// --- player ---
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 10);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 5);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);

		graph.setPlayer(x);
		graph.generateEdgesFunctions();
		Nickerl n = new Nickerl(graph);
		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);

		n.run();
	}

}
