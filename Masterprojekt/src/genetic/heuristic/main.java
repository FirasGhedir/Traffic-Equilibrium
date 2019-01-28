package genetic.heuristic;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import bai_A.Mintb_FC;
import graphGenerator.GridGraphGenerator;
import graphModel.Edge;
import graphModel.Graphs;
import graphModel.Vertex;

import heuristic.SocialOptimum;
import heuristic.TestCorrectness;
import ilog.concert.IloException;
import player.Player;
import bai_A.*;
public class main {
	
	static SocialOptimum systemOptimalFlow;
	
	/**
	 * 
	 * @param args
	 * @throws IloException
	 */
	public static void main(String[] args) throws IloException {

		Vertex a = new Vertex(1);
		Vertex b = new Vertex(2);
		Vertex c = new Vertex(3);
		Vertex d = new Vertex(4);
		Edge ab = new Edge(a, b, 1);
		Edge ac = new Edge(a, c, 2);
		Edge ad = new Edge(a, d, 5);
		Edge cb = new Edge(c, b, 3);
		Edge cd = new Edge(c, d, 1);
		Edge bd = new Edge(b, d, 3);
		ArrayList<Edge> edges = new ArrayList<>();
		ArrayList<Vertex> vertices = new ArrayList<>();
		edges.add(ab);
		edges.add(ac);
		edges.add(ad);
		edges.add(cb);
		edges.add(cd);
		edges.add(bd);
		vertices.add(a);
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);

		Graphs g = new Graphs();
		g.setEdges(edges);
		g.setVertices(vertices);

		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4); // do not change !!
		test.generateGraph(graph, map);

		
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(15), 10);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(15), 10);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0, player1);
		x.add(1, player2);
        graph.setPlayer(x);
		
		
//		graph.generatePlayers();

		graph.generateEdgesFunctions();// edge functions are totally randomized
		 systemOptimalFlow = new SocialOptimum(graph);

		Mintb_FC pp = new Mintb_FC();
		pp.run(graph);
		

	}

}
