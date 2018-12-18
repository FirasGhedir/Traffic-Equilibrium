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
		GridGraphGenerator test = new GridGraphGenerator(5, 5);
		test.generateGraph(graph, map);
		
		Player player1 = new Player(1, graph.getVertices().get(0), graph.getVertices().get(24), 6);
		Player player2 = new Player(2, graph.getVertices().get(11), graph.getVertices().get(24), 7);
		Player player3 = new Player(3, graph.getVertices().get(16), graph.getVertices().get(24), 11);

		ArrayList<Player> x = new ArrayList<>();
		x.add(0,player1);
		x.add(1,player2);
		x.add(2,player3);
		graph.settplayers(x);
		
		//edge functions are totally randomized 
	    graph.generateedgesfunctions();
		
		SocialOptimum solver = new SocialOptimum();
		
		solver.step1(graph);

	}

}
