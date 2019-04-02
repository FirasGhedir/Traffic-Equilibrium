package nickerl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.BellmanFordShortestPath;
import heuristic.GraphPath;

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

	static double delta;

	boolean debug = false;

	public Nickerl(Graphs g) throws IloException {
		this.g = g;
		cplex = new IloCplex();
		y = new ArrayList<>();
		players = new ArrayList<>();
	}

	private Graphs newgraph(Graphs g2, int x) {

		if (debug) {
			System.out.println("Start of newgraph");
		}

		Graphs graph = new Graphs();
		graph.setVertices(g2.getVertices());
		for (int i = 0; i < g2.getEdges().size(); i++) { // go through all edges of the original graph

			if (g2.getEdges().get(i).getValues().get(x) > 0) { // get the flow in the SO of player x on edge i
				graph.getEdges().add(g2.getEdges().get(i)); // add only if the flow is non-zero

				// TODO: Check if this really is proper call by value and nothing weird happens
			}

		}

		return graph;
	}

	private void printGraph(Graphs toPrint) {
		for (int i = 0; i < toPrint.getAdjacencyMatrix().length; i++) {
			for (int j = 0; j < toPrint.getAdjacencyMatrix().length; j++) {
				System.out.print(toPrint.getAdjacencyMatrix()[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private void printShortestPath(List<Edge> KP) {
		for (Edge i : KP) {
			System.out.println("(" + i.getFrom().getId() + "," + i.getTo().getId() + ")");
		}
	}

	private void printNonzeroFlows(Graphs toPrint, int p) {
		for (Edge i : toPrint.getEdges()) {
			if (i.getValues().get(p) != 0) {
				System.out.println("(" + i.getFrom().getId() + "," + i.getTo().getId() + ") : " + i.getValues().get(p));
			}
		}
	}

	public void step1() throws IloException {

		// g is the graph
		if (debug) {
			System.out.println("Start of step 1");

			System.out.println("Original Graph:");
			printGraph(g);
		}

		players = new ArrayList<>(g.getPlayers());
		for (int i = 0; i < g.getPlayers().size(); i++) { // go through all the players

			if (debug) {
				System.out.println("Data of player " + i);
				System.out.println("Source: " + g.getPlayers().get(i).getSource().getId());
				System.out.println("Sink: " + g.getPlayers().get(i).getSink().getId());
				System.out.println("Demand: " + g.getPlayers().get(i).getDemand());
			}

			while (g.getPlayers().get(i).getDemand() > 0) { // If the demand of the current player is not 0
				Graphs x = newgraph(g, i); // A new graph is generated

				if (debug) {
					System.out.println("Graph for player " + i);
					printGraph(x);
				}

				List<Edge> KP = BellmanFordShortestPath
						.findPathBetween(x, g.getPlayers().get(i).getSource(), g.getPlayers().get(i).getSink())
						.getEdgeList(); // This generates the edges on a non-zero flow shortest path

				if (debug) {
					System.out.println("Current shortest Path");
					printShortestPath(KP);

					printNonzeroFlows(x, i);
				}

				ArrayList<Double> tmp = new ArrayList<>();

				for (int k = 0; k < KP.size(); k++) {
					tmp.add(KP.get(k).getValues().get(i)); // save the value of the SO for each edge on the shortest
															// path
															// for player i
				}

				double fmax = Collections.min(tmp); // How much flow goes over this path?

				if (debug) {
					System.out.println("Current fmax: " + fmax);
				}

				// TODO: add this code to final
				// maybe we have to break out earlier...
				if (g.getPlayers().get(i).getDemand() - fmax < getDelta()) {
					tmp.clear();
					break;
				}

				Player sigma = new Player(players.size(), g.getPlayers().get(i).getSource(),
						g.getPlayers().get(i).getSink(), fmax); // create a new commodity.
																// Source and sink come from i.
																// demand is the flow going over the path
				players.add(sigma);
				// TODO: Not sure if something janky is going on here... How do the IDs work?? I
				// hope they start at 0...

				// Update the SO flows and stuff in g
				for (int k = 0; k < g.getEdges().size(); k++) { // go through all the edges of the graph
					if (KP.contains(g.getEdges().get(k))) { // if the edge is in the shortest path...
						g.getEdges().get(k).getValues().set(g.getPlayers().get(i).getId(),
								g.getEdges().get(k).getValues().get(g.getPlayers().get(i).getId()) - fmax);

						// TODO: This sets values below 0.0001 to 0. Update that everywhere necessary
						if (g.getEdges().get(k).getValues().get(g.getPlayers().get(i).getId()) < getDelta()) {
							g.getEdges().get(k).getValues().set(g.getPlayers().get(i).getId(), 0.0);
						}
						g.getEdges().get(k).getValues().add(sigma.getId(), fmax);

					} else {
						g.getEdges().get(k).getValues().add(sigma.getId(), 0.0);
					}

				}

				g.getPlayers().get(i).setDemand(g.getPlayers().get(i).getDemand() - fmax);
				tmp.clear();

			}

		}

		if (debug) {
			System.out.println("Same original graph?");
			printGraph(g);
		}

		step2(0);
	}

	// sublisthead is the id where the sublist begins
	public void step2(int sublisthead) throws IloException {

		if (debug) {
			System.out.println("Start of step 2");
		}

		// TODO: are there IDs getting shuffeld too that maybe shouldn't?
		Collections.shuffle(g.getEdges().subList(sublisthead, g.getEdges().size()));
		// Collections.shuffle(g.getEdges());
		for (int i = 0; i < g.getEdges().size(); i++) {
			g.getEdges().get(i).setBetta(0.0); // Initialize all Beta = 0

		}

		step3();

		if (debug) {
			System.out.println("End of step 2");
		}
	}

	public boolean step3() throws IloException {

		if (debug) {
			System.out.println("Start of step 3");
		}

		ArrayList<Player> p0 = new ArrayList<>(players); // p0 are just a copy of all players
		ArrayList<ArrayList<Player>> list = new ArrayList<>(); // list remembers all past playerlists

		for (int i = 0; i < g.getEdges().size() - 1; i++) {
			list.add(i, new ArrayList<Player>());
		} // add an empty arraylist for each edge

		// TODO: "i" can not necessarily be use equally as index for the list and as
		// edge index!
		// Check for everthing following whether "i" is used correcty or should be e.g.
		// i-1

		for (int id = 0; id < g.getEdges().size() - 1; id++) { // Go through all but the last edge as an id for the
																// considered player list

			// Are here call by reference problems?
			if (id == 0) {
				list.set(id, p0);
			} else {
				list.set(id, list.get(id - 1));
			}

			Boolean flag = true;
			while (flag) {
				int tmp = 0;
				flag = false;

				// Finds a player and saves him in tmp that has non-zero flow on i
				for (int j = 0; j < list.get(id).size(); j++) { // go through the player list

			//		ArrayList<Edge> a = g.getEdges();
		//			Edge b = a.get(id);
		//			ArrayList<Double> c = b.getValues();
					ArrayList<Player> d = list.get(id);
					Player e = d.get(j);

					if (debug) {
						System.out.println("Is the node id the same as the index? " + (j == e.getId()));

						System.out.println("Size of the current player List: " + list.get(id).size());
						for (Player p : list.get(id)) {
							System.out.println("ID of player: " + p.getId());
						}

					}

					// Error occurs here. Maybe the edges don't get updated properly when a new
					// commodity is created.
		//			Double f = c.get(e.getId());

					// TODO: A indexoutofboundsexpection occurs here. Reason seems to be, that the
					// list of values of some edges don't get properly updated.

					if (g.getEdges().get(id).getValues().get(list.get(id).get(j).getId()) > 0) { // i is the id for the
																									// current player
																									// list
						tmp = j;
						flag = true;
						check(list.get(id), id, tmp); // TODO: is the second i edge id or player list if?
						break;
					}
				}
			}
		}

		// TODO: This HAS!!! to be i--!! several things wrong here!!
		if (debug) {
			System.out.println("Do we even get here?");
		}
		for (int id = g.getEdges().size() - 1; id >= 0; id--) {
			if (debug) {
				System.out.println("The current size of the y vector: " + y.size());
			}

			if (y.get(id) != 1) {
				y.set(id, 0);
			}
			if (id == 0) {
				g.setPlayer(p0);
			} else {
				g.setPlayer(list.get(id - 1));
			}

			if (evaluation(g)) {
				return true;
			} else {
				y.set(id, 1);
				if (evaluation(g)) {
					return true;
				} else {

					if (debug) {
						System.out.println("Even setting stuff to 1 led to infeasibility");
					}
					y.set(id, 0);
					step2(id);

				}
			}

		}
		if (debug) {
			System.out.println("End of step 3");
		}
		return false;
	}

	// Player tmp has non-zero flow
	// alpha is the current player list p_id
	// id is the current index
	public void check(ArrayList<Player> alpha, int id, int tmp) {

		if (debug) {
			System.out.println("Start of check");

			System.out.println("is id the same as index?" + " " + (tmp == alpha.get(tmp).getId()));
		}
		Player k1 = new Player(alpha.get(tmp).getId(), alpha.get(tmp).getSource(), g.getEdges().get(id).getFrom(),
				alpha.get(tmp).getDemand());
		Player k2 = new Player(alpha.size(), g.getEdges().get(id).getTo(), alpha.get(tmp).getSink(),
				alpha.get(tmp).getDemand());
		g.getEdges().get(id).getValues().set(alpha.get(tmp).getId(), 0.0);
		Graphs x = newgraph(g, alpha.get(tmp).getId());

		if (debug) {
			for (int i = 0; i < x.getAdjacencyMatrix().length; i++) {
				for (int j = 0; j < x.getAdjacencyMatrix().length; j++) {
					System.out.print(x.getAdjacencyMatrix()[i][j]);
					System.out.print(" ");
				}
				System.out.println();
			}
		}
		GraphPath<Vertex, Edge> tmp2 = BellmanFordShortestPath.findPathBetween(x, k2.getSource(), k2.getSink());

		if (debug) {
			System.out.println(k1.getSource().getId() + " " + k1.getSink().getId());
			System.out.println(k2.getSource().getId() + " " + k2.getSink().getId());

			// TODO: A indexoutofboundexception occurs here somewhere
			// This can't work!! Some of the edges were deleted in the making of x! (fixed)

		//	ArrayList<Edge> a = g.getEdges();
		//	Edge b = a.get(id); // TODO: Error occurs HERE!! Edge Ids aren't properly used it seems. (fixed)
		//	ArrayList<Double> c = b.getValues();
	//		Double d = c.get(k1.getId());

			// TODO: Lists of values in edges for the new player k2 have not been updated
			// yet!!

			for (int i = 0; i < x.getEdges().size(); i++) {
				System.out.println("fluss im sozialen Optimum " + k1.getId() + " "
						+ g.getEdges().get(id).getValues().get(k1.getId()));
				// System.out.println("fluss im sozialen Optimum " + k2.getId() + " " +
				// g.getEdges().get(i).getValues().get(k2.getId()));

			}

			System.out.println("Is the shortest Path tmp2 null? " + (tmp2 == null));
			if (tmp2 == null) {
				System.out.println("s2 - t2: (" + k2.getSource().getId() + "," + k2.getSink().getId() + ")");
			}
		}

		List<Edge> K2 = tmp2.getEdgeList();

		if (debug) {
			System.out.println(K2.size());
		}

		GraphPath<Vertex, Edge> tmp3 = BellmanFordShortestPath.findPathBetween(x, k1.getSource(), k1.getSink());

		if (debug) {
			System.out.println("Is the shortest Path tmp3 null? " + (tmp3 == null));
			if (tmp3 == null) {
				System.out.println("s1 - t1: (" + k1.getSource().getId() + "," + k1.getSink().getId() + ")");
			}
		}

		List<Edge> K1 = tmp3.getEdgeList();

		for (Edge e : g.getEdges()) {

			e.getValues().add(k2.getId(), 0.0);

		}

		for (int i = 0; i < K1.size(); i++) {
			K1.get(i).getValues().set(k1.getId(), K1.get(i).getValues().get(alpha.get(tmp).getId()));
			K1.get(i).getValues().add(k2.getId(), 0.0);
			// K1.get(i).getValues().set(alpha.get(tmp).getId(), 0.0);

		}

		for (int i = 0; i < K2.size(); i++) {
			K2.get(i).getValues().set(k1.getId(), 0.0);
			K2.get(i).getValues().add(k2.getId(), K2.get(i).getValues().get(alpha.get(tmp).getId()));
			// K2.get(i).getValues().set(alpha.get(tmp).getId(), 0.0);
		}

		// alpha.remove(alpha.get(tmp));

		alpha.set(k1.getId(), k1);
		alpha.add(k2.getId(), k2);

		if (debug) {
			System.out.println("End of check");
		}
	}

	public void run() throws IloException {

		if (debug) {
			System.out.println("Start of run");
		}
		for (int i = 0; i < g.getEdges().size(); i++) {
			y.add(0);
		}

		step1();

		if (debug) {
			System.out.println("End of run");
		}
		for (int i = 0; i < y.size(); i++) {
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

	public double getDelta() {
		return Nickerl.delta;
	}

	public static void setDelta(double delta) {
		Nickerl.delta = delta;
	}


}
