package heuristic;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import graphGenerator.GridGraphGenerator;
import graphModel.Graphs;
import graphModel.Vertex;
import ilog.concert.IloException;
import player.Player;

public class test {

	public static void main(String[] args) throws IloException {
		
		Map<String, Vertex> map = new TreeMap<>();
		Graphs graph = new Graphs();
		GridGraphGenerator test = new GridGraphGenerator(4, 4);
		test.generateGraph(graph, map);
		
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(6), 15);
		Player player2 = new Player(2, graph.getVertices().get(1), graph.getVertices().get(10), 8);
		Player player3 = new Player(3, graph.getVertices().get(4), graph.getVertices().get(14), 10);
		Player player4 = new Player(4, graph.getVertices().get(3), graph.getVertices().get(15), 20);
		Player player5 = new Player(5, graph.getVertices().get(2), graph.getVertices().get(11), 17);

		
		/*graph.getEdges().get(0).setA(2);
		graph.getEdges().get(1).setA(4);
		graph.getEdges().get(2).setA(3);
		graph.getEdges().get(3).setA(5);
		
		graph.getEdges().get(0).setB(0);
		graph.getEdges().get(1).setB(0);
		graph.getEdges().get(2).setB(0);
		graph.getEdges().get(3).setB(0);*/

		ArrayList<Player> x = new ArrayList<>();
		x.add(0,player1);
		x.add(1,player2);
		x.add(2,player3);
		x.add(3,player4);
		x.add(4,player5);

		graph.settplayers(x);
		
		//edge functions are totally randomized 
	    graph.generateedgesfunctions();
		
		SocialOptimum solver = new SocialOptimum();
		
		solver.step1(graph);
		DSSP solver1 = new DSSP();
		solver1.solving(graph);
	}

}
