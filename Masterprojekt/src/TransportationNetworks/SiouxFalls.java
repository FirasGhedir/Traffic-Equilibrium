package TransportationNetworks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import genetic.heuristic.Chromosom;
import genetic.heuristic.GaMINTB;
import genetic.heuristic.Population;
import graphGenerator.GridGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;
import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
import player.Player;
import tntpUtil.network;
import tntpUtil.tntpParser;

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
 *          In the Main class you can create new graphs, test and work with
 *          them.
 */
public class SiouxFalls {

	/*
	 * --- other ---
	 */
	static ArrayList<Vertex> vertices = new ArrayList<>();
	static ArrayList<Edge> edges = new ArrayList<>();

	// --- tntp parser ---
	static tntpParser parser;

	static SocialOptimum systemOptimalFlow;

	/**
	 * The main method
	 * 
	 * --------------------------------------------
	 * 
	 * @param args             the command line arguments
	 * @param adjacency_matrix
	 * @throws IOException
	 * @throws IloException
	 */
	public static void main(String[] args) throws IOException, IloException {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();

		GridGraphGenerator test = new GridGraphGenerator(5, 5); // do not change !!
		test.generateGraph(graph, map);

		/*
		 * ==========
		 */
		network transportationNetwork = network.SiouxFalls;
		parser = new tntpParser(transportationNetwork);
		vertices = parser.getVertices();
		edges = parser.getEdges();
		/*
		 * ==========
		 */

		graph.setVertices(vertices);
		graph.setEdges(edges);

		Player player1 = new Player(0, graph.getVertices().get(0), graph.getVertices().get(8), 7);
		Player player2 = new Player(1, graph.getVertices().get(1), graph.getVertices().get(8), 4);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);
		// graph.setPlayer(x);
		graph.generatePlayers();
		graph.generateEdgesFunctions();

		/*
		 * =============================================================
		 * ======================== Test MINTB =========================
		 * =============================================================
		 */

//				Mintb_FC pp = new Mintb_FC();
//				pp.run(graph);

		/*
		 * =============================================================
		 * ======================= Test GaMINTB ========================
		 * =============================================================
		 */

		GaMINTB start = new GaMINTB();

		Population population = new Population(40);

		graph.generatePlayers();

		graph.generateEdgesFunctions();
		System.out.println("the number of edges " + graph.getEdges().size());

		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);
		System.out.println(systemOptimalFlow);

		final long time = System.currentTimeMillis();

		population.generatechromosomes(graph);
		for (int i = 0; i < 20; i++) {
			population.run(start.getBestsolutions(), graph, population);

		}

		Optional<Chromosom> alpha = start.getBestsolutions().stream()
				.min(Comparator.comparingInt(Chromosom::getEfficiency));
		start.savebestsolution(graph, alpha.get());

		for (int i = 0; i < alpha.get().getBeta().size(); i++) {
			if (alpha.get().getBeta().get(i) < 0.00001) {
				alpha.get().getBeta().set(i, 0.0);
			}
		}

		alpha.get().calculateE();

		for (int i = 0; i < alpha.get().getBeta().size(); i++) {
			if (alpha.get().getBeta().get(i) == 0) {
				alpha.get().vector[i] = false;
			}
		}

		alpha.get().setFeasible(population.evaluation(graph, alpha.get()));

		System.err.println(
				"##################################################### termination ########################################");
		System.out.println("Best final solution : " + Arrays.toString(alpha.get().getVector()) + " || Efficiency : "
				+ alpha.get().getEfficiency() + " || Feasibility : " + alpha.get().isFeasible());

		System.out.printf("\n==========\nThe GAMINTB heuristic took %dms for calculation.\n",
				System.currentTimeMillis() - time);

		TestCorrectness correct = new TestCorrectness();
		TestCorrectness correct1 = new TestCorrectness();

		Graphs g1 = new Graphs(graph);
		Graphs g2 = new Graphs(graph);

		System.out.println("getPlayers(0): "
				+ correct1.test(g1, g1.getPlayers().get(0).getSource(), g1.getPlayers().get(0).getSink()));
		System.out.println("getPlayers(1): "
				+ correct.test(g2, g2.getPlayers().get(1).getSource(), g2.getPlayers().get(1).getSink()));
	}
}
