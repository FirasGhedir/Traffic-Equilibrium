package heuristic;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;

import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
import player.Player;

public class main {

	static SocialOptimum systemOptimalFlow;

	/**
	 * 
	 * @param args
	 * @throws IloException
	 */
	public static void main(String[] args) throws IloException {

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(3, 3); // do not change !!
		test.generateGraph(graph, map);

		Player player1 = new Player(0, graph.getVertices().get(0), graph.getVertices().get(8), 7);
		Player player2 = new Player(1, graph.getVertices().get(1), graph.getVertices().get(8), 4);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);
		// graph.setPlayer(x);
		graph.generatePlayers();
		graph.generateEdgesFunctions();
		System.out.println("the number of edges " + graph.getEdges().size());

		SocialOptimum systemOptimalFlow = new SocialOptimum(graph);

		System.out.println(systemOptimalFlow);
		
	    final long time = System.currentTimeMillis();

		RMINTB rm = new RMINTB(graph);
		rm.run();
		
	    System.out.printf("\n==========\nThe RMINTB heuristic took %dms for calculation.\n", System.currentTimeMillis() - time);

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
